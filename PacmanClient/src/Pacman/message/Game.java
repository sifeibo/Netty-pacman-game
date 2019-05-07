package Pacman.message;

import java.io.Serializable;

public class Game implements Serializable {
    private String username;
    private String password;
    private int tag;
    private int rtag;
    private boolean ifsuccess;
    private int[][] array;
    private char key;
    private int score1;
    private int score2;

    public Game(String username, String password, int tag, int rtag, boolean ifsuccess){
        this.username = username;
        this.password = password;
        this.tag = tag;
        this.rtag = rtag;
        this.ifsuccess = ifsuccess;

    }

    public Game(int tag, char key, int[][] array, int score1, int score2){
        this.tag = tag;
        this.key = key;
        this.array = array;

        this.score1 = score1;

        this.score2 = score2;

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public int getRtag() {
        return rtag;
    }
    public void setRtag(int rtag) {
        this.rtag = rtag;
    }

    public int getTag() {
        return tag;
    }
    public void setTag(int tag) {
        this.tag = tag;
    }

    public  boolean getIfsuccess(){ return ifsuccess;}
    public void  setIfsuccess(boolean ifsuccess){this.ifsuccess = ifsuccess;}

    public int[][] getArray() {
        return array;
    }
    public void setArray(int [][] array) {
        this.array = array;
    }

    public char getKey(){ return key;}
    public void setKey(char key){ this.key = key;}

    public int getScore1(){return  score1;}
    public void setScore1(int score1){this.score1 = score1;}

    public int getScore2(){return  score2;}
    public void setScore2(int score2){this.score2 = score2;}
}
