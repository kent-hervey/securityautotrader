package com.autotrader.database;

import com.autotrader.database.models.UserCredentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseServiceIT {

    @Autowired
    private DatabaseService databaseService;

    @Test
    public void crudTest () {

        UserCredentials newUser22 = new UserCredentials();
        newUser22.setUser("22u1");
        newUser22.setBroker("22b1");
        newUser22.setToken("22t1");

        databaseService.save(newUser22);

        UserCredentials loadUser22 = databaseService.read("22u1", "22b1", UserCredentials.class);
        System.out.println("loadUser22 is  " + loadUser22);
        loadUser22.setToken(loadUser22.getToken() + "plusThis");
        System.out.println("loadUser22 is now " + loadUser22);
        databaseService.save(loadUser22);

        UserCredentials newUser = new UserCredentials();
        newUser.setUser("u1");
        newUser.setBroker("b1");
        newUser.setToken("t1");

        databaseService.save(newUser);

        UserCredentials loadUser = databaseService.read("u1", "b1", UserCredentials.class);
        assertEquals("u1", loadUser.getUser());
        assertEquals("b1", loadUser.getBroker());
        assertEquals("t1", loadUser.getToken());

        newUser.setToken("t2");
        databaseService.save(newUser);
        UserCredentials updateUser = databaseService.read("u1", "b1", UserCredentials.class);
        assertEquals("u1", updateUser.getUser());
        assertEquals("b1", updateUser.getBroker());
        assertEquals("t2", updateUser.getToken());


        //databaseService.delete(newUser);

//        UserCredentials deletedUser = databaseService.read("u1", "b1", UserCredentials.class);
//        assertNull(deletedUser);




    }















}
