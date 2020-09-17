package com.autotrader.database;

import com.autotrader.database.models.UserCredentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseServiceIT {

    @Autowired
    private DatabaseService databaseService;

    @Test
    public void crudTest () {
        UserCredentials newUser = new UserCredentials();
        newUser.setUser("u1");
        newUser.setBroker("b1");
        newUser.setToken("t1");

        databaseService.save(newUser);

        UserCredentials loadUser = databaseService.read(user:"u1", broker:"b1", UserCredentials.class);
        assertEquals(expected:"u1", loadUser.getUser());
        assertEquals(expected:"b1", loadUser.getBroker());
        assertEquals(expected:"t1", loadUser.getToken());

        newUser.setToken("t2");
        databaseService.save(newUser);
        UserCredentials updateUser = databaseService.read(user:"u1", broker:"b1"/*,UserCredentials.class*/);
        assertEquals(expected:"u1", updateUser.getUser());
        assertEquals(expected:"b1", updateUser.getBroker());
        assertEquals(expected:"t2", updateUser.getToken());


        databaseService.delete(newUser);

        UserCredentials deletedUser = databaseService.read(user:"u1", broker"b1", UserCredentials.class);
        assertNull(deletedUser);

    }















}
