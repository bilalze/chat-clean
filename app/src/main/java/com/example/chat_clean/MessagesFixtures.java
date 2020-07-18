package com.example.chat_clean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by troy379 on 12.12.16.
 */
public final class MessagesFixtures extends FixturesData {
    private MessagesFixtures() {
        throw new AssertionError();
    }

    public static Message getImageMessage() {
        Message message = new Message("1", getUser("1"), null);
        message.setImage(new Message.Image(getRandomImage()));
        return message;
    }

    public static Message getImageMessage2() {
        Message message = new Message("1", getUser("1"), "im fine");
        message.setImage(new Message.Image("https://pbs.twimg.com/media/DAd_BS7XsAIVZoG.jpg"));
        return message;
    }

//    public static Message getVoiceMessage() {
//        Message message = new Message(getRandomId(), getUser(), null);
//        message.setVoice(new Message.Voice("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3", rnd.nextInt(200) + 30));
//        return message;
//    }
    public static Message getButton() {
        Message message = new Message("1", getUser("1"), null);
        message.setButton1(new Message.Button1("Example.com"));
        //message.se(new Message.Voice("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3", rnd.nextInt(200) + 30));
        return message;
    }
    public static Message getTextMessage() {
        return getTextMessage(getRandomMessage());
    }

    public static Message getTextMessage(String text) {
        return new Message("1", getUser("1"), text);
    }

    public static ArrayList<Message> getMessages(Date startDate) {
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10/*days count*/; i++) {
            int countPerDay = rnd.nextInt(5) + 1;

            for (int j = 0; j < countPerDay; j++) {
                Message message;
                if (i % 2 == 0 && j % 3 == 0) {
                    message = getImageMessage();
                } else {
                    message = getTextMessage();
                }

                Calendar calendar = Calendar.getInstance();
                if (startDate != null) calendar.setTime(startDate);
                calendar.add(Calendar.DAY_OF_MONTH, -(i * i + 1));

                message.setCreatedAt(calendar.getTime());
                messages.add(message);
            }
        }
        return messages;
    }

    private static User getUser(String id) {
        //boolean even = rnd.nextBoolean();
        return new User(
                id,
                 names.get(Integer.parseInt(id)),
                avatars.get(Integer.parseInt(id)),
                true);
    }
}
