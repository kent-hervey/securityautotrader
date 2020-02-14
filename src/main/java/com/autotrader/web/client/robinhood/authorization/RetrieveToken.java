package com.autotrader.web.client.robinhood.authorization;

import com.autotrader.web.client.ClientResponse;
import com.autotrader.web.client.robinhood.authorization.request.LoginData;
import com.autotrader.web.client.robinhood.authorization.request.Request;
import com.autotrader.web.client.robinhood.authorization.response.Token;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;



public class RetrieveToken {

    private static final String URL = "https://api.robinhood.com/oauth2/token/";

    private RestTemplate restTemplate;

    public RetrieveToken(RestTemplate restTemplate){
        this.restTemplate = restTemplate;

    }

    public ClientResponse invoke(LoginData loginData) {
        //System.out.println("test me me");
        //System.out.println("\n\n\nIs RestTemplate null? " + (restTemplate==null)); Alex removed in Ep 9
        //System.out.println("afterwards");

        //first parameter is URL, second is method request type, third is http entity object
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Request> httpEntity = new HttpEntity<Request>(generateRequest(loginData), httpHeaders);
        ResponseEntity<Token> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, Token.class);
        Token token = responseEntity.getBody();

        //System.out.println("\n\nToken:  " + token.getAccess_token().substring(0,1000));
        System.out.println("\n\nToken:  " + token.getAccess_token().substring(0));
        System.out.println("token length is;  " + token.getAccess_token().length());

        return token;
    }

    private Request generateRequest(LoginData loginData) {
        Request request = new Request();
        request.setGrant_type(Constants.ROBINHOOD_GRANT_TYPE);
        request.setScope(Constants.ROBINHOOD_SCOPE);
        request.setClient_id(Constants.ROBINHOOD_CLIENT_ID);

        request.setDevice_token(loginData.getDevice_token());
        request.setUsername(loginData.getUsername());
        request.setPassword(loginData.getPassword());

        return request;
    }



}