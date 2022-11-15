package vietmobi.net.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import vietmobi.net.ecommerce.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    TextInputLayout layout_email, layout_password;
    TextInputEditText edtEmail, edtPassword;
    ImageView btnGotoForgot, btnLoginGg, btnLoginFb, btnBack;
    private String PASS = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        addEvents();
    }

    private void addEvents() {
        btnLogin.setOnClickListener(this);
        btnGotoForgot.setOnClickListener(this);
        btnLoginGg.setOnClickListener(this);
        btnLoginFb.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void initViews() {
        btnLogin = findViewById(R.id.btnLogin);
        layout_password = findViewById(R.id.layout_password);
        layout_email = findViewById(R.id.layout_email);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnGotoForgot = findViewById(R.id.btnGotoForgot);
        btnLoginGg = findViewById(R.id.btnLoginGg);
        btnLoginFb = findViewById(R.id.btnLoginFb);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnGotoForgot:
                gotoForgot();
                break;
            case R.id.btnLoginFb:
                loginFb();
                break;
            case R.id.btnLoginGg:
                loginGg();
                break;
            case R.id.btnBack:
                backToExit();
                break;

        }
    }

    private void backToExit() {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void loginGg() {
        Toast.makeText(this, "Comming soon", Toast.LENGTH_SHORT).show();
    }

    private void loginFb() {
        Toast.makeText(this, "Comming soon", Toast.LENGTH_SHORT).show();
    }

    private void gotoForgot() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void login() {
        if (validateEmailAddress(edtEmail) == true && validatePassword(edtPassword) == true){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    private boolean validateEmailAddress(EditText email){
        String emailInput = email.getText().toString();
        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            layout_email.setError("");
            layout_password.setEndIconDrawable(R.drawable.ic_tick);
            return true;
        } else {
            layout_email.setError("Not a valid email address. Should be your@email.com");
            edtEmail.requestFocus();
            return false;
        }
    }

    private boolean validatePassword(EditText password){
        String pass = password.getText().toString();
        if (!pass.isEmpty() && pass.equals(PASS)){
            layout_password.setError("");
            return true;
        }
        if (!pass.equals(PASS)) {
            layout_password.setError("Password not match");
            edtPassword.requestFocus();
            return false;
        } else {
            layout_password.setError("Password not null");
            edtPassword.requestFocus();
            return false;
        }
    }

}