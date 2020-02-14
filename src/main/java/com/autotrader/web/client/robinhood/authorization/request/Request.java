package com.autotrader.web.client.robinhood.authorization.request;

import com.autotrader.web.client.ClientRequest;
import lombok.Data;

@Data //annotation supplied by Lombok which will add boilerplate such as getters and setters
public class Request extends ClientRequest{
    private String grant_type;
    private String scope;
    private String client_id;

    private String device_token;
    private String username;
    private String password;




}
