package org.henry.com.messenger.database;

import org.henry.com.messenger.model.Message;
import org.henry.com.messenger.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class Database {

    public static Map<Long, Message> messages = new HashMap<>();
    public static Map<String, Profile> profiles  = new HashMap<>();

    static {
        Message m1 = new Message(1L, "Hello World", "kafuuma");
        Message m2 = new Message(2L,"Hello Jersey", "kafuuma");
        messages.put(m1.getId(), m1);
        messages.put(m2.getId(), m2);

        profiles.put( "henry", new Profile(1L, "henry", "kafuuma", "henry"));
    }

    public static Map<Long, Message> getMessages(){
        return messages;
    }

    public static Map<String, Profile> getProfiles(){
        return profiles;
    }
}
