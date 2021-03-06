package com.springone.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdLoader implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(AdLoader.class);

	@Autowired
	private AdRepository ads;
	@Autowired
	private CampaignRepository campaigns;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (ads.count() > 0) {
			return;
		}

		Ad ad = ads.save(new Ad("Hello World!", "<h3>Hello World!</h3><p>Hello there, World!</p>"));
		LOG.info("Created Ad " + ad);

		Campaign campaign = campaigns.save(new Campaign("Hello World", true, null));
		LOG.info("Created Campaign " + campaign);
	}

}
