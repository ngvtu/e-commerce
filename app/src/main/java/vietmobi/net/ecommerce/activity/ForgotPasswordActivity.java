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

import vietmobi.net.ecommerce.R;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSend;
    ImageView btnBack;
    TextInputLayout layout_email;
    TextInputEditText edtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initViews();
        addEvents();
    }

    private void addEvents() {
        btnSend.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void initViews() {
        btnSend = findViewById(R.id.btnSend);
        btnBack = findViewById(R.id.btnBack);
        layout_email = findViewById(R.id.layout_email);
        edtEmail = findViewById(R.id.edtEmail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                backToSignUp();
                break;
            case R.id.btnSend:
                sendRequest();
                break;
        }
    }

    private void sendRequest() {
        validateEmailAddress(edtEmail);
        if (validateEmailAddress(edtEmail)){
            Toast.makeText(this, "Send em", Toast.LENGTH_SHORT).show();
        }
    }

    private void backToSignUp() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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
            layout_email.setError("Not a valid email address. Should be your@email.com");
            edtEmail.requestFocus();
            return false;
        }
    }
}