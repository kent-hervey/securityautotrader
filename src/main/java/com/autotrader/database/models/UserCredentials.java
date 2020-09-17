package com.autotrader.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@DynamoDBTable(tableName="UserCredentials")
@Data
public class UserCredentials extends DatabaseModel {

    @DynamoDBHashKey(attributeName = "user")
    private String user;

    @DynamoDBRangeKey(attributeName = "broker")
    private String broker;

    @DynamoDBAttribute(attributeName = "token")
    private String token;


}
