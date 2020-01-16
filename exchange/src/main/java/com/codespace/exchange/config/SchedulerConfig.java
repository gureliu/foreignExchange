package com.codespace.exchange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * no running task in testsF
 * 
 * @author ugureli
 *
 */
@Configuration
@EnableScheduling
@Profile("!test")
public class SchedulerConfig {

}