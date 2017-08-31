package com.zuicoding.platform.blog.base;

/**
 * Created by Stephen.lin on 2017/8/30.
 */
public enum PostEnum {
    PUBLISH("publish","已发布"),DRAFT("draft","草稿"),PRIVATE("private","私有");

    private String value;
    private String text;

    PostEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
