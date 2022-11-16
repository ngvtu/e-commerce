package vietmobi.net.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vietmobi.net.ecommerce.R;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnGotoLogin, btnGotoSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        initViews();
        addEvents();
    }

    private void addEvents() {
        btnGotoLogin.setOnClickListener(this);
        btnGotoSignUp.setOnClickListener(this);
    }

    private void initViews() {
        btnGotoLogin = findViewById(R.id.btnGotoLogin);
        btnGotoSignUp = findViewById(R.id.btnGotoSignUp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGotoLogin:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                finish();
                break;
            case R.id.btnGotoSignUp:
                Intent intent1 = new Intent(this, SignUpActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
//                finish();
        }
    }
}