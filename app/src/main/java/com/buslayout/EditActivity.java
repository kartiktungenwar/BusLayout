package com.buslayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.buslayout.home.MainActivity;

public class EditActivity extends AppCompatActivity {

    private Button btn_submit;
    private EditText ev_password;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ev_password = (EditText) findViewById(R.id.ev_password);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = ev_password.getText().toString();
                if(TextUtils.isEmpty(password)){
                    ev_password.setError(getString(R.string.empty_password));
                    return;
                }
                if(!password.equals("1234")){
                    ev_password.setError(getString(R.string.invalid_password));
                    return;
                }
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}