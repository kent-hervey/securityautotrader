package com.autotrader.web;


import com.autotrader.web.client.BrokerCall;
import com.autotrader.web.client.robinhood.RobinhoodFactory;
import com.autotrader.web.client.robinhood.authorization.ConfidentialConstants;
import com.autotrader.web.client.robinhood.authorization.RetrieveToken;
import com.autotrader.web.client.robinhood.authorization.request.LoginData;
import com.autotrader.web.client.robinhood.authorization.response.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger((TestController.class));

    @Autowired
    //private RetrieveToken retrieveToken;
    private RobinhoodFactory factory;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");



    @GetMapping("/testServices")
    public String testServices() {

        Date date = new Date();
        String showDate = dateFormat.format(date);
        LOGGER.warn("REST endpoint invoke:  /testServices");

        int pass = 0;
        int fail = 0;
        String result = "At " + showDate+ "\n2Successfully  invoked endpoint. Running tests: \n";

        //Add in calls to different test functionality and add to result string and counters

        if(testLogin()){
            result += "Login: PASSED ";
            pass++;
        }
        else {
            result += "Login: FAILED ";
            fail++;
        }


        if(pass == 0 && fail == 0){
            result += "WARNING:  No tests ran.";
        }
        else {
            result += "Completed running tests. Passed: " + pass + " Failed: " + fail;
        }

        LOGGER.warn("Test Services endpoint result:  " + result);
        return result;
    }


    private boolean testLogin(){
        Token token = (Token)factory.invoke(BrokerCall.ROBINHOOD_LOGIN, generateLoginData());
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
