package vietmobi.net.ecommerce.activity.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.activity.IntroActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView btnBack;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch1, switch2, switch3;
    TextInputLayout layout_name, layout_email;
    TextInputEditText edtFullName, edtEmail;
    TextView btnSaveInfo, btnChangePass, btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        showUserInformation();


        initViews();
        addEvents();
    }

    private void showUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();

        edtEmail.setText(email, TextView.BufferType.EDITABLE);
    }

    private void addEvents() {
        btnBack.setOnClickListener(this);
        btnSaveInfo.setOnClickListener(this);
        btnChangePass.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        layout_name = findViewById(R.id.layout_name);
        layout_email = findViewById(R.id.layout_email);
        btnSaveInfo = findViewById(R.id.btnSaveInfo);
        btnChangePass = findViewById(R.id.btnChangePass);
        edtFullName = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        btnSignOut = findViewById(R.id.btnSignOut);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnSaveInfo:
                saveInfo();
                break;
            case R.id.btnChangePass:
                changePass();
                break;
            case R.id.btnSignOut:
                signOut();
                break;
        }
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SettingsActivity.this, IntroActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void changePass() {
        final android.app.Dialog dialog = new android.app.Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_change_password);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void saveInfo() {
        Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
    }
}