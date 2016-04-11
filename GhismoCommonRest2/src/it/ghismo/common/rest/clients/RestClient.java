package it.ghismo.common.rest.clients;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

/*
import com.sun.jersey.api.json.JSONConfiguration;
 */


public class RestClient {

	public static final String USER_AGENT_NAME = "GhmCommonRest2";
	private enum HttpMethod {GET, POST, PUT, DELETE};
	
	public static String get(String restUrl) throws Exception {
		return exec(restUrl, null, HttpMethod.GET);
	}


	public static String put(String restUrl, String inputJson) throws Exception {
		return exec(restUrl, inputJson, HttpMethod.PUT);
	}	

	public static String post(String restUrl, String inputJson) throws Exception {
		return exec(restUrl, inputJson, HttpMethod.POST);
	}	
	
	public static String delete(String restUrl) throws Exception {
		return delete(restUrl, null);
	}
	public static String delete(String restUrl, String inputJson) throws Exception {
		return exec(restUrl, inputJson, HttpMethod.DELETE);
	}	

	private static String exec(String restUrl, String inputJson, HttpMethod httpMethod) throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(new JacksonFeature());
		//clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(restUrl);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.header("User-Agent", USER_AGENT_NAME);		
		
		Response response = null;
		
		switch (httpMethod) {
		case GET:
			response = invocationBuilder.get(Response.class);
			break;

		case PUT:
			response = invocationBuilder.put(Entity.json(inputJson), Response.class);
			break;

		case POST:
			response = invocationBuilder.post(Entity.json(inputJson), Response.class);
			break;

		case DELETE:
			response = invocationBuilder.delete(Response.class);
			break;
			
		default:
			response = invocationBuilder.get(Response.class);
			break;
		}
		
		if (response.getStatus() < 200 || response.getStatus() > 202) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

		
		String output = response.readEntity(String.class);

		return output;
	}	
	
	
	public static void main(String[] args) {
		try {
			//String restUrl = "http://localhost:9200/_cat/indices";
			String restUrl = "http://localhost:9200/giuseppe/annuncio2?pretty";
			String json = "{\"owner_id\" : \"ghismo\", \"subject_id\" : \"gigio\", \"description\" : \"addetto alla sicurezza nazionale\", \"tags\" : \"sicurezza\"}";
			
			//String ris = get(restUrl);
			String ris = post(restUrl, json);
			System.out.println(ris);
			
			
		} catch (Exception e) {
		}

	}
}