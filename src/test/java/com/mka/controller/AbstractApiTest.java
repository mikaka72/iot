package com.mka.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mka.App;
import com.mka.AppConfig;
import com.mka.SecurityConfiguration;
import com.mka.TestMongoConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureWebClient
@AutoConfigureMockMvc
@SpringBootTest(classes = {
		App.class,
        AppConfig.class,
        SecurityConfiguration.class
})
public abstract class AbstractApiTest {

	 @Autowired
	 protected MockMvc mockMvc;
	 
	 @Autowired
	 protected ObjectMapper objectMapper;
	
}
