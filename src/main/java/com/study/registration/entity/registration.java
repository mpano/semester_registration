package com.study.registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registration")
public class registration {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "student_name")
    private String student_name;

    @Column(name = "total_credits")
    private int credits;

    @Column(name = "courses")
    private int course;

    @Column(name = "total_amount")
    private int amount;

    public registration(int id, String sudent_name, int credits, int course, int amount) {
        this.id = id;
        this.student_name = sudent_name;
        this.credits = credits;
        this.course = course;
        this.amount = amount;
    }


    public registration() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSudent_name() {
        return student_name;
    }

    public void setSudent_name(String sudent_name) {
        this.student_name = sudent_name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "registration{" +
                "id=" + id +
                ", sudent_name='" + student_name + '\'' +
                ", credits=" + credits +
                ", course=" + course +
                ", amount=" + amount +
                '}';
    }
}
