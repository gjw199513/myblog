package com.gjw.blog.vo;

import java.io.Serializable;

/**
 * @author gjw19
 * @date 2018/11/24
 */
public class Menu implements Serializable {


    private static final long serialVersionUID = -7445428408519087518L;
    private String name;
	private String url;

	public Menu(String name, String url) {
		this.name = name;
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
