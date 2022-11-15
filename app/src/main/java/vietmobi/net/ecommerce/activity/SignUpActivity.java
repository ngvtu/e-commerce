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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vietmobi.net.ecommerce.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PASS = "123";
    Button btnSignUp;
    TextInputLayout layout_name, layout_email, layout_password;
    TextInputEditText edtName, edtEmail, edtPassword;
    ImageView btnGotoLogin, btnLoginGg, btnLoginFb, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
        addEvents();
    }

    private void addEvents() {
        btnSignUp.setOnClickListener(this);
        btnGotoLogin.setOnClickListener(this);
        btnLoginGg.setOnClickListener(this);
        btnLoginFb.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void initViews() {
        btnSignUp = findViewById(R.id.btnSignUp);
        layout_name = findViewById(R.id.layout_name);
        layout_password = findViewById(R.id.layout_password);
        layout_email = findViewById(R.id.layout_email);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnGotoLogin = findViewById(R.id.btnGotoLogin);
        btnLoginGg = findViewById(R.id.btnLoginGg);
        btnLoginFb = findViewById(R.id.btnLoginFb);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                signUp();
                break;
            case R.id.btnGotoLogin:
                gotoLogin();
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
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void loginGg() {
        Toast.makeText(this, "Comming soon", Toast.LENGTH_SHORT).show();
    }

    private void loginFb() {
        Toast.makeText(this, "Comming soon", Toast.LENGTH_SHORT).show();
    }

    private void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void signUp() {
        validateName(edtName);
        validateEmailAddress(edtEmail);
        validatePassword(edtPassword);

        if (validateName(edtName) && validateEmailAddress(edtEmail) && validatePassword(edtPassword)){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private boolean validateEmailAddress(EditText email){
        String emailInput = email.getText().toString();
        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            layout_email.setError("");
            layout_email.setEndIconDrawable(R.drawable.ic_tick);
            return true;
        } if (emailInput.isEmpty()){
            layout_email.setError("This field is required!");
            edtEmail.requestFocus();
            return false;
        } else{
            layout_email.setError("Not the format of the email");
            edtEmail.requestFocus();
            return false;
        }
    }

    private boolean validateName(EditText edtName){
        String name = edtName.getText().toString();
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(name);
        if (name.isEmpty()){
            layout_name.setError("This field is required");
            edtName.requestFocus();
            return false;
        } if (matcher.find()){
            layout_name.setError("");
            layout_name.setEndIconDrawable(R.drawable.ic_tick);
            return true;
        } else {
            layout_name.setError("Not format of name");
            edtName.requestFocus();
            return false;
        }
    }

    private boolean validatePassword(EditText password) {
        String pass = password.getText().toString();
        if (!pass.isEmpty() && pass.equals(PASS)) {
            layout_password.setError("");
            layout_password.setEndIconDrawable(R.drawable.ic_tick);
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