package com.practice.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.practice.model.Room;
import org.bson.conversions.Bson;

public class MongoRoomRepository implements RoomRepository {
    private final MongoClient db;

    public MongoRoomRepository(MongoClient db) {
        this.db = db;
    }
    private MongoDatabase getDb() {
        return db.getDatabase("chatDB");
    }
    public MongoCollection<Room> getCollection()
    {
        return getDb().getCollection("roomCollection", Room.class);
    }
    public Room getRoom(String senderId, String receiverId)
    {
        Bson filter1 = Filters.eq("senderId", senderId);
        Bson filter2 = Filters.eq("receiverId", receiverId);
        Bson filter3 = Filters.eq("senderId", receiverId);
        Bson filter4 = Filters.eq("receiverId", senderId);
        Bson filter5 = Filters.and(filter1, filter2);
        Bson filter6 = Filters.and(filter3, filter4);
        Bson filter7 = Filters.and(filter5, filter6);
        return getCollection().find(filter7, Room.class).first();
    }

}
