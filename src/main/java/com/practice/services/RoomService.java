package com.practice.services;

import com.practice.model.Room;
import com.practice.repository.RoomRepository;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public String getRoomId(String receiverId, String senderId){
        Room room = roomRepository.getRoom(receiverId, senderId);
        return room.getRoomId();
    }
////    public boolean isValidChatRoom(String senderId, String receiverId,String roomId){
////        Room room = roomRepository.getRoom(senderId, receiverId);
////        if(roomId.equals(room.getRoomId())){
////
////        }
////        return false;
////    }
    public boolean isValidRoomId(String senderId, String receiverId, String roomId){
        Room room = roomRepository.getRoom(senderId, receiverId);
        if(roomId.equals(room.getRoomId())){
            return true;
        }
        else
        {
            return false;
        }
    }
}
