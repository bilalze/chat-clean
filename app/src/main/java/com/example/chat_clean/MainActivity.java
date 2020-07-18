package com.example.chat_clean;


import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.colorgreen.swiper.OnSwipeTouchListener;
import com.colorgreen.swiper.SwipeAction;
import com.colorgreen.swiper.SwipeActionListener;
import com.example.chat_clean.holders.IncomingButtonMessageViewHolder;
import com.example.chat_clean.holders.OutgoingButtonMessageViewHolder;
import com.example.chat_clean.utils.AppUtils;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends DemoMessagesActivity
        implements MessageInput.InputListener,
        MessageInput.AttachmentsListener,
        DialogInterface.OnClickListener ,
        MessageHolders.ContentChecker<Message>,
        MessageInput.TypingListener{

    public static void open(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private MessagesList messagesList;
    private int _yDelta;
    private GestureDetector mDetector;
    private  View view1;
    private int positioner1=0;
    private ImageView imgs;
    private RelativeLayout imgl;
    private MediaPlayer mp;
    private int check=0;
    private static final byte CONTENT_TYPE_BUTTON = 5;
    private SwipeAction swipeAction;

    private boolean isViewContains(View view, int rx, int ry) {
        int[] l = new int[2];
        view.getLocationOnScreen(l);
        int x = l[0];
        int y = l[1];
        int w = view.getWidth();
        int h = view.getHeight();
        Log.d("floatt1",""+y);
        Log.d("floatt2",""+(y-h));
        Log.d("floatt3",""+ry);

        if ( ry > y || ry < y - h*1.5) {
            Log.d("floatt","false");
            return false;
        }
        Log.d("floatt","true");
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final OnSwipeTouchListener listener = new OnSwipeTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Log.d("floatV",""+x);
                Log.d("floatV1",""+y);
                if(isViewContains(findViewById(R.id.separator),x,y)) {
                   check=1;
                }
                if (check==1) {
                    return super.onTouch(v, event);
                }
                return false;
            }
        };
        findViewById(R.id.separator).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                findViewById(R.id.separator).getViewTreeObserver().removeOnGlobalLayoutListener( this );

                int[] location = new int[2];
                findViewById(R.id.separator).getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                final int targetHeight = y;
                Log.d("TAG"," "+targetHeight);
                swipeAction = new SwipeAction();

                swipeAction.setSwipeActionListener(new SwipeActionListener() {
                    @Override
                    public void onDragStart(float v, float v1) {

                    }

                    @Override
                    public void onDrag(float v, float v1) {
//                        findViewById(R.id.separator).setY( v );
//                        Log.d("floatV",""+v);
//                        Log.d("floatV1",""+v1);
                        int[] location = new int[2];
                        findViewById(R.id.separator).getLocationOnScreen(location);
                        Log.d("floatloc"," "+location[1]);

                        RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
// Create LayoutParams for it // Note 200 200 is width, height in pixels
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(findViewById(R.id.chater).getWidth(), targetHeight-(int) v );
// Align bottom-right, and add bottom-margin
                        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

                        findViewById(R.id.chater).setLayoutParams(params);
//                        findViewById(R.id.chater).setLayoutParams( new RelativeLayout.LayoutParams( findViewById(R.id.chater).getWidth(), targetHeight-(int) v ) );
                        //findViewById(R.id.chater).setY( v );
                        //bar.setLayoutParams( new RelativeLayout.LayoutParams( bar.getWidth(), (int) val ) );
                        // bar.setBackgroundColor( interpolateColor( lightBlue, darkBlue, val / targetHeight ) );
                    }

                    @Override
                    public void onDragEnd(float v, float v1) {
                        check=0;


                    }
                });

                swipeAction.setDirection( SwipeAction.DragDirection.Up );
                swipeAction.setSteps( new float[]{ targetHeight, targetHeight - targetHeight * 0.3f, 0 } );
                swipeAction.setDragThreshold( 0.4f );


                listener.addAction( swipeAction );

//        mDetector = new GestureDetector(this, new MyGestureListener());
//        imgs=(ImageView)findViewById(R.id.imager);
//        imgl=(RelativeLayout)findViewById(R.id.imgl);
//        findViewById(R.id.separator).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                final int Y = (int) event.getRawY();
//                Log.d("TAG",""+Y);
//                view1=view;
//                switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                    case MotionEvent.ACTION_DOWN:
//                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
//                        _yDelta = Y - lParams.bottomMargin;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        break;
//                    case MotionEvent.ACTION_POINTER_UP:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d("TAG","Action_move");
//                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
//                        layoutParams.bottomMargin = (Y - _yDelta);
//                        layoutParams.topMargin = -layoutParams.bottomMargin;
//
//                        view.animate().translationY(Y - _yDelta).setDuration(0);
//                        view.setLayoutParams(layoutParams);
//                        findViewById(R.id.root).invalidate();
//
//                        break;
//
//                }
////                findViewById(R.id.root).invalidate();
//                return mDetector.onTouchEvent(event);
//            }
//        });

            }
        });
        findViewById(R.id.root).setOnTouchListener( listener );


        this.messagesList = (MessagesList)findViewById(R.id.messagesList) ;
        initAdapter();

        MessageInput input = (MessageInput) findViewById(R.id.input);
        input.setInputListener(this);
        input.setTypingListener(this);
        input.setAttachmentsListener(this);
    }


    @Override
    public boolean onSubmit(CharSequence input) {
        super.messagesAdapter.addToStart(
                MessagesFixtures.getTextMessage(input.toString()), true);
        return true;
    }

    @Override
    public void onAddAttachments() {
//        super.messagesAdapter.addToStart(MessagesFixtures.getImageMessage(),true);
//                messagesAdapter.addToStart(MessagesFixtures.getVoiceMessage(),true);

        new AlertDialog.Builder(this)
                .setItems(R.array.view_types_dialog, this)
                .show();

    }

    private void initAdapter() {
        MessageHolders holders = new MessageHolders()
                .registerContentType(
                        CONTENT_TYPE_BUTTON,
                        IncomingButtonMessageViewHolder.class,
                        R.layout.item_custom_incoming_button_message,
                        OutgoingButtonMessageViewHolder.class,
                        R.layout.item_custom_outgoing_button_message,
                        this);

        super.messagesAdapter = new MessagesListAdapter<>(super.senderId, holders,super.imageLoader);
        super.messagesAdapter.enableSelectionMode(this);
        super.messagesAdapter.setLoadMoreListener(this);
        super.messagesAdapter.registerViewClickListener(R.id.messageUserAvatar,
                new MessagesListAdapter.OnMessageViewClickListener<Message>() {
                    @Override
                    public void onMessageViewClick(View view, Message message) {
                        AppUtils.showToast(MainActivity.this,
                                message.getUser().getName() + " avatar click",
                                false);
                    }
                });
        this.messagesList.setAdapter(super.messagesAdapter);
        messagesAdapter.setOnMessageClickListener(new MessagesListAdapter.OnMessageClickListener<Message>() {
            @Override
            public void onMessageClick(Message message) {
/*                if(message.getImageUrl() != null){
                 imgs.setImageDrawable(LoadImageFromWebOperations(message.getImageUrl()));
                 imgl.setVisibility(View.VISIBLE);
                    positioner1=2;

                }
                else if(message.getVoice().getUrl()!=null){
                    mp = new MediaPlayer();
                    try {
                        mp.setDataSource(message.getVoice().getUrl());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        mp.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mp.start();
                }*/
            }
        });
    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public void onStartTyping() {
        Log.v("Typing listener", getString(R.string.start_typing_status));
    }

    @Override
    public void onStopTyping() {
        Log.v("Typing listener", getString(R.string.stop_typing_status));
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                messagesAdapter.addToStart(MessagesFixtures.getImageMessage(), true);
                break;
//            case 1:
//                messagesAdapter.addToStart(MessagesFixtures.getVoiceMessage(), true);
//                break;
            case 1:
                messagesAdapter.addToStart(MessagesFixtures.getButton(), true);
                break;
        }
    }

    @Override
    public boolean hasContentFor(Message message, byte type) {
        switch (type) {
            case CONTENT_TYPE_BUTTON:
                return message.getButton1() != null
                        && message.getButton1().getUrl() != null
                        && !message.getButton1().getUrl().isEmpty();
        }
        return false;
    }
    public void button_method(View v){

        messagesAdapter.addToStart(
                MessagesFixtures.getImageMessage2(),true);
    }
//    @Override
//    protected void onCreate(Bundle arg0) {
//        super.onCreate(arg0);
//        setContentView(R.layout.activity_main);
//        findViewById(R.id.separator).setOnTouchListener(this);
//    }



    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("TAG","onDown: ");

            // don't return false here or else none of the other
            // gestures will work
            return true;
        }

//    @Override
//    public boolean onSingleTapConfirmed(MotionEvent e) {
//        Log.i("TAG", "onSingleTapConfirmed: ");
//        return true;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//        Log.i("TAG", "onLongPress: ");
//    }
//
//    @Override
//    public boolean onDoubleTap(MotionEvent e) {
//        Log.i("TAG", "onDoubleTap: ");
//        return true;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2,
//                            float distanceX, float distanceY) {
//        Log.i("TAG", "onScroll: ");
//        return true;
//    }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d("TAG", "onFling: "+velocityX+"   "+velocityY);
//            Log.d("TAG","Action_move");
            if(velocityY<-2500) {
                final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view1.getLayoutParams();
                layoutParams.bottomMargin = (-_yDelta);
                layoutParams.topMargin = -layoutParams.bottomMargin;
                view1.animate().translationY(-_yDelta).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(500);
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view1.setLayoutParams(layoutParams);
//                        findViewById(R.id.root).invalidate();
//                    }
//                }, 500);
                view1.setLayoutParams(layoutParams);
                findViewById(R.id.root).invalidate();
                positioner1=1;
            }
            return true;
        }
    }

    @Override
    public void onBackPressed() {

//        if(positioner1==1){
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view1.getLayoutParams();
//            layoutParams.bottomMargin =300;
//            layoutParams.topMargin = -layoutParams.bottomMargin;
//            view1.setLayoutParams(layoutParams);
//            view1.animate().translationY(300).setDuration(500);
//            findViewById(R.id.root).invalidate();
//            positioner1=0;
//
//        }
//        else if(positioner1==2){
//            imgl.setVisibility(View.GONE);
//            positioner1=0;
//        }

        if(swipeAction.getStep()==2){
            swipeAction.collapse();
        }
        else {
            if (mp != null) {
                try {
                    mp.reset();
                    mp.release();
                    mp = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            super.onBackPressed();
        }
    }
}

