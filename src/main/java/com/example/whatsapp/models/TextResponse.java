package com.example.whatsapp.models;

import java.util.ArrayList;

public class TextResponse {
    public String object;
    public ArrayList<Entry> entry;
}

public class Button{
    public String text;
    public String payload;
}

public class Change{
    public Value value;
    public String field;
}

public class Contact{
    public Profile profile;
    public String wa_id;
}

public class Context{
    public String from;
    public String id;
}

public class Entry{
    public String id;
    public ArrayList<Change> changes;
}

public class Message{
    public Context context;
    public String from;
    public String id;
    public String timestamp;
    public String type;
    public Button button;
}

public class Metadata{
    public String display_phone_number;
    public String phone_number_id;
}

public class Profile{
    public String name;
}

public class Value{
    public String messaging_product;
    public Metadata metadata;
    public ArrayList<Contact> contacts;
    public ArrayList<Message> messages;
}
