package com.autotrader.web.client.robinhood.authorization.request;


import com.autotrader.web.client.ClientRequest;
import com.autotrader.web.client.ClientResponse;
import lombok.Data;

@Data
public class LoginData extends ClientResponse {

    private String device_token;
    private String username;
    private String password;


}
