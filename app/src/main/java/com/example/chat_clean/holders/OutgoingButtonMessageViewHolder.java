package com.example.chat_clean.holders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.stfalcon.chatkit.messages.MessageHolders;
import com.example.chat_clean.Message;
import com.example.chat_clean.R;
import com.stfalcon.chatkit.utils.DateFormatter;

/*
 * Created by troy379 on 05.04.17.
 */
public class OutgoingButtonMessageViewHolder
        extends MessageHolders.IncomingTextMessageViewHolder<Message> {

    private TextView Text1;
    private ImageView Image1;
    private TextView tvTime;
    public Button button1;

    public OutgoingButtonMessageViewHolder(View itemView, Object payload) {
        super(itemView, payload);
        Text1 = (TextView) itemView.findViewById(R.id.messageText);
        Image1 = (ImageView) itemView.findViewById(R.id.image);
        tvTime = (TextView) itemView.findViewById(R.id.messageTime);
        button1=(Button) itemView.findViewById(R.id.button1);
    }

    @Override
    public void onBind(Message message) {
        super.onBind(message);
        Text1.setText("How you doin?");

        tvTime.setText(DateFormatter.format(message.getCreatedAt(), DateFormatter.Template.TIME));
    }

}
