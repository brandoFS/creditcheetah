package com.seerauberstudios.creditcheetah.util;

/**
 * Created by bmadden on 5/11/15.
 */
public class LuhnUtil {


    public static boolean isLuhnValid(String input){

        if(input.length() >= 10){
            int sum = 0;
            boolean alternate = false;
            for(int i = input.length() - 1;i >=0; i--){
                int num = Character.getNumericValue(input.charAt(i));
                    if (!alternate){
                        num = num * 1;
                    } else {
                        num = num * 2;
                    }
                    if (num > 9) {
                        num -= 9;
                    }

                sum += num;
                alternate = !alternate;
            }
            return (sum % 10 == 0);
        }

        return false;

    }

}
