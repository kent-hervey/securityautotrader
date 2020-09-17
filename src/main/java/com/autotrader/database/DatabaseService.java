package com.autotrader.database;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.autotrader.database.models.DatabaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private DynamoDBMapper mapper;

    @Autowired
    public DatabaseService(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public void save(DatabaseModel databaseModel) {
        mapper.save(databaseModel);
    }

    public <T extends DatabaseModel> T read(String user, String broker, Class<T> model){
        return mapper.load(model, user, broker);
    }

    public void delete(DatabaseModel databaseModel) {
        mapper.delete(databaseModel);
    }

}
