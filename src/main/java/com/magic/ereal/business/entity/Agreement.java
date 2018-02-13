package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 使用协议与隐私政策 关于一真
 * Created by Eric Xie on 2017/5/24 0024.
 */
public class Agreement implements Serializable {


    private Integer id;

    private String title;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
