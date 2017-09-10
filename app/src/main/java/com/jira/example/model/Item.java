package com.jira.example.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 9/10/17.
 */

public class Item {
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public enum ISSUE_TYPE {STORY, DEFECT, TASK};
    public enum PRIORITY {MINOR, MAJOR, CRITICAL};
    public static final int ISSUE_TYPE_STORY = 0;
    public static final int ISSUE_TYPE_DEFECT = 1;
    public static final int ISSUE_TYPE_TASK = 2;
    public static final int MINOR_PRIORITY = 0;
    public static final int MAJOR_PRIORITY = 1;
    public static final int CRITICAL_PRIORITY = 2;

    public Item(long id, String name, String summary, int issueType, int priority, String epicLink) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.issueType = issueType;
        this.priority = priority;
        this.epicLink = epicLink;
    }

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("summary")
    private String summary;
    @SerializedName("issueType")
    private int issueType = ISSUE_TYPE_TASK;
    @SerializedName("priority")
    private int priority = MINOR_PRIORITY;
    @SerializedName("epicLink")
    private String epicLink;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ISSUE_TYPE getIssueType() {
        if(issueType == ISSUE_TYPE_STORY){
            return ISSUE_TYPE.STORY;
        } else if(issueType == ISSUE_TYPE_DEFECT){
            return ISSUE_TYPE.DEFECT;
        }

        return ISSUE_TYPE.TASK;
    }

    public void setIssueType(int issueType) {
        this.issueType = issueType;
    }

    public PRIORITY getPriority() {
        if(priority == MINOR_PRIORITY){
            return PRIORITY.MINOR;
        } else if(priority == MAJOR_PRIORITY){
            return PRIORITY.MAJOR;
        }

        return PRIORITY.CRITICAL;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getEpicLink() {
        return epicLink;
    }

    public void setEpicLink(String epicLink) {
        this.epicLink = epicLink;
    }
}
