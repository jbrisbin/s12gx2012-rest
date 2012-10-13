package com.springone.rest;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(rel = "ads", path = "ads")
public interface AdRepository extends CrudRepository<Ad, Long> {

	@RestResource(rel = "active", path = "active")
	public List<Ad> findByActiveIsTrue();

}
