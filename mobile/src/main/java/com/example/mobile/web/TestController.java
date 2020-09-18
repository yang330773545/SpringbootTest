package com.example.mobile.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/who")
    public String testMobile(Device device){
        if (device.isMobile()) {
            logger.info("Hello mobile user!");
            return "mobile";
        } else if (device.isTablet()) {
            logger.info("Hello tablet user!");
            return "tablet";
        } else {
            logger.info("Hello desktop user!");
            return "desktop";
        }
    }

    /**
     * 示例： 使用site_preference = mobile改变其首选项为mobile
     */
    @RequestMapping("/")
    public String home(SitePreference sitePreference) {
        logger.info(sitePreference.name());
        if (sitePreference == SitePreference.NORMAL) {
            logger.info("Site preference is normal");
            return "home";
        } else if (sitePreference == SitePreference.MOBILE) {
            logger.info("Site preference is mobile");
            return "home-mobile";
        } else if (sitePreference == SitePreference.TABLET) {
            logger.info("Site preference is tablet");
            return "home-tablet";
        } else {
            logger.info("no site preference");
            return "home";
        }
    }
}
