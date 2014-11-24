package com.westlakestudent.entity;

import java.io.Serializable;

public class ImageUrl implements Serializable{

	/**
	 * 2014年11月21
	 */
	private static final long serialVersionUID = 1L;
	
	private int width;
	
	private int height;
	
	private String url;
	
	private String desc;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "width : " + width + ",height : " + height
				+ ", desc ; " + desc + "\n url : " + url;
	}
	
	
	public boolean isValid(){
		return url != null && !url.equals("");
	}
	
}
