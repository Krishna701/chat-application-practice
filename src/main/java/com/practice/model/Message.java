package com.practice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {
    String sentBy;
    String messageReceived;
    String messageSentTime;
}

