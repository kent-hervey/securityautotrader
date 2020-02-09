package com.autotrader.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/testServices")
    public String testServices() {
        int pass = 0;
        int fail = 0;
        String result = "Successfully  invoked endpoing. Running tests: \n";

        //Add in calls to different test functionality and add to result string and counters

        if(pass == 0 && fail == 0){
            result += "WARNING:  No tests ran.";
        }
        else {
            result += "Completed running tests. Passed: " + pass + " Failed: " + fail;
        }
        return result;
    }


}
