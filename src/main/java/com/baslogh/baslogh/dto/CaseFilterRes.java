package com.baslogh.baslogh.dto;

public class CaseFilterRes {
    int total;
    int grievance;
    int criticism;
    int proposal;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getGrievance() {
        return grievance;
    }

    public void setGrievance(int grievance) {
        this.grievance = grievance;
    }

    public int getCriticism() {
        return criticism;
    }

    public void setCriticism(int criticism) {
        this.criticism = criticism;
    }

    public int getProposal() {
        return proposal;
    }

    public void setProposal(int proposal) {
        this.proposal = proposal;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    int request;
}
