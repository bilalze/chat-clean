package com.example.chat_clean.holders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chat_clean.Message;
import com.example.chat_clean.R;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.utils.DateFormatter;

/*
 * Holder for the button message type have to create one for the incoming and outgoing message both are same as of now
 */
public class IncomingButtonMessageViewHolder
        extends MessageHolders.IncomingTextMessageViewHolder<Message> {
    //any text we would like to add
    private TextView Text1;
    //image to show with the button
    private ImageView Image1;
    //the time the message was sent
    private TextView tvTime;
    //the button
    public Button button1;

    public IncomingButtonMessageViewHolder(View itemView, Object payload) {
        super(itemView, payload);
        Text1 = (TextView) itemView.findViewById(R.id.messageText);
        Image1 = (ImageView) itemView.findViewById(R.id.image);
        tvTime = (TextView) itemView.findViewById(R.id.messageTime);
        button1=(Button) itemView.findViewById(R.id.button1);
    }

    @Override
    public void onBind(Message message) {
        super.onBind(message);
        //i set the text here for testing in future we can set it programatically from the server
        Text1.setText("How you doin?");

        tvTime.setText(DateFormatter.format(message.getCreatedAt(), DateFormatter.Template.TIME));
    }

}
