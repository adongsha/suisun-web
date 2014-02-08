package cn.suisun.utils;

public class MyException extends Exception{


	private static final long serialVersionUID = -1421116533818041254L;

	public MyException() {
		super();
	}

	public MyException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyException(String message) {
		super(message);
	}

	public MyException(Throwable cause) {
		super(cause);
	}

	
}
