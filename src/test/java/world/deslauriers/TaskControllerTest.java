package world.deslauriers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import world.deslauriers.domain.dto.QualityCompleteUpdateCommand;

@MicronautTest
public class TaskControllerTest {
	
	@Inject
	@Client("/")
	HttpClient client;
	
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
	}
}
