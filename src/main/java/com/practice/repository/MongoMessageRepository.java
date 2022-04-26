package com.practice.repository;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.practice.model.Message;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MongoMessageRepository implements MessageRepository {
private final MongoClient db;

    public MongoMessageRepository(MongoClient db) {
        this.db = db;
    }
    private MongoDatabase getDb(){
        return db.getDatabase("chatDB");
    }
private MongoCollection<Message> getCollection(){
        return getDb().getCollection("messageCollection", Message.class);
}


    @Override
    public List<Message> getMessage(String roomId) {
        Bson filter = Filters.eq("_id", roomId);
        return getCollection().find(filter,Message.class).into(new ArrayList<>());
    }

    @Override
    public void saveMessage(String sentBy, String receivedMessage, String sentTime) {
        Message message = new Message(sentBy, receivedMessage,sentTime);
        getCollection().insertOne(message);
    }

}
