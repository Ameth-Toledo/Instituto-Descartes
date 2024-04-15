package com.toledo.control.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty name;
    private final StringProperty firstName;
    private final StringProperty matricula;

    public Student(String name, String firstName, String matricula) {
        this.name = new SimpleStringProperty(name);
        this.firstName = new SimpleStringProperty(firstName);
        this.matricula = new SimpleStringProperty(matricula);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty matriculaProperty() {
        return matricula;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setMatricula(String matricula) {
        this.matricula.set(matricula);
    }

    public String getMatricula() {
        return matricula.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", matricula=" + matricula +
                '}';
    }
}
