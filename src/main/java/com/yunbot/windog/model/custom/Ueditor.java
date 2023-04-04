package com.yunbot.windog.model.custom;
/**
 * @author William
 */
public class Ueditor {
	private String state;
	private String url;
	private String title;
	private String original;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public Ueditor(String state, String url, String title, String original) {
		super();
		this.state = state;
		this.url = url;
		this.title = title;
		this.original = original;
	}

	public Ueditor() {
		super();
	}
	
	
}
