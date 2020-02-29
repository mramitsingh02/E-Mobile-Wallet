package com.tester.spring.rest.webservices.repository.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "User Details")
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @ApiModelProperty(notes = "User Id should be Unique number")
    private Long userId;

    @Size(min = 2, max = 20, message = "Name should be min 2 Character and max 20 Character")
    @ApiModelProperty(notes = "Name should be min 2 Character and max 20 Character")
    private String name;

    @Email
    @ApiModelProperty(notes = "Should be email type.")
    private String email;

    @Past(message = "Date Of Birth should be past.")
    @ApiModelProperty(notes = "Date Of Birth should be past.")
    private Date dob;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    public User() {
    }

    public User(String name, Long userId, Date dob) {
        this.name = name;
        this.userId = userId;
        this.dob = dob;
    }

    public User(Long userId, String name, String email, Date dob) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", postList=" + postList +
                '}';
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
