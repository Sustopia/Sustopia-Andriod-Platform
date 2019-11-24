package data_structure;

/**
 * 
 * @author Star Sky
 * 记录爬取的课程成绩
 */
public class GradeInfo {
	/**
	 * 课程成绩所属年
	 * @example 2019年春季属于2018-2019-2，year处记录为2018
	 */
	private int year;
	/**
	 * 课程成绩所属学期
	 * @example 2019年春季属于2018-2019-2，termNum记录为2
	 */
	private int termNum;
	/**
	 * 课程ID
	 * @example CS102A
	 */
	private String ID;
	/**
	 * 课程名称
	 */
	private String name;
	/**
	 * 十三值制的成绩
	 * @example A-
	 */
	private String rank;
	/**
	 * 百分制，如果为P、W、通过等非数字情况，grade为-1 
	 */
	private int grade;
	/**
	 * 课程的学分
	 */
	private int credit;
	/**
	 * 课程的学时
	 */
	private int period;
	/**
	 * 课程评估方式
	 * @see {@link data_structure.GradeInfo.Assessment}
	 */
	private Assessment assessment;
	/**
	 * 课程性质
	 * @example 通识理工基础课
	 */
	private String characteristic;
	/**
	 * 成绩标识，详见教务系统，一般是空字符串
	 */
	private String identification;
	
	public GradeInfo(int year,int termNum,
			String ID,String name,String rank,int grade,int credit,
			int period,Assessment ass,String characteristic,String identification) {
		this.year = year;
		this.termNum = termNum;
		this.ID = ID;
		this.name = name;
		this.rank = rank;
		this.grade = grade;
		this.credit = credit;
		this.setPeriod(period);
		this.assessment = ass;
		this.characteristic = characteristic;
		this.identification = identification;
	}
	
	/**
	 * 
	 * @return 课程成绩所属年
	 * @example 2019年春季属于2018-2019-2，year处记录为2018
	 */
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return 课程成绩所属学期
	 * @example 2019年春季属于2018-2019-2，termNum记录为2
	 */
	public int getTermNum() {
		return termNum;
	}

	public void setTermNum(int termNum) {
		this.termNum = termNum;
	}

	/**
	 * 
	 * @return 课程ID
	 * @example CS102A
	 */
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	/**
	 * 
	 * @return 课程名称
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return 等级制
	 * @example A-
	 */
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	/**
	 * 
	 * @return 百分制，遇到非数字则为-1
	 */
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	/**
	 * 
	 * @return 课程学分
	 */
	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	/**
	 * 
	 * @return 课程考核方式
	 * @see {@link data_structure.GradeInfo.Assessment}
	 */
	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	/**
	 * 
	 * @return 课程性质
	 * @example 通识理工基础课
	 */
	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}
	
	/**
	 * 
	 * @return 成绩标识，详见教务系统，我所见均为空字符串
	 */
	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
	/**
	 * 
	 * @return 课程总学时
	 */
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * 
	 * @author Star Sky
	 * 课程考核方式<br>
	 * 目前所见共有三种<br>
	 * INSPECT: 考查
	 * EXAMINATION: 考试
	 * OTHER: 其它
	 */
	public enum Assessment{
		/**
		 * {@value 考查}
		 */
		INSPECT,
		/**
		 * {@value 考试}
		 */
		EXAMINATION,
		/**
		 * {@value 其它}
		 */
		OTHER;
	}
	
	/**
	 * 仅供测试
	 */
	@Override
	@Deprecated
	public String toString() {
		return this.year+" "+this.termNum+" "+this.ID+" "+this.name+" "
				+this.grade+" "+this.credit+" "+this.period+" "+this.characteristic+" "
				+this.rank+" "+this.assessment+"\n";
	}
}
