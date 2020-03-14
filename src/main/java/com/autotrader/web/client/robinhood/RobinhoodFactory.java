package com.autotrader.web.client.robinhood;


import com.autotrader.web.client.BrokerCall;
import com.autotrader.web.client.ClientRequest;
import com.autotrader.web.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RobinhoodFactory {
    private static final Logger Logger = LoggerFactory.getLogger(RobinhoodFactory.class);

    @Autowired
    private List<RobinhoodCalls> robinhoodCalls;

    private Map<BrokerCall, RobinhoodCalls> mappedRobinhoodCalls;

    public ClientResponse invoke(BrokerCall brokerCall, ClientRequest clientRequest){
        return mappedRobinhoodCalls.get(brokerCall).invoke(clientRequest);

    }

    @PostConstruct //Java 1.8 annotation triggers the method to populate...
    private void populateMappedRobinhoodCalls(){
        mappedRobinhoodCalls = new HashMap<>();

        for(RobinhoodCalls calls : robinhoodCalls){
            Logger.info("Populating mappedRobinhoodCalls with service:  " + calls.getBrokerCall());
            mappedRobinhoodCalls.put(calls.getBrokerCall(), calls);


        }
    }


}
