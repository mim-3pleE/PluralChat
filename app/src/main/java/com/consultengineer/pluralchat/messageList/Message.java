package com.consultengineer.pluralchat.messageList;

public class Message {
    public String message;
    public  String createdAt;
    public String sendersName;
    public boolean isSent;

    public Message(String message, String createdAt, String sendersName,
                              boolean isSent) {
        this.message = message;
        this.createdAt = createdAt;
        this.sendersName = sendersName;
        this.isSent = isSent;
    }
}
