package spiders;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.config.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.*;
import org.apache.http.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import data_structure.*;
import data_structure.GradeInfo.Assessment;

/**
 * 
 * @author Star Sky
 * 教务系统爬虫，英文命名过于复杂，且教务系统的url本身包含jwxt，故取此名。
 * 有来自log4j的3条Warning，暂时未解决。
 */
public class JWXT_Spider {
	/**
	 * 判断是否执行login且成功
	 */
	boolean isLogin = false;
	/**
	 * 一个html解析对象
	 */
	Document document;
	/**
	 * 一个浏览器对象
	 */
	HttpClient client = HttpClients.createDefault();
	/**
	 * 超时设置为5s，不设置时，默认超时非常长，会导致程序长时间阻塞
	 */
	RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
			.setConnectionRequestTimeout(5000).build();
	/**
	 * 此命名借用了教务系统中的命名，实则为学号
	 */
	String username;
	
	/**
	 * 构造一个教务系统爬虫
	 * @param username 学号
	 */
	public JWXT_Spider(String username) {
		this.username = username;
	}
	/**
	 * 执行其它方法前务必执行此方法！
	 * @param 密码
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void login(String password) throws ClientProtocolException, IOException {
		if(isLogin) return;
		String url = "https://cas.sustech.edu.cn/cas/login";
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = client.execute(httpGet);
		int status = httpResponse.getStatusLine().getStatusCode();

		if (status == 200) {
			String entity = EntityUtils.toString(httpResponse.getEntity());
			document = Jsoup.parse(entity);
			String execution = document.select("#fm1 input[name=execution]").val();

			List<NameValuePair> pairs = new ArrayList<>();
			pairs.add(new BasicNameValuePair("username", username));
			pairs.add(new BasicNameValuePair("password", password));
			pairs.add(new BasicNameValuePair("execution", execution));
			pairs.add(new BasicNameValuePair("_eventId", "submit"));
			pairs.add(new BasicNameValuePair("geolocation", ""));

			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(pairs));
			httpResponse = client.execute(httpPost);
			int loginCode = httpResponse.getStatusLine().getStatusCode();
			if (loginCode != 200)
				throw new LoginException(loginCode, "The status code of login post is " + loginCode + "");

			httpGet = new HttpGet("https://jwxt.sustech.edu.cn/jsxsd/framework/main.jsp");
			client.execute(httpGet);
		} else {
			throw new LoginException(status, "The status code of first connection is " + status);
		}
		isLogin = true;
	}

	/**
	 * 爬取课表
	 * @return 一个所有教务系统课表中显示的课程的信息的List.
	 * @throws NotLoginException 如果没有登陆，就会抛此异常
	 * @see {@link data_structure.ClassInfo}
	 */
	public List<ClassInfo> clawTimeTable() throws ClientProtocolException, IOException, NotLoginException {
		if(!isLogin) throw new NotLoginException();
		List<ClassInfo> timeTable = new ArrayList<>();
		HttpGet get = new HttpGet("https://jwxt.sustech.edu.cn/jsxsd/xskb/xskb_list.do");
		get.setConfig(config);
		document = Jsoup.parse(EntityUtils.toString(client.execute(get).getEntity())
				.replaceAll("&nbsp;|&ensp;|&emsp;|&thinsp;|&zwnj;|&zwj;", ""));
		// 去掉html中无用的空格，防止后面判断各项长度时出现偏差。
		Elements tds = document.select("#kbtable > tbody > tr > td");
		// 每一个td都是课表中的一格
		for (int i = 0; i < tds.size(); i++) {
			Elements kbcontent = tds.get(i).select(".kbcontent");
			if (kbcontent.size() > 0) {
				Element es = tds.get(i).select(".kbcontent").get(0);
				List<TextNode> list = es.textNodes();
				for (int j = 0; j < (list.size() + 1) / 2; j++) {
					String name = list.get(j * 2).text();
					String teacher = es.select("[title=\"老师\"]").get(j).text();
					String weeksString = es.select("[title=\"周次(节次)\"]").get(j).text();
					List<Integer> weeks = weeksStringToList(weeksString);
					String room = es.select("[title=\"教室\"]").get(j).text();
					timeTable.add(new ClassInfo(name, teacher, weeks, room, (i % 7 + 1), i / 7 + 1));
				}
			}
		}
		return timeTable;
	}

	/**
	 * 
	 * @return 一个所有课程成绩组成的List。
	 * @see {@link data_structure.GradeInfo}
	 * @throws ClientProtocolException
	 * @throws NotLoginException 如果没有登陆，就会抛此异常
	 * @throws IOException
	 */
	public List<GradeInfo> clawGrades() throws ClientProtocolException, IOException, NotLoginException {
		if(!isLogin) throw new NotLoginException();
		String url = "https://jwxt.sustech.edu.cn/jsxsd/kscj/cjcx_query";
		String basicUrl = "https://jwxt.sustech.edu.cn/jsxsd/kscj/cjcx_list?kcxz=&kcmc=&xsfs=all&kksj=";
		AbstractQueue<GradeInfo> queue = new ConcurrentLinkedQueue<>();
		ArrayList<Thread> threads = new ArrayList<>();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse res = client.execute(httpGet);
		parseToDocument(res);
		Elements options = document.select("#kksj > option");
		// 这是查询成绩时的参数表
		for (int j = 1; j < options.size(); j++) {
			String lastTerm = options.get(j).val();
			Runnable r = () -> {
				try {
					HttpResponse resp = client.execute(new HttpGet(basicUrl + lastTerm));
					Document docu = staticParseToDocument(resp);
					Elements trs = docu.select("#dataList > tbody > tr");
					if (!trs.get(1).text().equals("未查询到数据"))
						for (int i = 1; i < trs.size(); i++) {
							Elements tds = trs.get(i).select("td");
							int year = Integer.parseInt(tds.get(1).ownText().substring(0, 4));
							int termNum = Integer.parseInt(tds.get(1).ownText().substring(10, 11));
							String ID = tds.get(2).ownText();
							String name = tds.get(3).ownText();
							String rank = tds.get(4).ownText();
							int grade = -1;
							try {
								grade = Integer.parseInt(tds.get(5).ownText());
							} catch (NumberFormatException e) {
								// 可能会有W,通过等情况报词错，直接忽略。
							}
							int credit = Integer.parseInt(tds.get(6).ownText());
							int period = Integer.parseInt(tds.get(7).ownText());
							String ass = tds.get(8).ownText();
							Assessment assessment;
							switch (ass) {
							case "考试":
								assessment = Assessment.EXAMINATION;
								break;
							case "考查":
								assessment = Assessment.INSPECT;
								break;
							default:
								assessment = Assessment.OTHER;
							}
							String characteristic = tds.get(10).ownText();
							String identification = tds.get(11).ownText();
							queue.add(new GradeInfo(year, termNum, ID, name, rank, grade, credit, period, assessment,
									characteristic, identification));
						}
				} catch (Exception e) {
					e.printStackTrace();
				}

			};
			threads.add(new Thread(r));
			threads.get(j - 1).start();
		}
		for (int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();//等候爬取结束，把queue转为list
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		List<GradeInfo> list = new ArrayList<>(queue);
		return list;
	}

	
	/**
	 * 获取所有成绩。
	 * 分别包括三种类型，详见GPA_Info说明。
	 * @return 一个所有三种类型成绩形成的List.
	 * @throws NotLoginException 如果没有登陆，就会抛此异常
	 * @see {@link data_structure.GPA_Info}
	 */
	public List<GPA_Info> clawGPA_List() throws NotLoginException {
		if(!isLogin) throw new NotLoginException();
		String basicUrl = "https://jwxt.sustech.edu.cn/jsxsd/kscj/cjjd_list?jdfs=";
		AbstractQueue<GPA_Info> queue = new ConcurrentLinkedQueue<>();
		String[] jdfsArgs = { "xn", "xq", "all" };
		Thread[] threads = new Thread[3];
		for (int i = 0; i < threads.length; i++) {
			int index = i;
			threads[i] = new Thread(() -> {
				try {
					HttpGet h = new HttpGet(basicUrl + jdfsArgs[index]);
					HttpResponse response = client.execute(h);
					Document docu = staticParseToDocument(response);
					Elements trs = docu.select("#dataList > tbody > tr");
					if (!trs.get(1).text().equals("未查询到数据"))
						for (int j = 1; j < trs.size(); j++) {
							Elements tds = trs.get(j).select("td");
							String s1 = tds.get(1).ownText();
							String s2 = tds.get(2).ownText();
							switch (index) {
							case 0:
								queue.add(new GPA_Info(Integer.parseInt(s1.substring(0, 4))
										, Double.parseDouble(s2)));
								break;
							case 1:
								queue.add(new GPA_Info(Integer.parseInt(s1.substring(0, 4)),
										Integer.parseInt(s1.substring(10)), Double.parseDouble(s2)));
								break;
							case 2:
								queue.add(new GPA_Info(Double.parseDouble(s1), s2));
								break;
							}
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			threads[i].start();
		}
		for(int i=0;i<threads.length;i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		List<GPA_Info> list = new ArrayList<>(queue);
		return list;
	}

	/**
	 * 将周次字符串转化为枚举了所有上课周的一个list。
	 * @return 1-12转为List中的1,2,3...12. 
	 * 			<br>2,4,6,8,10 转为List中的2,4,6,8,10
	 */
	private static List<Integer> weeksStringToList(String string) {
		List<Integer> list = new ArrayList<>();
		Pattern pattern = Pattern.compile("(\\d+(?=[,\\(]))|(\\d+-\\d+(?=[,\\(]))");
		Matcher m = pattern.matcher(string);
		while (m.find()) {
			String strMatched = m.group();
			int indexOf = strMatched.indexOf("-");
			if (indexOf < 0) {
				list.add(Integer.parseInt(strMatched));
			} else {
				int start = Integer.parseInt(strMatched.substring(0, indexOf));
				int stop = Integer.parseInt(strMatched.substring(indexOf + 1));
				for (int i = start; i <= stop; i++) {
					list.add(i);
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param 一个HttpResponse，转为类内部的document对象
	 * @throws ParseException
	 * @throws IOException
	 * @see JWXT_Spider.staticParseToDocument
	 */
	private void parseToDocument(HttpResponse h) throws ParseException, IOException {
		document = staticParseToDocument(h);
	}
	/**
	 * 
	 * @param 一个HttpResponse对象。
	 * @return 一个Document对象
	 * @throws ParseException
	 * @throws IOException
	 */
	private static Document staticParseToDocument(HttpResponse h) throws ParseException, IOException {
		return Jsoup.parse(EntityUtils.toString(h.getEntity()));
	}
	/**
	 * 仅供测试
	 * @param agrs
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws NotLoginException 
	 */
	@Deprecated
	public static void main(String[] agrs) throws ClientProtocolException, IOException, NotLoginException {
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		String password = sc.next();
		long l = System.currentTimeMillis();
		JWXT_Spider user = new JWXT_Spider(username);
		user.login(password);
		user.clawTimeTable();
		user.clawGrades();
		user.clawGPA_List();
		System.out.println(System.currentTimeMillis()-l);
	}
}
