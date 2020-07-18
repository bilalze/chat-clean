package com.example.chat_clean;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.MessageContentType;

import java.util.Date;

/*
 * this is message object class
 */
public class Message implements IMessage,
        MessageContentType.Image, /*this is for default image messages implementation*/
        MessageContentType /*and this one is for custom content type (in this case - button message)*/ {

    private String id;
    private String text;
    private Date createdAt;
    private User user;
    private Image image;
    private Voice voice;
    private Button1 button1;

    public Message(String id, User user, String text) {
        this(id, user, text, new Date());
    }

    public Message(String id, User user, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public String getImageUrl() {
        return image == null ? null : image.url;
    }



    public Voice getVoice() {
        return voice;
    }



    public Button1 getButton1() {
        return button1;
    }

    public String getStatus() {
        return "Sent";
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setImage(Image image) {
        this.image = image;
    }




    public void setVoice(Voice voice) {
        this.voice = voice;
    }



    public void setButton1(Button1 button1) {
        this.button1 = button1;
    }




    public static class Image {

        private String url;

        public Image(String url) {
            this.url = url;
        }
    }




    public static class Voice {

        private String url;
        private int duration;

        public Voice(String url, int duration) {
            this.url = url;
            this.duration = duration;
        }

        public String getUrl() {
            return url;
        }

        public int getDuration() {
            return duration;
        }
    }





    public static class Button1 {

        private String url;
        //private Drawable drawable;
        //private String text;

       // public Button1(String url, Drawable drawable,String text) {
            public Button1(String url) {
            this.url = url;
            //this.drawable = drawable;
            //this.text=text;
        }

        public String getUrl() {
            return url;
        }
//        public String getText() {
//            return text;
//        }
//
//        public Drawable getDrawable() {
//            return drawable;
//        }

    }
}
