package com.codegym.model;

import org.hibernate.annotations.Parent;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String employeeId;

    @NotEmpty
    @Size(min = 2, max = 30)
    @Pattern(regexp="^[A-Za-z]*$", message = "\n" + "Must be words")
    private String name;


    private LocalDate dateOfBirth;

    @NotEmpty
    private String gender;

    @NotEmpty
    @Pattern(regexp="^[0-9]*$", message = "\n" + "Must start with 0")
    private String numberPhone;

    @NotEmpty
    private String personId;

    @NotEmpty
    @Pattern(regexp="(^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(.[A-Za-z0-9]+)$)", message = "\n" + "Wrong format")
    private String email;

    @NotEmpty
    private String address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    }
