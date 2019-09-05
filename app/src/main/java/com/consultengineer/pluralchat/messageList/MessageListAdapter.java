package com.consultengineer.pluralchat.messageList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultengineer.pluralchat.R;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter{
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context mContext;
    private List<Message> mMessageList;

    public MessageListAdapter(Context context, List<Message> messages){
        mContext = context;
        mMessageList = messages;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = mMessageList.get(position);
        if(message.isSent){
            return VIEW_TYPE_MESSAGE_SENT;
        }else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        if(i== VIEW_TYPE_MESSAGE_SENT){
            view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.item_message_sent, viewGroup, false);
            return new SentMessageHolder(view);
        }else if(i==VIEW_TYPE_MESSAGE_RECEIVED){
            view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.item_message_received, viewGroup, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Message message = mMessageList.get(i);

        switch (viewHolder.getItemViewType()){
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder)viewHolder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder)viewHolder).bind(message);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        public ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
            nameText = itemView.findViewById(R.id.text_message_name);
            profileImage = itemView.findViewById(R.id.image_message_profile);
        }

        void bind(Message message){
            messageText.setText(message.message);
            timeText.setText(message.createdAt);
            nameText.setText(message.sendersName);
        }
    }

    private class SentMessageHolder extends  RecyclerView.ViewHolder{
        TextView messageText, timeText;

        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
        }

        void bind(Message message){
            messageText.setText(message.message);
            timeText.setText(message.createdAt);
        }
    }


}
