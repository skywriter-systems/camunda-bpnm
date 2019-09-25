package camunda_demo.camunda;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class printUser implements JavaDelegate {

    ObjectMapper userJsonObject = new ObjectMapper();
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

//        String newUserJson = (String) delegateExecution.getVariable("user");
//
//        System.out.println(newUserJson);
//
//        User newUser = userJsonObject.readValue(newUserJson,User.class);
//
//        System.out.println("--------------------- new User Id = "+newUser.getId());
    }
}
