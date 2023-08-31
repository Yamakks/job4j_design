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

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Contact{"
               + "zipCode=" + zipCode
                + ", phone='" + phone
                + ", email='" + email + '\''
                + '}';
    }
}
