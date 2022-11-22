package vietmobi.net.ecommerce.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import vietmobi.net.ecommerce.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MY_REQUEST_CODE = 1;
    ImageView btnBack;
    CircleImageView imgUser;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    Switch switch1, switch2, switch3;
    TextInputLayout layout_change_name, layout_change_email;
    TextView btnSaveInfo, tvFullName, tvEmailUser, btnChangePass, btnSignOut;

    final private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                if (intent == null) {
                    return;
                }
                Uri uri = intent.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    setBitmapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        initViews();
        showUserInformation();

        addEvents();
    }

    private void showUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        tvEmailUser.setText(user.getEmail());
        tvFullName.setText(user.getDisplayName());
        Glide.with(this).load(user.getPhotoUrl()).error(R.drawable.img).into(imgUser);
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
        layout_change_name = findViewById(R.id.layout_change_name);
        layout_change_email = findViewById(R.id.layout_change_email);
        btnSaveInfo = findViewById(R.id.btnChangeInfo);
        btnChangePass = findViewById(R.id.btnChangePass);
        tvFullName = findViewById(R.id.tvFullName);
        tvEmailUser = findViewById(R.id.tvEmailUser);
        btnSignOut = findViewById(R.id.btnSignOut);
        imgUser = findViewById(R.id.imgUser);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnChangeInfo:
                changeInfo();
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

    public void changePass() {
        final android.app.Dialog dialog = new android.app.Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_change_password);

        TextInputLayout layout_old_password = dialog.findViewById(R.id.layout_old_password);
        TextInputLayout layout_new_password = dialog.findViewById(R.id.layout_new_password);
        TextInputLayout layout_confirm_password = dialog.findViewById(R.id.layout_confirm_password);
        TextInputEditText edtOldPassword = dialog.findViewById(R.id.edtOldPassword);
        TextInputEditText edtNewPassword = dialog.findViewById(R.id.edtNewPassword);
        TextInputEditText edtConfirmPassword = dialog.findViewById(R.id.edtConfirmPassword);
        TextView btnSavePass = dialog.findViewById(R.id.btnSavePass);
        LinearLayout gotoForgot = dialog.findViewById(R.id.gotoForgot);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        btnSavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validatePassword(edtOldPassword, edtNewPassword, edtConfirmPassword, layout_old_password, layout_new_password, layout_confirm_password);
                String newPassword = edtNewPassword.getText().toString().trim();

                if (validatePassword(edtOldPassword, edtNewPassword, edtConfirmPassword, layout_old_password, layout_new_password, layout_confirm_password)) {
                    user.updatePassword(newPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SettingsActivity.this, "User password updated!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }
                            });

                }
            }
        });

        gotoForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void changeInfo() {
        final android.app.Dialog dialog = new android.app.Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_change_info);

        CircleImageView imgUserUpdate = dialog.findViewById(R.id.imgUserUpdate);
        TextInputLayout layout_change_name = dialog.findViewById(R.id.layout_change_name);
        TextInputLayout layout_change_email = dialog.findViewById(R.id.layout_change_email);
        TextInputEditText edtNewName = dialog.findViewById(R.id.edtNewName);
        TextInputEditText edtNewEmail = dialog.findViewById(R.id.edtNewEmail);
        TextView btnUpdate = dialog.findViewById(R.id.btnUpdate);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        edtNewEmail.setText(user.getEmail());
        edtNewName.setText(user.getDisplayName());
        Glide.with(this).load(user.getPhotoUrl()).error(R.drawable.img).into(imgUser);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtNewName.getText().toString().trim();
                String email = edtNewEmail.getText().toString().trim();

                validateName(edtNewName, layout_change_name);
                validateEmailAddress(edtNewEmail, layout_change_email);
                if (validateEmailAddress(edtNewEmail, layout_change_email) && validateName(edtNewName, layout_change_name)) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user == null) {
                        return;
                    }

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build();

                    user.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(SettingsActivity.this, "update email", Toast.LENGTH_SHORT).show();
                            showUserInformation();
                        }
                    });

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SettingsActivity.this, "Update!", Toast.LENGTH_SHORT).show();
                                        showUserInformation();
                                        dialog.dismiss();
                                    }
                                }
                            });
                }
            }
        });

        imgUserUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            this.requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    private boolean validateEmailAddress(EditText email, TextInputLayout layout_change_email) {
        String emailInput = email.getText().toString();
        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            layout_change_email.setError("");
            layout_change_email.setEndIconDrawable(R.drawable.ic_tick);
            return true;
        }
        if (emailInput.isEmpty()) {
            layout_change_email.setError("This field is required!");
            email.requestFocus();
            return false;
        } else {
            layout_change_email.setError("Not the format of the email");
            email.requestFocus();
            return false;
        }
    }

    private boolean validateName(EditText edtName, TextInputLayout layout_change_name) {
        String name = edtName.getText().toString();
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(name);
        if (name.isEmpty()) {
            layout_change_name.setError("This field is required");
            edtName.requestFocus();
            return false;
        }
        if (matcher.find()) {
            layout_change_name.setError("");
            layout_change_name.setEndIconDrawable(R.drawable.ic_tick);
            return true;
        } else {
            layout_change_name.setError("Not format of name");
            edtName.requestFocus();
            return false;
        }
    }

    private boolean validatePassword(EditText edtOldPassword, EditText edtNewPassword, EditText edtPasswordCf, TextInputLayout layout_old_password, TextInputLayout layout_new_password, TextInputLayout layout_confirm_password) {

        loginPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        String passwordUser = loginPreferences.getString("passwordUser", "");

        String oldPassword = edtOldPassword.getText().toString().trim();
        String newPassword = edtNewPassword.getText().toString();
        String passConfirm = edtPasswordCf.getText().toString();
        if (!oldPassword.equals(passwordUser)) {
            layout_old_password.setError("Incorrect password");
            edtOldPassword.requestFocus();
            return false;
        } else if (newPassword.length() > 6 && newPassword.equals(passConfirm) && oldPassword.equals(passwordUser)) {
            layout_old_password.setError("");
            layout_new_password.setError("");
            layout_old_password.setEndIconDrawable(R.drawable.ic_tick);
            return true;
        } else if (newPassword.length() <= 6){
            layout_old_password.setError("");
            layout_new_password.setError("Your password must have at least 6 characters.");
            edtNewPassword.requestFocus();
            return false;
        } else if (!newPassword.equals(passConfirm)){
            layout_new_password.setError("Repeat password is incorrect");
            edtNewPassword.requestFocus();
            return false;
        } else{
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "You must accept permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setBitmapImageView(Bitmap bitmap) {
        imgUser.setImageBitmap(bitmap);
    }
}