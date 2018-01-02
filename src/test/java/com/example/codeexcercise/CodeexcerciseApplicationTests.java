package com.example.codeexcercise;


import com.example.codeexcercise.controller.AppMainController;
import com.example.codeexcercise.model.BodySum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CodeexcerciseApplicationTests {

	private String LOCALHOST = "http://localhost:";

	@Autowired
	AppMainController appMainController;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception{
		assertThat(appMainController).isNotNull();
	}

	@Test
	public void shouldReturnObject(){
		assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/",
				BodySum.class)).isNotNull();

	}

	@Test
	public void shouldReturnBadRequest(){
		BodySum bodySum = this.restTemplate.getForObject(LOCALHOST + port + "/",
				BodySum.class);
		bodySum.setSumResult(0);
		assertThat(this.restTemplate.postForEntity(LOCALHOST + port + "/", bodySum, BodySum.class)
				.getStatusCode().is4xxClientError()).isEqualTo(true);
	}

	@Test
	public void shouldReturnOK(){
		BodySum bodySum = this.restTemplate.getForObject(LOCALHOST + port + "/",
				BodySum.class);
		int tmpSum = 0;
		for (int i : bodySum.getNumbers()) {
			tmpSum += i;
		}
		bodySum.setSumResult(tmpSum);
		assertThat(this.restTemplate.postForEntity(LOCALHOST + port + "/", bodySum, BodySum.class)
				.getStatusCode().is2xxSuccessful()).isEqualTo(true);
	}
}
