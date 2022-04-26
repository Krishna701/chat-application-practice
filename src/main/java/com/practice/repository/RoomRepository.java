package com.practice.repository;

import com.practice.model.Room;

public interface RoomRepository {

    Room getRoom(String senderId, String receiverId);
}
