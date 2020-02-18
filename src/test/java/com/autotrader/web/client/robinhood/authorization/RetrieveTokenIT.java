package com.autotrader.web.client.robinhood.authorization;


import com.autotrader.web.client.robinhood.authorization.request.LoginData;
import com.autotrader.web.client.robinhood.authorization.response.Token;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetrieveTokenIT {

    @Autowired
    private RetrieveToken retriveToken;

    @Test
    public void SpringTest(){
        Token token = (Token)retriveToken.invoke(generateLoginData());
        assertNotNull(token);
        assertTrue(StringUtils.isNotBlank(token.getAccess_token()));


    }

    private LoginData generateLoginData(){
        LoginData loginData = new LoginData();
        loginData.setDevice_token(ConfidentialConstants.ROBINHOOD_DEVICE_TOKEN);
        loginData.setUsername(ConfidentialConstants.ROBINHOOD_USERNAME);
        loginData.setPassword(ConfidentialConstants.ROBINHOOD_PASSWORD);

        return loginData;
    }



}
