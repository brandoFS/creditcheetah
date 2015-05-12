package com.seerauberstudios.creditcheetah;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.seerauberstudios.creditcheetah.model.Ready;
import com.seerauberstudios.creditcheetah.util.RegexUtil;

import java.util.Calendar;
import java.util.EnumSet;
import java.util.Locale;


public class MainActivity extends Activity {

    private EnumSet<Ready> ready = EnumSet.noneOf(Ready.class); //enums to dertermine when button is enabled
    private EnumSet<Ready> allReady = EnumSet.allOf(Ready.class);

    private EditText ccNum, ccMonth, ccYear, ccCVV;
    private Button submitBtn;
    private ImageView ccLogo, cvvLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccLogo = (ImageView)findViewById(R.id.mainactivity_card_icon);
        cvvLogo = (ImageView)findViewById(R.id.mainactivity_cardcvv_icon);
        ccNum = (EditText)findViewById(R.id.mainactivity_card_number);
        ccCVV = (EditText)findViewById(R.id.mainactivity_cardcvv);
        ccMonth = (EditText)findViewById(R.id.mainactivity_card_expiration_month);
        ccYear = (EditText)findViewById(R.id.mainactivity_card_expiration_year);
        submitBtn = (Button)findViewById(R.id.mainMenu_submitButton);
        setupUI();
    }



    private void validateSubmit(){
        if(ready.equals(allReady))submitBtn.setEnabled(true);
        else submitBtn.setEnabled(false);

    }

    private void showError(String error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }



    private void setupUI(){
        ((EditText)findViewById(R.id.mainactivity_card_number)).addTextChangedListener(new TextWatcher() { //set text watcher to change card brand logo
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 6) { //change back to blank if they delete
                    ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.genericcard, null));
                    cvvLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.cvv, null));
                    ready.remove(Ready.CCNUM);
                    validateSubmit();
                } else if (s.length() == 7) { //get first 6 for IIN
                    setCardBrand(s.toString()); //check/set card brand
                    ready.add(Ready.CCNUM);
                    validateSubmit();
                }


            }
        });
        ((EditText)findViewById(R.id.mainactivity_card_expiration_month)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ccMonth.setTextColor(getResources().getColor(android.R.color.black));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 1) {
                    int month = Integer.parseInt(s.toString());
                    if (month > 0 && month < 13) {
                        ready.add(Ready.MONTH);
                        //do date stuff
                        validateSubmit();
                    } else {
                        ready.remove(Ready.MONTH);
                        validateSubmit();
                        ccMonth.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        ccMonth.setError("Invalid date (M/M)");
                    }

                }
            }
        });
        ((EditText)findViewById(R.id.mainactivity_card_expiration_year)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ccYear.setTextColor(getResources().getColor(android.R.color.black));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 1) {
                    int year = Integer.parseInt(s.toString());
                    int currentYear = Calendar.getInstance(Locale.getDefault()).get(Calendar.YEAR) % 100; //Get current year divide by 100 to get last 2 digits
                    if (year >= currentYear && year <= currentYear + 50) {
                        ready.add(Ready.YEAR);
                        //do date stuff
                        validateSubmit();
                    } else {
                        ccYear.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        ccYear.setError("Expired Year");
                        ready.remove(Ready.YEAR);
                        validateSubmit();
                    }

                }
            }
        });

        ((EditText)findViewById(R.id.mainactivity_cardcvv)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ready.remove(Ready.CVV);
                validateSubmit();
                if (s.length() > 2) {
                    ready.add(Ready.CVV);
                    validateSubmit();
                }

            }
        });


        ((Button)findViewById(R.id.mainMenu_submitButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        }

    private void setCardBrand(String iin){ //Check card's using pattern matching then change card logo
        Boolean amex = false;

        if(RegexUtil.VISA_PATTERN.matcher(iin).matches()){
            ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.visa, null));
        }
        else if(RegexUtil.MC_PATTERN.matcher(iin).matches()){
            ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.mastercard, null));
        }
        else if(RegexUtil.AMEX_PATTERN.matcher(iin).matches()){
            ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.amex, null));
            amex = true;
        }
        else if(RegexUtil.DISC_PATTERN.matcher(iin).matches()){
            ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.discover, null));
        }
        else if(RegexUtil.JCB_PATTERN.matcher(iin).matches()){
            ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.jcb, null));
        }
        else {
            ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.genericcard, null));
        }

        if(amex){
            ccNum.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
            cvvLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.amexcvv, null));
            ccCVV.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        }
        else{
            ccNum.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
            cvvLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.cvv, null));
            ccCVV.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        }


    }


}
