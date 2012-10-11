package com.springone.rest;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Entity implementation class for Entity: Ad
 * 
 */
@Entity
public class Ad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String content;
	private Date created;
	private Date updated;
	private boolean active;

	public Ad() {
	}

	public Ad(String title, String content) {
		super();
		this.title = title;
		this.content = content;
		this.active = true;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Ad [id=" + id + ", title=" + title + ", content=" + content + ", created=" + created + ", updated="
				+ updated + ", active=" + active + "]";
	}

	@PrePersist
	private void setCreated() {
		this.created = Calendar.getInstance().getTime();
	}

	@PreUpdate
	private void setUpdated() {
		this.updated = Calendar.getInstance().getTime();
	}

}
