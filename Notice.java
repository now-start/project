package com.example.studyproject;

public class Notice {
    String noticeTitle;
    String noticeContent;
    String noticeName;
    String noticeDate;

    public Notice(String noticeTitle, String noticeContent, String noticeName, String noticeDate) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeName = noticeName;
        this.noticeDate = noticeDate;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }
}
