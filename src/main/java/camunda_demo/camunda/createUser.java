package camunda_demo.camunda;

import camunda_demo.domain.User;
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
import org.springframework.web.client.RestTemplate;

@Component
public class createUser implements JavaDelegate {

//    private static final Logger LOG = LoggerFactory.getLogger(createUser.class);
    
    private String resourceServer=Contants.mainServer;
    ObjectMapper objectMapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    ObjectMapper userJsonObject = new ObjectMapper();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("------------------------ Create New user ----------------------- ");

        String login = (String) delegateExecution.getVariable("login");
        String firstName = (String) delegateExecution.getVariable("firstName");
        String lastName = (String) delegateExecution.getVariable("lastName");
        String email = (String) delegateExecution.getVariable("email");
        String imageUrl = (String) delegateExecution.getVariable("imageUrl");
        String langKey = (String) delegateExecution.getVariable("langKey");
        String access_token = (String) delegateExecution.getVariable("access_token");

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setImageUrl(imageUrl);
        newUser.setLangKey(langKey);

        String userString = userJsonObject.writeValueAsString(newUser);

        System.out.println(userString);

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        reqHeaders.set("access_token", access_token);


        HttpEntity<String> entity = new HttpEntity<String>(userString, reqHeaders);

        ResponseEntity<String>
                responseEntity = restTemplate.postForEntity(resourceServer + "/uaa/api/users?access_token=" + access_token, entity, String.class);

        JsonNode root = objectMapper.readTree(responseEntity.getBody());
//        LOG.info("----------------- New user = {}",root);

        delegateExecution.setVariable("user",root.toString());


    }
}
