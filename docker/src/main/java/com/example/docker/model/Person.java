package com.example.docker.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;
@XmlRootElement
@XmlType(propOrder = { "personId", "name", "birthDay" })
public class Person implements Serializable {
    private int personId;
    private String name;
    private Date birthDay;

    public Person() {

    }

    public Person(int personId, String name, Date birthDay) {
        this.personId = personId;
        this.name = name;
        this.birthDay = birthDay;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
