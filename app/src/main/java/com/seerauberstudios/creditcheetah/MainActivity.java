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
import com.seerauberstudios.creditcheetah.util.RegexUtil;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends Activity {

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
        submitBtn.setEnabled(true);

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
                } else if (s.length() == 7) { //get first 6 for IIN
                    setCardBrand(s.toString()); //check/set card brand
                }


            }
        });
        ((EditText)findViewById(R.id.mainactivity_card_expiration_month)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 1) {
                    int month = Integer.parseInt(s.toString());
                    if (month > 0 && month < 13) {
                        //do date stuff
                    } else {
                        ccMonth.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        showError("Out of Range");
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

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 1) {
                    int year = Integer.parseInt(s.toString());
                    int currentYear = Calendar.getInstance(Locale.getDefault()).get(Calendar.YEAR) % 100; //Get current year divide by 100 to get last 2 digits
                    if (year >= currentYear && year <= currentYear + 50) {
                        //do date stuff
                    } else {
                        ccYear.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        showError("Out of Range");
                    }

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
        }
        else{
            ccNum.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
            cvvLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.cvv, null));
        }


    }


}
