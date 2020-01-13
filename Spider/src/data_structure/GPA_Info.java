package data_structure;

public class GPA_Info {
	/**
	 * 存储的GPA的类型
	 * @see {@link data_structure.GPA_Info.GPA_Type}
	 */
	private GPA_Type type;
	/**
	 * GPA所属年<br>
	 * 总GPA则为-1
	 */
	private int year;
	/**
	 * GPA所属学期<br>
	 * 学年GPA或者总GPA则为-1
	 */
	private int term;
	/**
	 * GPA值
	 */
	private double gpa;
	/**
	 * GPA排名
	 * @example 20/70
	 */
	private String rank;
	
	public GPA_Info(int year, int term, double gpa,String rank,GPA_Type type) {
		super();
		this.year = year;
		this.term = term;
		this.gpa = gpa;
		this.type = type;
		this.rank = rank;
	}
	
	public GPA_Info(int year, double gpa) {
		this(year,-1,gpa,"",GPA_Type.YEAR);
	}
	
	public GPA_Info(int year, int term, double gpa) {
		this(year,term,gpa,"",GPA_Type.TERM);
	}
	
	public GPA_Info(double gpa,String rank) {
		this(-1,-1,gpa,rank,GPA_Type.ALL);
	}
	
	/**
	 * 
	 * @return GPA排名，不为总GPA时没有排名，为空字符串
	 * @example 20/70
	 */
	public String getRank() {
		return rank;
	}
	
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public void setType(GPA_Type type) {
		this.type = type;
	}
	/**
	 * 
	 * @return GPA类型
	 * @see {@link data_structure.GPA_Info.GPA_Type}
	 */
	public GPA_Type getType() {
		return type;
	}
	/**
	 * 
	 * @return GPA所属年，当为总GPA时，此值为-1
	 */
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * 
	 * @return GPA所属学期，范围[1, 3]，当为年度GPA或总GPA时，此值为-1
	 */
	public int getTerm() {
		return term;
	}
	
	public void setTerm(int term) {
		this.term = term;
	}
	/**
	 * 
	 * @return GPA值
	 */
	public double getGpa() {
		return gpa;
	}
	
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	/**
	 * 仅供测试
	 */
	@Override
	@Deprecated
	public String toString() {
		return this.year+" "+this.term+" "+this.gpa+" "+this.rank+" "+this.type;
	}
	/**
	 * 
	 * @author Star Sky<br>
	 * 三种GPA类型<br>
	 * YEAR:年度GPA<br>
	 * TERM:学期GPA<br>
	 * ALL:总GPA
	 */
	public enum GPA_Type{
		/**
		 * {@value 年度}
		 */
		YEAR,
		/**
		 * {@value 学期}
		 */
		TERM,
		/**
		 * {@value 总}
		 */
		ALL;
	}
}
