package com.chinasofti.model;
// default package

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * Activity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="activity"
    ,catalog="club"
)

public class Activity  implements Serializable {


    // Fields    

     private String id;
     private String content;
     private Timestamp startTime;
     private Timestamp endTime;
     private String remark;
     private String createUserid;
     private Timestamp createTime;
     private Integer status;
     private String address;
    // Property accessors
    @Id @GeneratedValue    
    @Column(name="id", unique=true, nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    @NotNull(message ="startTime is empty")  
    @Column(name="startime", length=19)
    public Timestamp getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    @NotNull(message = "endTime is empty")
    @Column(name="endtime", length=19)
    public Timestamp getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }   
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    @NotNull(message="createUserid is empty")
    @Column(name="createuserid", length=32)
    public String getCreateUserid() {
        return this.createUserid;
    }
    
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }
    
    @Column(name="createtime", length=19)

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @NotNull(message="content is empty")
    @Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	 @Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@NotNull(message="address is  empty")
	 @Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
      
	public Activity(String id, String content, Timestamp startTime, Timestamp endTime, String remark,
			String createUserid, Timestamp createTime, Integer status) {
		super();
		this.id = id;
		this.content = content;
		this.startTime = startTime;
		this.endTime = endTime;
		this.remark = remark;
		this.createUserid = createUserid;
		this.createTime = createTime;
		this.status = status;
	}

	public Activity() {
		super();
	}   
}