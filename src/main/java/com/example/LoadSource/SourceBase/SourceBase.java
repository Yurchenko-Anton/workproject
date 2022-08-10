package com.example.LoadSource.SourceBase;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class SourceBase implements Comparable<SourceBase>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private long duration;
@Column(name = "called_number")
private String calledNumber;
@Column(name = "calling_number")
    private String callingNumber;
@Column(name = "start_timestamp")
private String startTimestamp;
@Column(name = "end_timestamp")
private String endTimestamp;
private String direction;
@Column(name = "called_name")
    private String calledName;
@Column(name = "calling_name")
    private String callingName;
@Column(name = "called_department")
    private String calledDepartment;
@Column(name = "calling_department")
    private String callingDepartment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public String getCallingNumber() {
        return callingNumber;
    }

    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber;
    }

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public String getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(String endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCalledName() {
        return calledName;
    }

    public void setCalledName(String calledName) {
        this.calledName = calledName;
    }

    public String getCallingName() {
        return callingName;
    }

    public void setCallingName(String callingName) {
        this.callingName = callingName;
    }

    public String getCalledDepartment() {
        return calledDepartment;
    }

    public void setCalledDepartment(String calledDepartment) {
        this.calledDepartment = calledDepartment;
    }

    public String getCallingDepartment() {
        return callingDepartment;
    }

    public void setCallingDepartment(String callingDepartment) {
        this.callingDepartment = callingDepartment;
    }

    @Override
    public int compareTo(SourceBase o) {
        return getStartTimestamp().compareTo(o.getStartTimestamp());
    }
}
