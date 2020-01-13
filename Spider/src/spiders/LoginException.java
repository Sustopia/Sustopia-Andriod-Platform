package spiders;

/**
 * 
 * @author Star Sky
 * 专属异常类，登录失败时抛出
 */
public class LoginException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 登录失败的返回值，401往往是密码错误。
	 */
	private int statusCode;
	
	/**
	 * 
	 * @param statusCode 登录失败的返回值
	 * @param str 描述性字符串
	 */
	public LoginException(int statusCode, String str) {
		super(str);
		this.statusCode = statusCode;
	}
	
	/**
	 * 
	 * @return 登录失败的返回值
	 */
	public int getStatusCode() {
		return statusCode;
	}

}
