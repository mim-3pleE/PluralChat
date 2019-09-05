package com.consultengineer.pluralchat.messageList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class UserStatusUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        updateUserStatus();
        Toast.makeText(context, "true" +"!",Toast.LENGTH_SHORT).show();
    }

    private boolean hasPermissionToWriteChatContacts(Context c){
        PackageManager packageManager = c.getPackageManager();
        return packageManager.checkPermission("com.consultengineer.pluralchat.WRITE_CHAT_CONTACTS",
                "com.consultengineer.addChatContact") == PackageManager.PERMISSION_GRANTED;
    }

    private void updateUserStatus(){}
}
