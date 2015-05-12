package com.seerauberstudios.creditcheetah;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.seerauberstudios.creditcheetah.util.RegexUtil;


public class MainActivity extends Activity {

    private EditText ccNum, ccMonth, ccYEar, ccCVV;
    private Button submitBtn;
    private ImageView ccLogo, cvvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccLogo = (ImageView)findViewById(R.id.mainactivity_card_icon);
        cvvLogo = (ImageView)findViewById(R.id.mainactivity_cardcvv_icon);
        submitBtn = (Button)findViewById(R.id.mainMenu_submitButton);
        setupUI();
    }



    private void validateSubmit(){
        submitBtn.isEnabled();

    }

    private void showError(String error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }



    private void setupUI(){
        ((EditText)findViewById(R.id.mainactivity_card_number)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 4) {
                    ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.genericcard, null));
                } else if (s.length() == 16) {
                    setCardBrand(s.toString());
                }


            }
        });

    }

    private void setCardBrand(String iin){
       // ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.visa, null));
        System.out.println("HERE!!!!!!!!!! " + iin);

        if(RegexUtil.VISA_PATTERN.matcher(iin).matches()){
            ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.visa, null));
        }
        else if(RegexUtil.MC_PATTERN.matcher(iin).matches()){
            ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.mastercard, null));
        }
        else if(RegexUtil.AMEX_PATTERN.matcher(iin).matches()){
            ccLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.mipmap.amex, null));
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

    }


}
