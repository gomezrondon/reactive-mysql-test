package com.gomezrondon.reactivemysqltest;

import com.gomezrondon.reactivemysqltest.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void firstTest()   {
		ResponseEntity<List<Product>> result = restTemplate.exchange("/products", HttpMethod.GET,null, new ParameterizedTypeReference<List<Product>>(){});
		List<Product> body = result.getBody();

		Product product = body.get(body.size() - 1);
		long id = product.getId();
		assertEquals(HttpStatus.OK, result.getStatusCode());


		ResponseEntity<Product> result2 = restTemplate.exchange("/products/"+id, HttpMethod.GET,null, new ParameterizedTypeReference<Product>(){});
		Product body2 = result2.getBody();


		assertEquals(HttpStatus.OK, result2.getStatusCode());
		assertNotNull(body2);
	}



}
