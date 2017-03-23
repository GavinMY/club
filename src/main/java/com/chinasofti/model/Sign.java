package com.chinasofti.model;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Sign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="sign"
    ,catalog="club"
)

public class Sign  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer userid;
     private Integer activeid;
     private Timestamp signtime;
     private String signaddress;
     private Integer approveuserid;
     private Timestamp approvetime;
     private Integer status;


    // Constructors

    /** default constructor */
    public Sign() {
    }

	/** minimal constructor */
    public Sign(Integer userid) {
        this.userid = userid;
    }
    
    /** full constructor */
    public Sign(Integer userid, Integer activeid, Timestamp signtime, String signaddress, Integer approveuserid, Timestamp approvetime, Integer status) {
        this.userid = userid;
        this.activeid = activeid;
        this.signtime = signtime;
        this.signaddress = signaddress;
        this.approveuserid = approveuserid;
        this.approvetime = approvetime;
        this.status = status;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="userid", nullable=false)

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    
    @Column(name="activeid")

    public Integer getActiveid() {
        return this.activeid;
    }
    
    public void setActiveid(Integer activeid) {
        this.activeid = activeid;
    }
    
    @Column(name="signtime", length=19)

    public Timestamp getSigntime() {
        return this.signtime;
    }
    
    public void setSigntime(Timestamp signtime) {
        this.signtime = signtime;
    }
    
    @Column(name="signaddress", length=500)

    public String getSignaddress() {
        return this.signaddress;
    }
    
    public void setSignaddress(String signaddress) {
        this.signaddress = signaddress;
    }
    
    @Column(name="approveuserid")

    public Integer getApproveuserid() {
        return this.approveuserid;
    }
    
    public void setApproveuserid(Integer approveuserid) {
        this.approveuserid = approveuserid;
    }
    
    @Column(name="approvetime", length=19)

    public Timestamp getApprovetime() {
        return this.approvetime;
    }
    
    public void setApprovetime(Timestamp approvetime) {
        this.approvetime = approvetime;
    }
    
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   








}