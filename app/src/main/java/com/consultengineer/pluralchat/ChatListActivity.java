package com.consultengineer.pluralchat;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.consultengineer.pluralchat.messageList.MessageListActivity;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private ListView mListView;
    private ChatListAdapter mListAdapter;
    private ArrayList<ChatListItem> mChatListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        mListView= findViewById(R.id.chat_list);
        mListAdapter = new ChatListAdapter(this, -1, mChatListItems);
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(this);
        updateChatList();
    }

    private void updateChatList() {
        ChatListItem temp = new ChatListItem("Harry", "How are you son?", "9:30pm");
        mChatListItems.add(temp);
        temp = new ChatListItem("Doakes", "See you at the cabin", "8:00pm");
        mChatListItems.add(temp);
        temp = new ChatListItem("Dexter", "I am out on my boat", "12:30am");
        mChatListItems.add(temp);
        temp = new ChatListItem("Debra", "Want to grab a beer", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Morgan", "What's up bro", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Rita", "My car broke down", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Codey", "Where are my doughnuts", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Masuka", "Exciting stuff man", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Batista", "Wanna go bowling", "yesterday");
        mChatListItems.add(temp);

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestReadContactsPermissions();
    }

    private void requestReadContactsPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_SMS}, 100);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MessageListActivity.class);
        intent.putExtra(MessageListActivity.EXTRA_CONTACT_NAME,
                mChatListItems.get(position).contactName);
        startActivity(intent);

    }
}
