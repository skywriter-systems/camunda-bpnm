package camunda_demo.camunda;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class userList implements JavaDelegate {

   // private static final Logger LOG = LoggerFactory.getLogger(userList.class);
    private String resourceServer=Contants.mainServer;
    private RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("------------------------ Getting Users List -----------------------");

        String access_token = (String) delegateExecution.getVariable("access_token");
        System.out.println(access_token);
        String siteURL = resourceServer + "/uaa/api/users?access_token=" + access_token;
        String result =restTemplate.getForObject(siteURL, String.class);

        delegateExecution.setVariable("userList",result);
    }
}
