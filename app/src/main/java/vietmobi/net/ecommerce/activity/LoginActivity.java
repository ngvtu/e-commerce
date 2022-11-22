package vietmobi.net.ecommerce.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.activity.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    protected FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    String email, password;
    private Boolean saveLogin;
    private CheckBox cbxRemember;
    private TextView btnGotoSignUp, btnLogin;
    private TextInputLayout layout_email, layout_password;
    private TextInputEditText edtEmail, edtPassword;
    private ImageView btnGotoForgot, btnLoginGg, btnLoginFb, btnBack;
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
        btnGotoSignUp.setOnClickListener(this);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            edtEmail.setText(loginPreferences.getString("email", ""));
            edtPassword.setText(loginPreferences.getString("password", ""));
            cbxRemember.setChecked(true);
        }
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
        btnGotoSignUp = findViewById(R.id.btnGotoSignUp);
        cbxRemember = findViewById(R.id.cbxRemember);
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
            case R.id.btnGotoSignUp:
                Intent intent = new Intent(this, SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
        }
    }

    private void backToExit() {
        Intent intent = new Intent(this, IntroActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void loginGg() {
        Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();


    }

    private void loginFb() {
        Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
    }

    private void gotoForgot() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void login() {
        email = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString().trim();

        loginPrefsEditor.putString("passwordUser", password).commit();

        if (cbxRemember.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true)
                    .putString("email", email)
                    .putString("password", password)
                    .commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }
        if (validateEmailAddress(edtEmail) && validatePassword(edtPassword)) {
            mFirebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                layout_password.setError("Password or email not match");
                                edtEmail.requestFocus();
                            }
                        }
                    });


        }
    }

    private boolean validateEmailAddress(EditText email) {
        String emailInput = email.getText().toString();
        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            layout_email.setError("");
            layout_password.setEndIconDrawable(R.drawable.ic_tick);
            return true;
        } else {
            layout_email.setError("Not a valid email address. Should be your@email.com");
            edtEmail.requestFocus();
            return false;
        }
    }

    private boolean validatePassword(EditText password) {
        String pass = password.getText().toString();
        if (!pass.isEmpty()) {
            layout_password.setError("");
            layout_password.setEndIconDrawable(R.drawable.ic_tick);
            return true;
        } else {
            layout_password.setError("Password not null");
            edtPassword.requestFocus();
            return false;
        }
    }

}