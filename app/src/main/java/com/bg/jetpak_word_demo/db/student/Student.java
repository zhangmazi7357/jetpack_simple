package com.bg.jetpak_word_demo.db.student;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int id;


    private int student_number;


    public Student(int student_number) {
        this.student_number = student_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_number() {
        return student_number;
    }

    public void setStudent_number(int student_number) {
        this.student_number = student_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id &&
                student_number == student.student_number;
    }


}
