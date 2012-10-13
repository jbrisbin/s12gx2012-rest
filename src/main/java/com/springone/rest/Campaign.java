package com.springone.rest;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Campaign
 * 
 */
@Entity
public class Campaign implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Boolean active;
	@OneToMany
	private List<Ad> ads;

	public Campaign() {
	}

	public Campaign(String name, Boolean active, List<Ad> ads) {
		super();
		this.name = name;
		this.active = active;
		this.ads = ads;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", name=" + name + ", active=" + active + ", ads=" + ads + "]";
	}

}
