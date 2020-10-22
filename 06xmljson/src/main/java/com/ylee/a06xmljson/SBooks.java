package com.ylee.a06xmljson;

public class SBooks implements Comparable<SBooks>{
    String author;
    String title;
    Float price;

    public SBooks(String author, String title, Float price) {
        this.author = author;
        this.title = title;
        this.price = price;
    }

    @Override
    // 가격기준 내림차순
//    public int compareTo(SBooks o) {
//        if(this.price > o.price){
//            return -1;
//        }else if(this.price < o.price){
//            return 1;
//        }
//        return 0;
//    }

    // 저자 이름순 오름차순
    public int compareTo(SBooks o) {
        return this.author.compareTo(o.author);
    }
}
