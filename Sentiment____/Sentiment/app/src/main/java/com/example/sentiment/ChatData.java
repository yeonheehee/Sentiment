package com.example.sentiment;

import java.util.ArrayList;

public class ChatData {
    private String msg;
    private String nickname;
    private ArrayList<String> Test = new ArrayList<String>();



    public String getMsg() {
        return msg;
    }
    public ArrayList TestMsg(){
        return Test;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
