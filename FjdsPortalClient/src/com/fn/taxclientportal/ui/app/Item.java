/**
 * 
 */
package com.fn.taxclientportal.ui.app;

import java.io.Serializable;

/**
 * @author luxiang
 *
 */
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6970679686467829064L;

	
	private String title;
	private String id;
	private String publicDate;
	private String url;
	
	public Item() {
		
	}
	
	public Item(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}
	
	public Item(String title, String id, String publicDate, String url) {
		super();
		this.title = title;
		this.id = id;
		this.publicDate = publicDate;
		this.url = url;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(String publicDate) {
		this.publicDate = publicDate;
	}
	
	
}
