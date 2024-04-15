package com.toledo.control.models;

import java.util.ArrayList;

public class Access implements DataBase {
    private ArrayList<Student> students = new ArrayList<>();

    @Override
    public boolean addStudent(ArrayList<Student> students) {
        return this.students.addAll(students);
    }

    @Override
    public void saveStudent(Student student) {
        students.add(student);
    }

    @Override
    public void updateStudent(Student student) {
        for (Student s : students) {
            if (s.getMatricula().equals(student.getMatricula())) {
                s.setName(student.getName());
                s.setFirstName(student.getFirstName());
                break;
            }
        }
    }
}
