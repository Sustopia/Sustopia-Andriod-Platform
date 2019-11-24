package data_structure;

import java.util.List;

/**
 * 课表中的课程信息，详见各方法
 * @author Star Sky
 * @version 1.0.0
 */
public class ClassInfo {
	/**
	 * 课程名称
	 */
	private String name;
	/**
	 * 课程老师名称
	 */
	private String teacher;
	/**
	 * 上课的周次，枚举了所有上课的周次
	 * @example [2,4,6,8,10,12,14,16]
	 */
	private List<Integer> weeks;
	/**
	 * 上课的教室，是一个完整的字符串
	 * @example 一教301
	 */
	private String room;
	/**
	 * 表示一星期中的第几日，范围是[1, 7]
	 */
	private int weekDay;
	/**
	 * 表示当日上课时间是第几节大课，范围是[1, 6]
	 */
	private int time;

	/**
	 * 
	 * @param name 课程名称
	 * @param teacher 课程老师
	 * @param weeks 课程周列表
	 * @param room 课程教室
	 * @param weekDay 上课的星期
	 * @param time 当日的节次
	 */
	public ClassInfo(String name, String teacher, List<Integer> weeks, String room,int weekDay,int time) {
		this.name = name;
		this.teacher = teacher;
		this.weeks = weeks;
		this.room = room;
		this.weekDay = weekDay;
		this.time = time;
	}
	
	/**
	 * @return 上课的是周几，范围[1, 7]
	 */
	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}
	
	/**
	 * 
	 * @return 当日第几节大课，范围[1, 6]，早课是1，晚课是5.
	 */
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
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
	 * @return 任课老师
	 */
	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	/**
	 * 
	 * @return 上课周次，枚举了所有周
	 * @example [2,4,6,8,10,12,14,16]
	 */
	public List<Integer> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Integer> weeks) {
		this.weeks = weeks;
	}
	
	/**
	 * 
	 * @return 课程教室
	 * @example 一教301
	 */
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	/**
	 * 仅供测试用
	 */
	@Override
	@Deprecated
	public String toString() {
		return name+" "+teacher+" "+weeks+" "+room+" "+time+"\n";
	}

}
