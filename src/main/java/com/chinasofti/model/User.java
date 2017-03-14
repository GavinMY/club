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
     private String employid;
     private String department;
     private Timestamp createTime;
     private Integer state;
     private String position;
     private String approveuserid;
     private Timestamp approvetime;
     private String userType;
     private String chineseName;

    // Constructors

    /** default constructor */
    public User() {
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
    
    @Column(name="user_type", length=40)

    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    @Column(name="approveuserid")
	public String getApproveuserid() {
		return approveuserid;
	}
	public void setApproveuserid(String approveuserid) {
		this.approveuserid = approveuserid;
	}
	@Column(name="approvetime")
	public Timestamp getApprovetime() {
		return approvetime;
	}
	public void setApprovetime(Timestamp approvetime) {
		this.approvetime = approvetime;
	}
	@Column(name="employid")
	public String getEmployid() {
		return employid;
	}
	public void setEmployid(String employid) {
		this.employid = employid;
	}
	@Column(name="department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name="chineseName")
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
}