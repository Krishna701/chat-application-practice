package com.practice.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.practice.model.User;
import jakarta.inject.Singleton;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.conversions.Bson;

import java.util.ArrayList;
@Singleton
public class MongoUserRepository implements UserRepository {
    private final MongoClient db;

    public MongoUserRepository(MongoClient db) {
        this.db = db;
    }
    private MongoDatabase getDb(){
        return db.getDatabase("chatDB");
    }
    private MongoCollection<User> getCollection(){
        return getDb().getCollection("userCollection", User.class);
}

@Override
public User getUser(String userId){
    Bson filter = Filters.eq("user_id",userId);
    return getCollection().find(filter,User.class).first();
    }

    @Override
    public boolean exists(String userName) {
        Bson filter =Filters.eq("userName",userName);
        ArrayList<User> users = getCollection().find(filter, User.class).into(new ArrayList<>());
        return !users.isEmpty();
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = getCollection().find(User.class).into(new ArrayList<>());
        return users;
    }

    @Override
    public String addUser(String userName) {
        User user = new User("",userName);
        InsertOneResult result = getCollection().insertOne(user);
        String id = result.getInsertedId().asString().getValue();
        user.setUserId(id);

        Bson updateFilter = Filters.eq("_id", id);
        BsonValue value = new BsonString(id);
        getCollection().updateOne(updateFilter, new BsonDocument("password",value));
        return id;
    }

    @Override
    public boolean existsUser(User user) {
        Bson filter1 = Filters.eq("_id", user.getUserId());
        Bson filter2 = Filters.eq("userName", user.getUserName());
        Bson filter = Filters.and(filter1, filter2);
        User getUser = getCollection().find(filter,User.class).first();
        if(getUser != null) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public ArrayList<User> getAllUsersExcept(String myId) {
        User thisUser = getUser(myId);
        ArrayList<User> users = getCollection().find(User.class).into(new ArrayList<>());
        users.remove(thisUser); // why are we doing this needs clarifications
        return users;
    }

    @Override
    public boolean UpdateUser(String userId, String newUserName) {
        Bson updateFilter = Filters.eq("_id", userId);
        BsonValue value = new BsonString(newUserName);
        UpdateResult result = getCollection().updateOne(updateFilter, new BsonDocument("userName", value));
        return result.wasAcknowledged();

    }

    @Override
    public boolean DeleteUser(String userId) {
        Bson deleteFilter = Filters.eq("_id", userId);
        DeleteResult result = getCollection().deleteOne(deleteFilter);
        return result.wasAcknowledged();
    }


}
