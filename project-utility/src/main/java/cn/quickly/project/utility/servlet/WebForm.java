package cn.quickly.project.utility.servlet;

import java.io.Serializable;

public class WebForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String method;

	private String action;

	private String enctype;

	private String charset = "UTF-8";

	private boolean debug;

	private FormInputs inputs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEnctype() {
		return enctype;
	}

	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public FormInputs getInputs() {
		return inputs;
	}

	public void setInputs(FormInputs inputs) {
		this.inputs = inputs;
	}

}
