package com.autotrader.web.client.robinhood.authorization;

import com.autotrader.web.client.robinhood.authorization.request.LoginData;
import com.autotrader.web.client.robinhood.authorization.request.Request;
import com.autotrader.web.client.robinhood.authorization.response.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class RetrieveTokenTest {
    private static final String URL = "https://api.robinhood.com/oauth2/token/";
    private static final String TOKEN = "Some token string";
    private static String USERNAME = "Some username";
    private static String PASSWORD = "Some password";
    private static String DEVICE_TOKEN = "Some device token";


    @Mock
    private RestTemplate mockRestTemplate;
    //first right failing test case
    private RetrieveToken retrieveToken;

    @Before
    public void setup() {
        retrieveToken = new RetrieveToken(mockRestTemplate);
    }

    @Test
    public void invokeTest(){
        when(mockRestTemplate.exchange(anyString(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<?>>any(), Mockito.<Class<?>>any())).thenReturn(generateResponseEntity());


        Token response = (Token)retrieveToken.invoke(generateLoginData());


        assertEquals(TOKEN, response.getAccess_token());


        ArgumentCaptor<HttpEntity> httpEntityArgumentCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(mockRestTemplate).exchange(eq(URL), eq(HttpMethod.POST), httpEntityArgumentCaptor.capture(), eq(Token.class));

        HttpEntity<Request> captureHttpEntity = httpEntityArgumentCaptor.getValue();
        HttpHeaders capturedHeaders = captureHttpEntity.getHeaders();
        assertEquals(MediaType.APPLICATION_JSON, capturedHeaders.getContentType());

        Request captureRequest = captureHttpEntity.getBody();
        assertEquals(Constants.ROBINHOOD_GRANT_TYPE, captureRequest.getGrant_type());
        assertEquals(Constants.ROBINHOOD_CLIENT_ID,  captureRequest.getClient_id());
        assertEquals(Constants.ROBINHOOD_SCOPE, captureRequest.getScope());
        assertEquals(USERNAME, captureRequest.getUsername());
        assertEquals(PASSWORD, captureRequest.getPassword());
        assertEquals(DEVICE_TOKEN, captureRequest.getDevice_token());


    }

    private ResponseEntity generateResponseEntity() {
        Token token = new Token();
        token.setAccess_token(TOKEN);

        return new ResponseEntity(token, HttpStatus.OK);

    }

    private LoginData generateLoginData(){
        LoginData loginData = new LoginData();
        loginData.setUsername(USERNAME);
        loginData.setPassword(PASSWORD);
        loginData.setDevice_token(DEVICE_TOKEN);

        return loginData;
    }


}
