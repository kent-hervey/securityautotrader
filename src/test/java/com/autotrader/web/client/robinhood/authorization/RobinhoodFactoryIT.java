package com.autotrader.web.client.robinhood.authorization;

import com.autotrader.web.client.BrokerCall;
import com.autotrader.web.client.ClientRequest;
import com.autotrader.web.client.ClientResponse;
import com.autotrader.web.client.robinhood.RobinhoodFactory;
import com.autotrader.web.client.robinhood.authorization.request.LoginData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobinhoodFactoryIT {

    @Autowired
    private RobinhoodFactory robinhoodFactory;

    @Test
    public void invokeTest(){
        ClientResponse response = robinhoodFactory.invoke(BrokerCall.ROBINHOOD_LOGIN, generateLoginData());
    }

    private LoginData generateLoginData() {
        LoginData loginData = new LoginData();
        loginData.setDevice_token(ConfidentialConstants.ROBINHOOD_DEVICE_TOKEN);
        loginData.setUsername(ConfidentialConstants.ROBINHOOD_USERNAME);
        loginData.setPassword(ConfidentialConstants.ROBINHOOD_PASSWORD);

        return loginData;

    }


}
