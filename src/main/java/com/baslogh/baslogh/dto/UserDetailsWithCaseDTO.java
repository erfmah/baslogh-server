package com.baslogh.baslogh.dto;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.User;

import java.util.Set;

public class UserDetailsWithCaseDTO {
    User user;
    int writtenCasesCount;
    int requestedCasesCount;

    public Set<Case> getCases() {
        return cases;
    }

    public void setCases(Set<Case> cases) {
        this.cases = cases;
    }

    Set<Case> cases;

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
