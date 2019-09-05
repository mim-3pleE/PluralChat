package com.consultengineer.pluralchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends ArrayAdapter<ChatListItem> {
    private Context context;
    private ArrayList<ChatListItem> chatListItems;

    public ChatListAdapter(Context context, int resource,  ArrayList<ChatListItem> chatList) {
        super(context, resource, chatList);
        this.context = context;
        this.chatListItems = chatList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chat_list_item, parent, false);

        TextView contactName = rowView.findViewById(R.id.contactName);
        TextView chatSnippert = rowView.findViewById(R.id.chatSnippet);
        TextView chatTime = rowView.findViewById(R.id.chatTime);
        ChatListItem chatListItem = chatListItems.get(position);

        contactName.setText(chatListItem.contactName);
        chatSnippert.setText(chatListItem.chatSnippet);
        chatTime.setText(chatListItem.chatTime);

        return rowView;
    }

}
