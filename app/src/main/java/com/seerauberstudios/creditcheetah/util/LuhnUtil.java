package com.seerauberstudios.creditcheetah.util;

/**
 * Created by bmadden on 5/11/15.
 */
public class LuhnUtil {


    public static boolean isLuhnValid(String input){

        if(input.length() >= 10){
            int sum = 0;
            for(int i = 0; i < input.length(); i++){
                int num = Character.getNumericValue(input.charAt(i));
                if(i % 2 != 0){
                    num = num * 1;
                }
                else {
                    num = num * 2;
                }
                if(num > 9){
                    num -= 9;
                }
                sum += num;
            }
            return (sum % 10 == 0);
        }

        return false;

    }

}
