package com.springone.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class AdTests {

	@Autowired
	private AdRepository ads;
	@Autowired
	private CampaignRepository campaigns;

	@Test
	public void testAdLoader() {
		long adsCnt = ads.count();
		long campaignCnt = campaigns.count();

		assertThat(adsCnt, greaterThan(0L));
		assertThat(campaignCnt, greaterThan(0L));
	}

}
