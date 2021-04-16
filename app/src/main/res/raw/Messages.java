package com.example.mainapp;

public class Messages {

    private int agent;
    private int nonagent;
    private int sender;
    private String message;

    public int getAgent() {
        return agent;
    }

    public void setAgent(int agent) {
        this.agent = agent;
    }

    public int getNonagent() {
        return nonagent;
    }

    public void setNonagent(int nonagent) {
        this.nonagent = nonagent;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "agent=" + agent +
                ", nonagent=" + nonagent +
                ", sender=" + sender +
                ", message='" + message + '\'' +
                '}';
    }
}
