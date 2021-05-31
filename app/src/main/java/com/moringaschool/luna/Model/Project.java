package com.moringaschool.luna.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("color")
    @Expose
    private Integer color;
    @SerializedName("shared")
    @Expose
    private Boolean shared;
    @SerializedName("sync_id")
    @Expose
    private Integer syncId;
    @SerializedName("favorite")
    @Expose
    private Boolean favorite;
    @SerializedName("inbox_project")
    @Expose
    private Boolean inboxProject;
    @SerializedName("url")
    @Expose
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Integer getSyncId() {
        return syncId;
    }

    public void setSyncId(Integer syncId) {
        this.syncId = syncId;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getInboxProject() {
        return inboxProject;
    }

    public void setInboxProject(Boolean inboxProject) {
        this.inboxProject = inboxProject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
