package com.example.congratulationseverything;

/*
------------------------뷰페이저 어댑터의 배열 추출------------------------------------------
 */
public class DataPage {
    String back;    //배경 이미지
    String letter;  //편지 내용

    public DataPage(String back, String letter){
        this.back = back;
        this.letter = letter;
    }
    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getLetter() {

        return letter;
    }

    public void setLetter(String letter) {

        this.letter = letter;
    }
}