package com.seerauberstudios.creditcheetah;

import com.seerauberstudios.creditcheetah.util.LuhnUtil;
import com.seerauberstudios.creditcheetah.util.RegexUtil;

import junit.framework.TestCase;

/**
 * Created by brando on 5/12/15.
 */
public class UtilTests extends TestCase {
    //Test numbers for each credit card brand and bad one for failz
    private final String AMEX_TEST = "371449635398431";
    private final String DISCOVER_TEST = "6011111111111117";
    private final String JCB_TEST = "3530111333300000";
    private final String MC_TEST = "5555555555554444";
    private final String VISA_TEST = "4111111111111111";
    private final String BAD_TEST =  "5555511284958336";

    public void testLuhnAlogrithmn(){ //test Luhn algorithmn to validate credit card numbers

        assertEquals(true, LuhnUtil.isLuhnValid(AMEX_TEST));
        assertEquals(true, LuhnUtil.isLuhnValid(DISCOVER_TEST));
        assertEquals(true, LuhnUtil.isLuhnValid(JCB_TEST));
        assertEquals(true, LuhnUtil.isLuhnValid(MC_TEST));
        assertEquals(true, LuhnUtil.isLuhnValid(VISA_TEST));
        //negative cases
        assertEquals(false, LuhnUtil.isLuhnValid(BAD_TEST));
        assertEquals(false, LuhnUtil.isLuhnValid(AMEX_TEST + BAD_TEST));

    }

    public void testRegex(){ //Test pattern matching for card brands

        //AMEX
        assertEquals(true,RegexUtil.AMEX_PATTERN.matcher(AMEX_TEST).matches());
        assertEquals(false, RegexUtil.DISC_PATTERN.matcher(AMEX_TEST).matches());
        assertEquals(false,RegexUtil.JCB_PATTERN.matcher(AMEX_TEST).matches());
        assertEquals(false,RegexUtil.MC_PATTERN.matcher(AMEX_TEST).matches());
        assertEquals(false,RegexUtil.VISA_PATTERN.matcher(AMEX_TEST).matches());

        //DISCOVER
        assertEquals(false,RegexUtil.AMEX_PATTERN.matcher(DISCOVER_TEST).matches());
        assertEquals(true, RegexUtil.DISC_PATTERN.matcher(DISCOVER_TEST).matches());
        assertEquals(false,RegexUtil.JCB_PATTERN.matcher(DISCOVER_TEST).matches());
        assertEquals(false,RegexUtil.MC_PATTERN.matcher(DISCOVER_TEST).matches());
        assertEquals(false,RegexUtil.VISA_PATTERN.matcher(DISCOVER_TEST).matches());

        //JCB
        assertEquals(false,RegexUtil.AMEX_PATTERN.matcher(JCB_TEST).matches());
        assertEquals(false, RegexUtil.DISC_PATTERN.matcher(JCB_TEST).matches());
        assertEquals(true,RegexUtil.JCB_PATTERN.matcher(JCB_TEST).matches());
        assertEquals(false,RegexUtil.MC_PATTERN.matcher(JCB_TEST).matches());
        assertEquals(false,RegexUtil.VISA_PATTERN.matcher(JCB_TEST).matches());

        //MASTERCARD
        assertEquals(false,RegexUtil.AMEX_PATTERN.matcher(MC_TEST).matches());
        assertEquals(false, RegexUtil.DISC_PATTERN.matcher(MC_TEST).matches());
        assertEquals(false,RegexUtil.JCB_PATTERN.matcher(MC_TEST).matches());
        assertEquals(true,RegexUtil.MC_PATTERN.matcher(MC_TEST).matches());
        assertEquals(false,RegexUtil.VISA_PATTERN.matcher(MC_TEST).matches());

        //VISA
        assertEquals(false,RegexUtil.AMEX_PATTERN.matcher(VISA_TEST).matches());
        assertEquals(false, RegexUtil.DISC_PATTERN.matcher(VISA_TEST).matches());
        assertEquals(false,RegexUtil.JCB_PATTERN.matcher(VISA_TEST).matches());
        assertEquals(false,RegexUtil.MC_PATTERN.matcher(VISA_TEST).matches());
        assertEquals(true,RegexUtil.VISA_PATTERN.matcher(VISA_TEST).matches());


    }




}
