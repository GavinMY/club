package com.chinasofti.model;
// default package

import java.sql.Timestamp;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * Activity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="activity"
    ,catalog="club"
)

public class Activity  implements java.io.Serializable {


    // Fields    

     private String id;
     private String subject;
     private Timestamp startTime;
     private Timestamp endTime;
     private String venue;
     private String banner;
     private String briefContent;
     private String description;
     private Integer limitNum;
     private Integer joinType;
     private Timestamp deadline;
     private Integer status;
     private String createUserid;
     private Timestamp createTime;
     private String editUserid;
     private Timestamp editTime;
     private String reviewUserid;
     private Timestamp reviewTime;
     private String enrollNeed;
     private String videoPath;
     private String role;
     private Integer pushCount;
     private String push;
     private Timestamp submitTime;
     private String submitUserId;
     private String pushInfo;
     private Timestamp firstPublishTime;
     private String imgpath;
     private Integer csHour;
     private String ic;
     private String isUpload;
     private String updateStatus;
     private String confirmStatus;


    // Constructors

    /** default constructor */
    public Activity() {
    }

	/** minimal constructor */
    public Activity(String subject) {
        this.subject = subject;
    }
    
    /** full constructor */
    public Activity(String subject, Timestamp startTime, Timestamp endTime, String venue, String banner, String briefContent, String description, Integer limitNum, Integer joinType, Timestamp deadline, Integer status, String createUserid, Timestamp createTime, String editUserid, Timestamp editTime, String reviewUserid, Timestamp reviewTime, String enrollNeed, String videoPath, String role, Integer pushCount, String push, Timestamp submitTime, String submitUserId, String pushInfo, Timestamp firstPublishTime, String imgpath, Integer csHour, String ic, String isUpload, String updateStatus, String confirmStatus) {
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.banner = banner;
        this.briefContent = briefContent;
        this.description = description;
        this.limitNum = limitNum;
        this.joinType = joinType;
        this.deadline = deadline;
        this.status = status;
        this.createUserid = createUserid;
        this.createTime = createTime;
        this.editUserid = editUserid;
        this.editTime = editTime;
        this.reviewUserid = reviewUserid;
        this.reviewTime = reviewTime;
        this.enrollNeed = enrollNeed;
        this.videoPath = videoPath;
        this.role = role;
        this.pushCount = pushCount;
        this.push = push;
        this.submitTime = submitTime;
        this.submitUserId = submitUserId;
        this.pushInfo = pushInfo;
        this.firstPublishTime = firstPublishTime;
        this.imgpath = imgpath;
        this.csHour = csHour;
        this.ic = ic;
        this.isUpload = isUpload;
        this.updateStatus = updateStatus;
        this.confirmStatus = confirmStatus;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="id", unique=true, nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="subject", nullable=false, length=200)

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Column(name="start_time", length=19)

    public Timestamp getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    
    @Column(name="end_time", length=19)

    public Timestamp getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    
    @Column(name="venue", length=500)

    public String getVenue() {
        return this.venue;
    }
    
    public void setVenue(String venue) {
        this.venue = venue;
    }
    
    @Column(name="banner", length=300)

    public String getBanner() {
        return this.banner;
    }
    
    public void setBanner(String banner) {
        this.banner = banner;
    }
    
    @Column(name="brief_content", length=500)

    public String getBriefContent() {
        return this.briefContent;
    }
    
    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }
    
    @Column(name="description", length=65535)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="limitNum")

    public Integer getLimitNum() {
        return this.limitNum;
    }
    
    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }
    
    @Column(name="join_type")

    public Integer getJoinType() {
        return this.joinType;
    }
    
    public void setJoinType(Integer joinType) {
        this.joinType = joinType;
    }
    
    @Column(name="deadline", length=19)

    public Timestamp getDeadline() {
        return this.deadline;
    }
    
    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }
    
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="create_userid", length=32)

    public String getCreateUserid() {
        return this.createUserid;
    }
    
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }
    
    @Column(name="create_time", length=19)

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="edit_userid", length=32)

    public String getEditUserid() {
        return this.editUserid;
    }
    
    public void setEditUserid(String editUserid) {
        this.editUserid = editUserid;
    }
    
    @Column(name="edit_time", length=19)

    public Timestamp getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }
    
    @Column(name="review_userid", length=32)

    public String getReviewUserid() {
        return this.reviewUserid;
    }
    
    public void setReviewUserid(String reviewUserid) {
        this.reviewUserid = reviewUserid;
    }
    
    @Column(name="review_time", length=19)

    public Timestamp getReviewTime() {
        return this.reviewTime;
    }
    
    public void setReviewTime(Timestamp reviewTime) {
        this.reviewTime = reviewTime;
    }
    
    @Column(name="enroll_need", length=65535)

    public String getEnrollNeed() {
        return this.enrollNeed;
    }
    
    public void setEnrollNeed(String enrollNeed) {
        this.enrollNeed = enrollNeed;
    }
    
    @Column(name="video_path")

    public String getVideoPath() {
        return this.videoPath;
    }
    
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
    
    @Column(name="role")

    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    @Column(name="push_count")

    public Integer getPushCount() {
        return this.pushCount;
    }
    
    public void setPushCount(Integer pushCount) {
        this.pushCount = pushCount;
    }
    
    @Column(name="push", length=10)

    public String getPush() {
        return this.push;
    }
    
    public void setPush(String push) {
        this.push = push;
    }
    
    @Column(name="submit_time", length=19)

    public Timestamp getSubmitTime() {
        return this.submitTime;
    }
    
    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }
    
    @Column(name="submit_user_id", length=40)

    public String getSubmitUserId() {
        return this.submitUserId;
    }
    
    public void setSubmitUserId(String submitUserId) {
        this.submitUserId = submitUserId;
    }
    
    @Column(name="pushInfo")

    public String getPushInfo() {
        return this.pushInfo;
    }
    
    public void setPushInfo(String pushInfo) {
        this.pushInfo = pushInfo;
    }
    
    @Column(name="first_publish_time", length=19)

    public Timestamp getFirstPublishTime() {
        return this.firstPublishTime;
    }
    
    public void setFirstPublishTime(Timestamp firstPublishTime) {
        this.firstPublishTime = firstPublishTime;
    }
    
    @Column(name="imgpath", length=100)

    public String getImgpath() {
        return this.imgpath;
    }
    
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
    
    @Column(name="cs_hour")

    public Integer getCsHour() {
        return this.csHour;
    }
    
    public void setCsHour(Integer csHour) {
        this.csHour = csHour;
    }
    
    @Column(name="ic", length=20)

    public String getIc() {
        return this.ic;
    }
    
    public void setIc(String ic) {
        this.ic = ic;
    }
    
    @Column(name="is_upload", length=1)

    public String getIsUpload() {
        return this.isUpload;
    }
    
    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }
    
    @Column(name="update_status", length=1)

    public String getUpdateStatus() {
        return this.updateStatus;
    }
    
    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }
    
    @Column(name="confirm_status", length=1)

    public String getConfirmStatus() {
        return this.confirmStatus;
    }
    
    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }
   








}