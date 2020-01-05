package com.yulece.app.management.comments.api.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EmailMessage implements Serializable {

    private static final long serialVersionUID = -1896343237520686072L;
    @NotNull(message = "邮件标题不能为空")
    private String title;
    @NotNull(message = "邮件内容不能为空")
    private String context;
    @NotNull(message = "发送邮件地址不能为空")
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

    public EmailMessage(@NotNull String title, @NotNull String context, @NotNull String email) {
        this.title = title;
        this.context = context;
        this.email = email;
    }

}
