package com.runchen.blog.entity.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BasePO implements Serializable {

    /** primary key */
    protected Long id;
    /** 删除转态：1: deleted, 0: normal */
    protected Integer deleted;
    /** 创建人 */
    protected String createdBy;
    /** 创建时间 */
    protected Date createdTime;
    /** 最后修改人 */
    protected String modifiedBy;
    /** 最后修改时间 */
    protected Date modifiedTime;

    public void save() {
        String operator = "lisa";
        Date date = new Date();
        this.setDeleted(0);
        this.setCreatedBy(operator);
        this.setModifiedBy(operator);
        this.setCreatedTime(date);
        this.setModifiedTime(date);
    }

    public void update() {
        String operator = "lisa";
        Date date = new Date();
        this.setModifiedBy(operator);
        this.setModifiedTime(date);
    }

    public void delete() {
        this.setDeleted(1);
        update();
    }

    public Long getId() {
        return id;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasePO basePO = (BasePO) o;
        return Objects.equals(id, basePO.id) &&
                Objects.equals(deleted, basePO.deleted) &&
                Objects.equals(createdBy, basePO.createdBy) &&
                Objects.equals(createdTime, basePO.createdTime) &&
                Objects.equals(modifiedBy, basePO.modifiedBy) &&
                Objects.equals(modifiedTime, basePO.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deleted, createdBy, createdTime, modifiedBy, modifiedTime);
    }

    @Override
    public String toString() {
        return "BasePO{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
