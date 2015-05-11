package com.seerauberstudios.creditcheetah;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private EditText ccNum, ccMonth, ccYEar, ccCVV;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            }
        });

    }


}
