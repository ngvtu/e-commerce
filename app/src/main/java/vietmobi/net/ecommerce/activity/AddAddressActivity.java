package vietmobi.net.ecommerce.activity;

import static vietmobi.net.ecommerce.activity.main.ProfileFragment.MY_REQUEST_CODE_3;
import static vietmobi.net.ecommerce.adapter.AddressesAdapter.MY_REQUEST_CODE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.database.AddressDatabase;
import vietmobi.net.ecommerce.models.Address;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MY_REQUEST_CODE_2 = 9;
    ImageView btnBack;
    TextView btnSaveAddress, btnUpdateAddress;
    TextInputEditText edtName, edtAddress, edtDistrict, edtPhone, edtCity, edtCountry;
    TextInputLayout layout_name, layout_address, layout_district, layout_city, layout_phone, layout_country;
    Address address;

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
        btnUpdateAddress.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        address = (Address) bundle.get("Address");
        edtName.setText(address.getFullName());
        edtAddress.setText(address.getAddress());
        edtDistrict.setText(address.getDistrict());
        edtCity.setText(address.getProvince());
        edtCountry.setText(address.getCountry());
        edtPhone.setText(address.getNumberPhone());
        btnUpdateAddress.setVisibility(View.VISIBLE);
        btnSaveAddress.setVisibility(View.GONE);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnSaveAddress = findViewById(R.id.btnSaveAddress);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtDistrict = findViewById(R.id.edtDistrict);
        edtPhone = findViewById(R.id.edtPhone);
        edtCity = findViewById(R.id.edtCity);
        btnUpdateAddress = findViewById(R.id.btnUpdateAddress);
        edtCountry = findViewById(R.id.edtCountry);
        layout_name = findViewById(R.id.layout_name);
        layout_address = findViewById(R.id.layout_address);
        layout_district = findViewById(R.id.layout_district);
        layout_city = findViewById(R.id.layout_city);
        layout_phone = findViewById(R.id.layout_phone);
        layout_country = findViewById(R.id.layout_country);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                finish();
                break;
            case R.id.btnSaveAddress:

                break;
            case R.id.btnUpdateAddress:
                updateAddress();
                break;
        }
    }

    private void addAddress() {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String district = edtDistrict.getText().toString().trim();
        String city = edtCity.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String country = edtCountry.getText().toString().trim();

        Address mAddress = new Address(name, address, district, city, country, phone, false);
        AddressDatabase.getInstance(this).AddressDAO().insertAddress(mAddress);

        Toast.makeText(this, "Add Address successfully", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    private void updateAddress() {
        address.setFullName(edtName.getText().toString().trim());
        address.setAddress(edtAddress.getText().toString().trim());
        address.setDistrict(edtDistrict.getText().toString().trim());
        address.setProvince(edtCity.getText().toString().trim());
        address.setNumberPhone(edtPhone.getText().toString().trim());
        address.setCountry(edtCountry.getText().toString().trim());

        AddressDatabase.getInstance(this).AddressDAO().updateAddress(address);

        Toast.makeText(this, "Add Address successfully", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();
    }
}