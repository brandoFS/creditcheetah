package com.seerauberstudios.creditcheetah.util;


import java.util.regex.Pattern;


/**
 * Created by bmadden on 5/11/15.
 */
public class RegexUtil {

    private static final String visa ="^4[0-9]{6,}$";
    private static final String masterCard = "^5[1-5][0-9]{5,}$";
    private static final String americanExpress = "^3[47][0-9]{5,}$";
    private static final String discover = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
    private static final String jcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";

    public static final Pattern VISA_PATTERN = Pattern.compile(visa);
    public static final Pattern MC_PATTERN = Pattern.compile(masterCard);
    public static final Pattern AMEX_PATTERN = Pattern.compile(americanExpress);
    public static final Pattern DISC_PATTERN = Pattern.compile(discover);
    public static final Pattern JCB_PATTERN = Pattern.compile(jcb);




}
