package com.zuicoding.platform.blog.modal;

import java.util.List;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p></p>
 */
public class DashBord {

    /**
     * 文章总数
     */
    private int postCount;
    //评论总数
    private int commentCount;
    //待评论总数
    private int appCommentCount;

    //最近评论列表
    private List<WpCommentWithBLOBs> comments;

    //最近发表的文章列表
    private List<WpPost> posts;

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getAppCommentCount() {
        return appCommentCount;
    }

    public void setAppCommentCount(int appCommentCount) {
        this.appCommentCount = appCommentCount;
    }

    public List<WpCommentWithBLOBs> getComments() {
        return comments;
    }

    public void setComments(List<WpCommentWithBLOBs> comments) {
        this.comments = comments;
    }

    public List<WpPost> getPosts() {
        return posts;
    }

    public void setPosts(List<WpPost> posts) {
        this.posts = posts;
    }
}
