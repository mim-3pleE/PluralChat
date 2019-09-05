package com.consultengineer.pluralchat;

public class ChatListItem {
    public String contactName;
    public String chatSnippet;
    public String contactIconUrl;
    public String chatTime;


    public ChatListItem(String contactName, String chatSnippet, String chatTime) {
        this.contactName = contactName;
        this.chatSnippet = chatSnippet;
        this.chatTime = chatTime;
    }
}
