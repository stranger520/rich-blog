package com.zuicoding.platform.blog.modal;

public class WpPostWithBLOBs extends WpPost {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wp_post.post_content
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    private String postContent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wp_post.post_title
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    private String postTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wp_post.post_excerpt
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    private String postExcerpt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wp_post.to_ping
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    private String toPing;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wp_post.pinged
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    private String pinged;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wp_post.post_content_filtered
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    private String postContentFiltered;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wp_post.post_content
     *
     * @return the value of wp_post.post_content
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public String getPostContent() {
        return postContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wp_post.post_content
     *
     * @param postContent the value for wp_post.post_content
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public void setPostContent(String postContent) {
        this.postContent = postContent == null ? null : postContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wp_post.post_title
     *
     * @return the value of wp_post.post_title
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wp_post.post_title
     *
     * @param postTitle the value for wp_post.post_title
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wp_post.post_excerpt
     *
     * @return the value of wp_post.post_excerpt
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public String getPostExcerpt() {
        return postExcerpt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wp_post.post_excerpt
     *
     * @param postExcerpt the value for wp_post.post_excerpt
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt == null ? null : postExcerpt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wp_post.to_ping
     *
     * @return the value of wp_post.to_ping
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public String getToPing() {
        return toPing;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wp_post.to_ping
     *
     * @param toPing the value for wp_post.to_ping
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public void setToPing(String toPing) {
        this.toPing = toPing == null ? null : toPing.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wp_post.pinged
     *
     * @return the value of wp_post.pinged
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public String getPinged() {
        return pinged;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wp_post.pinged
     *
     * @param pinged the value for wp_post.pinged
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public void setPinged(String pinged) {
        this.pinged = pinged == null ? null : pinged.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wp_post.post_content_filtered
     *
     * @return the value of wp_post.post_content_filtered
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public String getPostContentFiltered() {
        return postContentFiltered;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wp_post.post_content_filtered
     *
     * @param postContentFiltered the value for wp_post.post_content_filtered
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    public void setPostContentFiltered(String postContentFiltered) {
        this.postContentFiltered = postContentFiltered == null ? null : postContentFiltered.trim();
    }
}