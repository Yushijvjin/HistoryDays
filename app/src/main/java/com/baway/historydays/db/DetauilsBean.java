package com.baway.historydays.db;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/28 9:24
 */

public class DetauilsBean {

    private String url;
    private String content;
    private String title;
    private String date;
    private int flag;

    @Override
    public String toString() {
        return "DetauilsBean{" +
                "content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", flag=" + flag +
                '}';
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getFlag() {
        return flag;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public DetauilsBean(String content, int flag, String date, String title, String url) {
        this.content = content;
        this.flag = flag;
        this.date = date;
        this.title = title;
        this.url = url;
    }

    public DetauilsBean() {

    }
}
