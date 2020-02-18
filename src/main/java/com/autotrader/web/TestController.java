package com.autotrader.web;


import com.autotrader.web.client.robinhood.authorization.ConfidentialConstants;
import com.autotrader.web.client.robinhood.authorization.RetrieveToken;
import com.autotrader.web.client.robinhood.authorization.request.LoginData;
import com.autotrader.web.client.robinhood.authorization.response.Token;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RetrieveToken retrieveToken;

    @GetMapping("/testServices")
    public String testServices() {
        int pass = 0;
        int fail = 0;
        String result = "Successfully  invoked endpoint. Running tests: \n";

        //Add in calls to different test functionality and add to result string and counters

        if(testLogin()){
            result += "Login: PASSED";
            pass++;
        }
        else {
            result += "Login: FAILED";
            fail++;
        }


        if(pass == 0 && fail == 0){
            result += "WARNING:  No tests ran.";
        }
        else {
            result += "Completed running tests. Passed: " + pass + " Failed: " + fail;
        }
        return result;
    }

    private boolean testLogin(){
        Token token = (Token)retrieveToken.invoke(generateLoginData());
        if(token==null){
            return false;
        }
        else if("".equals(token.getAccess_token())) {
            return false;
        }
        return true;
    }

    @org.jetbrains.annotations.NotNull
    private LoginData generateLoginData(){
        LoginData loginData = new LoginData();
        loginData.setDevice_token(ConfidentialConstants.ROBINHOOD_DEVICE_TOKEN);
        loginData.setUsername(ConfidentialConstants.ROBINHOOD_USERNAME);
        loginData.setPassword(ConfidentialConstants.ROBINHOOD_PASSWORD);

        return loginData;
    }




}
