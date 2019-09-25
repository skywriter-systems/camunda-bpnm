package camunda_demo.camunda;

import camunda_demo.domain.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.List;

public class printList implements JavaDelegate {

//    private static final Logger LOG = LoggerFactory.getLogger(printList.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("------------------------ Printing Users list Content -----------------------");

        String result = (String) delegateExecution.getVariable("userList");


        List<User> users = objectMapper.readValue(result, new TypeReference<List<User>>(){});

        users.stream().forEach(System.out::println);

    }
}
