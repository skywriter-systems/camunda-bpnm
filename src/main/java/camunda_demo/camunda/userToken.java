package camunda_demo.camunda;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class userToken implements JavaDelegate {

//    private static final Logger LOG = LoggerFactory.getLogger(userToken.class);

    ObjectMapper objectMapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    private String serverPath=Contants.authServer;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("------------------------ Getting User Token ----------------------- ");

        String username = (String)delegateExecution.getVariable("username");
        String password = (String)delegateExecution.getVariable("password");
//        System.out.println("------------------------ data ----------------------- "+serverPath,username,password);
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
        formParams.set("username", username);
        formParams.set("password", password);
        formParams.set("grant_type", "password");
        reqHeaders.add("Authorization", "Basic d2ViX2FwcDpjaGFuZ2VpdA==");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formParams, reqHeaders);

        ResponseEntity<String>
                responseEntity = restTemplate.postForEntity(serverPath + "/token", entity, String.class);

        JsonNode root = objectMapper.readTree(responseEntity.getBody());


        delegateExecution.setVariable("access_token", root.get("access_token").asText());

    }
}
