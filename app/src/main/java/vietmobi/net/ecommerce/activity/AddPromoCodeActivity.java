package vietmobi.net.ecommerce.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.models.PromoCode;

public class AddPromoCodeActivity extends AppCompatActivity {

    ImageView btnBack;
    TextView btnAddPromoCode;
    TextInputEditText edtCodeName, edtValuePromo, edtCode, edtRemainingDay;
    TextInputLayout layout_remaining, layout_name_promo, layout_code, layout_promo_value;
    PromoCode promoCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_promo_code);

        initViews();
        addEvents();
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
        btnAddPromoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPromoCode();
            }
        });
    }

    private void addPromoCode() {
        String codeName = edtCodeName.getText().toString().trim();
        String code = edtCode.getText().toString().trim();
        int promoValue = Integer.parseInt(edtValuePromo.getText().toString().trim());
        int remainingDay = Integer.parseInt(edtRemainingDay.getText().toString().trim());

        PromoCode promoCode = new PromoCode(codeName, code, remainingDay, promoValue);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("list_promo_code");

        myRef.child(code).setValue(promoCode, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(AddPromoCodeActivity.this, "Test", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void initViews() {
        btnAddPromoCode = findViewById(R.id.btnAddPromoCode);
        edtCodeName = findViewById(R.id.edtCodeName);
        edtValuePromo = findViewById(R.id.edtValuePromo);
        edtCode = findViewById(R.id.edtCode);
        edtRemainingDay = findViewById(R.id.edtRemainingDay);
        layout_remaining = findViewById(R.id.layout_remaining);
        layout_name_promo = findViewById(R.id.layout_name_promo);
        layout_code = findViewById(R.id.layout_code);
        layout_promo_value = findViewById(R.id.layout_promo_value);
        btnBack = findViewById(R.id.btnBack);
    }
}