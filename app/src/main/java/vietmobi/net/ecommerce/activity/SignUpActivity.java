package vietmobi.net.ecommerce.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.activity.main.MainActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    protected FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    TextView btnSignUp;
    TextInputLayout layout_name, layout_email, layout_password, layout_confirm_password;
    TextInputEditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    ImageView btnGotoLogin, btnLoginGg, btnLoginFb, btnBack;
    ProgressDialog progressDialog;

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
        layout_confirm_password = findViewById(R.id.layout_confirm_password);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        progressDialog = new ProgressDialog(this);
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
        Intent intent = new Intent(this, IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void loginGg() {
        Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
    }

    private void loginFb() {
        Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
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
        validatePassword(edtPassword, edtConfirmPassword);

        if (validateName(edtName) && validateEmailAddress(edtEmail) && validatePassword(edtPassword, edtConfirmPassword)){
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//            finish();

            String email = Objects.requireNonNull(edtEmail.getText()).toString().trim();
            String password = Objects.requireNonNull(edtPassword.getText()).toString().trim();

            progressDialog.setTitle("Registering, please wait.");
            progressDialog.show();
            mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                            }
                        }
                    });
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

    private boolean validatePassword(EditText password, EditText passwordCf) {
        String pass = password.getText().toString();
        String passConfirm = passwordCf.getText().toString();
        if (!pass.isEmpty() && pass.equals(passConfirm)) {
            layout_password.setError("");
            layout_password.setEndIconDrawable(R.drawable.ic_tick);
            return true;
        } else {
            layout_password.setError("Repeat password is incorrect");
            edtPassword.requestFocus();
            return false;
        }
    }
}