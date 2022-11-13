package com.uniBoke.utils;

import java.util.Random;


public class RandomChar {

    //length用户要求产生字符串的长度
    /**
     * @param isNumber 是否为纯数字
     * @param length 随机字符串的长度
     * */
    public String getRandomString(int length,boolean isNumber){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String numberStr="0123456789";
        Random random = new Random();
        StringBuffer sb=new StringBuffer();
        int number = 0;
        for(int i=0;i<length;i++){
            if(isNumber){
                do {
                    number = random.nextInt(10);
                }while (number == 0);
                sb.append(numberStr.charAt(number));
            }else {
                number = random.nextInt(62);
                sb.append(str.charAt(number));
            }
        }
        return sb.toString();
    }

}
