package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "worker")
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker {
    @XmlAttribute
    private boolean marry;
    @XmlAttribute
    private int age;
    private Contact contact;
    private String[] statuses;

    public Worker() {
    }

    public Worker(boolean marry, int age, Contact contact, String[] statuses) {
        this.marry = marry;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    public boolean isMarry() {
        return marry;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
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

    public static void main(String[] args) throws JAXBException, IOException {

        final Worker person = new Worker(false, 30, new Contact(11, "4578",
                "blabla@bla.com"),
                new String[]{"Head of department", "Department of IT"});
        JAXBContext context = JAXBContext.newInstance(Worker.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        final Gson gson = new GsonBuilder().create();
        final String workerGson = gson.toJson(person);
        System.out.println(workerGson);
        final Worker personDeser = gson.fromJson(workerGson, Worker.class);
        System.out.println(personDeser);
        final String WorkerJson =
                "{"
                        + "\"marry\":true,"
                        + "\"age\":19,"
                        + "\"contact\":"
                        + "{"
                        + "\"zipCode\":495,"
                        + "\"phone\":\"11-11\","
                        + "\"email\":\"blabla@bla.com\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Worker personMod = gson.fromJson(WorkerJson, Worker.class);
        System.out.println(personMod);


        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Worker result = (Worker) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        final Worker worker1 = new Worker(false, 150, new Contact(999, "112233",
                "WhiteBlaxk@blow.com"),
                new String[]{"Head of department", "Department of IT"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("marry", worker1.isMarry());
        jsonObject.put("age", worker1.getAge());
        jsonObject.put("contact", worker1.getContact());
        jsonObject.put("statuses", worker1.getStatuses());

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(worker1).toString());
    }
}
