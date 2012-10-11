package com.springone.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class AdTests {

	@Autowired
	private AdRepository ads;
	@Autowired
	private CampaignRepository campaigns;
	@Autowired
	private ApplicationContext appCtx;

	@Test
	public void testAdsExist() {
		assertThat(ads.count(), greaterThan(0L));
	}

	@Test
	public void testFindByActive() {
		List<Ad> activeAds = ads.activeAds();

		assertThat(activeAds, is(notNullValue()));
		assertThat(activeAds.size(), greaterThan(0));
	}

	@Test
	@Transactional
	public void testCampaignHasAds() {
		Campaign campaign = campaigns.findAll().iterator().next();
		List<Ad> ads = campaign.getAds();

		assertThat(ads.size(), greaterThan(0));
	}

}
