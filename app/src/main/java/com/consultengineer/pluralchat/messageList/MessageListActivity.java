package com.consultengineer.pluralchat.messageList;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.consultengineer.pluralchat.R;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    public static final String EXTRA_CONTACT_NAME = "MessageListActivity.contactName";
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageListAdapter;
    private List<Message> mMessageList = new ArrayList<>();
    private String sender;
    private int mPICTURE_ALLOW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        this.sender = getIntent().getStringExtra(MessageListActivity.EXTRA_CONTACT_NAME);
        
        setTitle(sender);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMessageRecycler = findViewById(R.id.reyclerview_message_list);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageListAdapter = new MessageListAdapter(this, mMessageList);
        mMessageRecycler.setAdapter(mMessageListAdapter);
        
        updateMessageList();
        
        findViewById(R.id.button_take_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
        findViewById(R.id.button_send_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLocation();
            }
        });
        
    }

    private void sendLocation() {
        requestLocationPermissions();
    }

    private void requestLocationPermissions() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED)
            return;



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==101){
            if(grantResults.length > 0  && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
            }else {

            }

        }


    }

    private void shoulsShowPermissionRationale(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
        PackageManager.PERMISSION_GRANTED){
            return;
        }

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            showPermissionRationale();
        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    101);
        }

    }
    private void  showPermissionRationale(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Location permission explanation")
                .setMessage("PluralChat needs the location permission to send your location to your friends")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MessageListActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                101);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mPICTURE_ALLOW = 100;
        startActivityForResult(intent, mPICTURE_ALLOW);
    }

    private void updateMessageList() {
        Message temp = new Message("Hiiii", "12:10pm", this.sender ,false);
        mMessageList.add(temp);
        temp = new Message("Hey", "12:11pm", this.sender ,true);
        mMessageList.add(temp);
        temp = new Message("What's the plan?", "12:14pm", this.sender ,false);
        mMessageList.add(temp);
        temp = new Message("You are coming to the party right?", "12:14pm", this.sender ,false);
        mMessageList.add(temp);
        temp = new Message("Yep! I am in", "12:20pm", this.sender ,true);
        mMessageList.add(temp);
        temp = new Message("Cool, Cya at my place then", "12:22pm", this.sender ,false);
        mMessageList.add(temp);
        temp = new Message("Same time", "12:30pm", this.sender ,false);
        mMessageList.add(temp);
        temp = new Message("Cya", "12:35pm", this.sender ,true);
        mMessageList.add(temp);

        mMessageListAdapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
               return super.onOptionsItemSelected(item);

        }

    }
}
