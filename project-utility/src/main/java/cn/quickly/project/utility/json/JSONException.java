package cn.quickly.project.utility.json;

public class JSONException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JSONException(String message) {
		super(message);
	}

	public JSONException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public JSONException(Throwable throwable) {
		super(throwable.getMessage(), throwable);
	}

}
