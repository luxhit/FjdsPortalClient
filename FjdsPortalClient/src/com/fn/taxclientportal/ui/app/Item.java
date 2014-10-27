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
