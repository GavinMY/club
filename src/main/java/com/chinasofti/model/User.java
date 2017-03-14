package com.chinasofti.model;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user"
    ,catalog="club"
)

public class User  implements java.io.Serializable {


    // Fields    

     private Integer userId;
     private String userName;
     private String password;
     private Timestamp createTime;
     private Integer state;
     private String position;
     private String email;
     private Timestamp loginTime;
     private String userType;
     private String role;


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String userName, String password, Timestamp createTime, Integer state, String position, String email, Timestamp loginTime, String userType, String role) {
        this.userName = userName;
        this.password = password;
        this.createTime = createTime;
        this.state = state;
        this.position = position;
        this.email = email;
        this.loginTime = loginTime;
        this.userType = userType;
        this.role = role;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="user_id", unique=true, nullable=false, length=11)

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Column(name="user_name", length=10)

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="password", length=100)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="create_time", length=19)

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="state")

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    @Column(name="position", length=10)

    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    @Column(name="email")

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="login_time", length=19)

    public Timestamp getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
    
    @Column(name="user_type", length=40)

    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Column(name="role", length=200)

    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
   








}