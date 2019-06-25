package com.baslogh.baslogh.dto;

import com.baslogh.baslogh.model.User;

import java.util.Set;

public class UserDetailDTO {
    User user;
    int writtenCasesCount;
    int requestedCasesCount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getWrittenCasesCount() {
        return writtenCasesCount;
    }

    public void setWrittenCasesCount(int writtenCasesCount) {
        this.writtenCasesCount = writtenCasesCount;
    }

    public int getRequestedCasesCount() {
        return requestedCasesCount;
    }

    public void setRequestedCasesCount(int requestedCasesCount) {
        this.requestedCasesCount = requestedCasesCount;
    }
}
