package com.flipper.example.insidelist.model;

public class DataModel extends RenderViewData {
    private String title = "";
    private int avatar = 0;
    private int calls = 0;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getCalls() {
        return calls;
    }

    public synchronized void incCalls() {
        calls++;
    }
}
