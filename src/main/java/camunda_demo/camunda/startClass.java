package camunda_demo.camunda;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class startClass {

    public static void main(String[] args) throws IOException {


        String serverPath = "http://localhost:9999/oauth";
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();



		HttpHeaders reqHeaders = new HttpHeaders();
		reqHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
		formParams.set("username", "admin");
		formParams.set("password", "admin");
		formParams.set("grant_type", "password");
		reqHeaders.add("Authorization", "Basic d2ViX2FwcDpjaGFuZ2VpdA==");

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formParams, reqHeaders);

		ResponseEntity<String>
				responseEntity = restTemplate.postForEntity(serverPath + "/token", entity, String.class);

		JsonNode root = objectMapper.readTree(responseEntity.getBody());

		System.out.println(root);


	}


	/*
        headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("username", "admin");
		headers.set("password", "admin");
		headers.set("contentType", "application/x-www-form-urlencoded; charset=utf-8");
		headers.set("authorization", "Basic c3RhdGljX3BhZ2U6Y2hhbmdlaXQ=");

        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("username", "admin");
		personJsonObject.put("password", "admin");

        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);

        String personResultAsJsonStr = restTemplate.postForObject(serverPath + "/token?grant_type=client_credentials", request, String.class);
		System.out.println(personResultAsJsonStr);
        JsonNode root = objectMapper.readTree(personResultAsJsonStr);

		System.out.println(root.get("access_token"));
*/

}
