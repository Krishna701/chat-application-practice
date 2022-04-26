package com.practice.services;


import com.practice.model.Message;
import com.practice.repository.MessageRepository;

import java.util.List;

public class MessageService
{
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessage(String roomId) {
        return messageRepository.getMessage(roomId);
    }
    public void saveMessage(String sentBy, String receivedMessage, String sentTime) {
        messageRepository.saveMessage(sentBy, receivedMessage, sentTime);
    }
}
