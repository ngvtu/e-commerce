package vietmobi.net.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.activity.database.AddressDatabase;
import vietmobi.net.ecommerce.models.Address;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;
    TextView btnSaveAddress;
    TextInputEditText edtName, edtAddress, edtDistrict, edtPhone, edtCity;
    TextInputLayout layout_name, layout_address, layout_district, layout_city, layout_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        initViews();
        addEvents();
    }

    private void addEvents() {
        btnBack.setOnClickListener(this);
        btnSaveAddress.setOnClickListener(this);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnSaveAddress = findViewById(R.id.btnSaveAddress);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtDistrict = findViewById(R.id.edtDistrict);
        edtPhone = findViewById(R.id.edtPhone);
        edtCity = findViewById(R.id.edtCity);
        layout_name = findViewById(R.id.layout_name);
        layout_address = findViewById(R.id.layout_address);
        layout_district = findViewById(R.id.layout_district);
        layout_city = findViewById(R.id.layout_city);
        layout_phone = findViewById(R.id.layout_phone);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                finish();
                break;
            case R.id.btnSaveAddress:
                addAddress();
                break;
        }
    }

    private void addAddress() {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String district = edtDistrict.getText().toString().trim();
        String city = edtCity.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        Address addressNew = new Address(name, address, district, city, phone, false);
        AddressDatabase.getInstance(this).AddressDAO().insertAddress(addressNew);

        Toast.makeText(this, "Add Note successfully", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}