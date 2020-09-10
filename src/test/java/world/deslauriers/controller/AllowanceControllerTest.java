package world.deslauriers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import world.deslauriers.domain.Allowance;
import world.deslauriers.domain.dto.QualityCompleteUpdateCommand;

@MicronautTest
public class AllowanceControllerTest {

	@Inject
	@Client("/")
	HttpClient client;
	
	@Test
	public void supplyInvalidNameTriggersValidationFailure() {
		
		HttpClientResponseException thrown = 
				assertThrows(HttpClientResponseException.class, () -> {
					client.toBlocking().exchange(HttpRequest.GET("/api/v1/WRONG"));
				});
		
		assertNotNull(thrown.getResponse());
		assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
	}
	
	@Test
	public void testCrudOperations() {
		
		//put quality
		QualityCompleteUpdateCommand cmd = new QualityCompleteUpdateCommand();
		cmd.setTaskId(Long.valueOf(73));
		cmd.setUpdateStatus(false);
		HttpRequest request = HttpRequest.PUT("/api/v1/task/quality", cmd);
        HttpResponse response = client.toBlocking().exchange(request);  

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
        
        //put complete
		request = HttpRequest.PUT("/api/v1/task/complete", cmd);
        response = client.toBlocking().exchange(request); 
        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
		
		
		//findall
		// Jaskson XML encoding error on JsonBackReferences
		request = HttpRequest.GET("/api/v1/allowance/all");
		List<Allowance> allowances = 
				client.toBlocking().retrieve(request, Argument.of(List.class, Allowance.class));
	
		assertEquals(1, allowances.size());
		
		
		
		
	}
	
	
}
