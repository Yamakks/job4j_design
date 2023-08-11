package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.nio.file.Files;
@XmlRootElement(name = "contact")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    private int zipCode;
    @XmlAttribute
    private String phone;
    @XmlAttribute
    private String email;

    public Contact() { }
    public Contact(int zipCode, String phone, String email) {
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
               + "zipCode=" + zipCode
                + ", phone='" + phone
                + ", email='" + email + '\''
                + '}';
    }

    /** public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
     */
}
