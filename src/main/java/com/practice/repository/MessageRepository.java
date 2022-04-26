package com.practice.repository;

import com.practice.model.Message;

import java.util.List;

public interface MessageRepository {
    List<Message> getMessage(String roomId);

    void saveMessage(String sentBy, String receivedMessage, String sentTime);
}
