package com.messi.appshelper.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table CATEGORY.
 */
public class Category {

    private Long id;
    private String cid;
    private String name;
    private String createType;
    private String lastOpenTime;
    private Integer clickTimes;

    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }

    public Category(Long id, String cid, String name, String createType, String lastOpenTime, Integer clickTimes) {
        this.id = id;
        this.cid = cid;
        this.name = name;
        this.createType = createType;
        this.lastOpenTime = lastOpenTime;
        this.clickTimes = clickTimes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getLastOpenTime() {
        return lastOpenTime;
    }

    public void setLastOpenTime(String lastOpenTime) {
        this.lastOpenTime = lastOpenTime;
    }

    public Integer getClickTimes() {
        return clickTimes;
    }

    public void setClickTimes(Integer clickTimes) {
        this.clickTimes = clickTimes;
    }

}