package spiders;

/**
 * 
 * @author Star Sky
 * 专属异常类，当没有登陆就试图爬取时抛出
 */
public class NotLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotLoginException() {
		super("Please execute login first!!!");
	}
	
}
