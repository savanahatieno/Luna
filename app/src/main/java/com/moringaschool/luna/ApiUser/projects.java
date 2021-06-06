package com.moringaschool.luna.ApiUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class projects {
    private int id;

    private  Integer commentCount;

    private Integer order;

    @SerializedName("body")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//    @SerializedName("id")
//    @Expose
//    private Long id;
//    @SerializedName("name")
//    @Expose
//    private String name;
//    @SerializedName("comment_count")
//    @Expose
//    private Integer commentCount;
//    @SerializedName("color")
//    @Expose
//    private Integer color;
//    @SerializedName("shared")
//    @Expose
//    private Boolean shared;
//    @SerializedName("sync_id")
//    @Expose
//    private Integer syncId;
//    @SerializedName("order")
//    @Expose
//    private Integer order;
//    @SerializedName("favorite")
//    @Expose
//    private Boolean favorite;
//    @SerializedName("url")
//    @Expose
//    private String url;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getCommentCount() {
//        return commentCount;
//    }
//
//    public void setCommentCount(Integer commentCount) {
//        this.commentCount = commentCount;
//    }
//
//    public Integer getColor() {
//        return color;
//    }
//
//    public void setColor(Integer color) {
//        this.color = color;
//    }
//
//    public Boolean getShared() {
//        return shared;
//    }
//
//    public void setShared(Boolean shared) {
//        this.shared = shared;
//    }
//
//    public Integer getSyncId() {
//        return syncId;
//    }
//
//    public void setSyncId(Integer syncId) {
//        this.syncId = syncId;
//    }
//
//    public Integer getOrder() {
//        return order;
//    }
//
//    public void setOrder(Integer order) {
//        this.order = order;
//    }
//
//    public Boolean getFavorite() {
//        return favorite;
//    }
//
//    public void setFavorite(Boolean favorite) {
//        this.favorite = favorite;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//}
