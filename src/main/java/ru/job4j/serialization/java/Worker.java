package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Worker {

    private final boolean marry;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public Worker(boolean marry, int age, Contact contact, String[] statuses) {
        this.marry = marry;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Worker{"
                + "marry=" + marry
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {

        final Worker person = new Worker(false, 30, new Contact(11, "4578",
                    "blabla@bla.com"),
                new String[]{"Head of department", "Department of IT"});
        final Gson gson = new GsonBuilder().create();
        final String workerGson = gson.toJson(person);
        System.out.println(workerGson);
        final Worker personDeser = gson.fromJson(workerGson, Worker.class);
        System.out.println(personDeser);
        final String personJson =
                    "{"
                            + "\"marry\":true,"
                            + "\"age\":19,"
                            + "\"contact\":"
                            + "{"
                            + "\"zipCode\":495,"
                            + "\"phone\":\"+7(495)111-111-11-11\""
                            + "},"
                            + "\"statuses\":"
                            + "[\"Student\",\"Free\"]"
                            + "}";
            final Worker personMod = gson.fromJson(personJson, Worker.class);
            System.out.println(personMod);
    }
}
