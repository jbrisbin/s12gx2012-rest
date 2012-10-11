package com.springone.rest;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdRepository extends CrudRepository<Ad, Long> {

	@Query("select a from Ad a where active = true")
	public List<Ad> activeAds();

}
