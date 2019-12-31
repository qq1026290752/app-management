package com.yulece.app.management.comments.api.entity;

import java.io.Serializable;

public class EmailMessage implements Serializable {

    private static final long serialVersionUID = -1896343237520686072L;
    private String title;
    private String context;
    private String email;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailMessage(String title, String context, String email) {
        this.title = title;
        this.context = context;
        this.email = email;
    }

    public EmailMessage() {
    }
}
