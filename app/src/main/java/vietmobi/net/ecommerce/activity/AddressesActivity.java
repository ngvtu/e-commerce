package vietmobi.net.ecommerce.activity;

import static vietmobi.net.ecommerce.activity.AddAddressActivity.MY_REQUEST_CODE_2;
import static vietmobi.net.ecommerce.activity.main.ProfileFragment.MY_REQUEST_CODE_3;
import static vietmobi.net.ecommerce.adapter.AddressesAdapter.MY_REQUEST_CODE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.adapter.AddressesAdapter;
import vietmobi.net.ecommerce.database.AddressDatabase;
import vietmobi.net.ecommerce.models.Address;

public class AddressesActivity extends AppCompatActivity {

    ImageView btnBack;
    FloatingActionButton btnAddAddress;
    RecyclerView rcvListAddress;
    AddressesAdapter addressesAdapter;
    List<Address> listAddress;
    Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresses);

        initViews();
        addListAddress();
        addEvents();

    }

    private void addListAddress() {
        listAddress = new ArrayList<>();

        listAddress = AddressDatabase.getInstance(this).AddressDAO().getListAddress();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        addressesAdapter = new AddressesAdapter(listAddress, this);
        addressesAdapter.notifyDataSetChanged();

        rcvListAddress.setAdapter(addressesAdapter);
        rcvListAddress.setLayoutManager(linearLayoutManager);

    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetAdd(view);
            }
        });
    }

    private void initViews() {
        finishActivity(MY_REQUEST_CODE_3);
        btnBack = findViewById(R.id.btnBack);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        rcvListAddress = findViewById(R.id.rcvListAddress);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE) {
            listAddress = AddressDatabase.getInstance(this).AddressDAO().getListAddress();
            addressesAdapter.setData(listAddress);

        }
    }

    public void showBottomSheetAdd(View view) {
        final android.app.Dialog dialog = new android.app.Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_add_address);

        TextView btnUpdateAddress = dialog.findViewById(R.id.btnUpdateAddress);
        TextView btnSaveAddress = dialog.findViewById(R.id.btnSaveAddress);
        TextInputLayout layout_name = dialog.findViewById(R.id.layout_name);
        TextInputLayout layout_address = dialog.findViewById(R.id.layout_address);
        TextInputLayout layout_district = dialog.findViewById(R.id.layout_district);
        TextInputLayout layout_city = dialog.findViewById(R.id.layout_city);
        TextInputLayout layout_phone = dialog.findViewById(R.id.layout_phone);
        TextInputLayout layout_country = dialog.findViewById(R.id.layout_country);

        TextInputEditText edtName = dialog.findViewById(R.id.edtName);
        TextInputEditText edtAddress = dialog.findViewById(R.id.edtAddress);
        TextInputEditText edtDistrict = dialog.findViewById(R.id.edtDistrict);
        TextInputEditText edtPhone = dialog.findViewById(R.id.edtPhone);
        TextInputEditText edtCity = dialog.findViewById(R.id.edtCity);
        TextInputEditText edtCountry = dialog.findViewById(R.id.edtCountry);

//        Bundle bundle = getIntent().getExtras();
//        if (bundle == null) {
//            return;
//        }
//        address = (Address) bundle.get("Address");
//        edtName.setText(address.getFullName());
//        edtAddress.setText(address.getAddress());
//        edtDistrict.setText(address.getDistrict());
//        edtCity.setText(address.getProvince());
//        edtCountry.setText(address.getCountry());
//        edtPhone.setText(address.getNumberPhone());
//        btnUpdateAddress.setVisibility(View.VISIBLE);
//        btnSaveAddress.setVisibility(View.GONE);

        btnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String district = edtDistrict.getText().toString().trim();
                String city = edtCity.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String country = edtCountry.getText().toString().trim();

                Address mAddress = new Address(name, address, district, city, country, phone, false);
                AddressDatabase.getInstance(view.getContext()).AddressDAO().insertAddress(mAddress);

                Toast.makeText(view.getContext(), "Add Address successfully", Toast.LENGTH_SHORT).show();
                listAddress = AddressDatabase.getInstance(view.getContext()).AddressDAO().getListAddress();
                addressesAdapter.setData(listAddress);
                dialog.dismiss();
            }
        });
        btnUpdateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address.setFullName(edtName.getText().toString().trim());
                address.setAddress(edtAddress.getText().toString().trim());
                address.setDistrict(edtDistrict.getText().toString().trim());
                address.setProvince(edtCity.getText().toString().trim());
                address.setNumberPhone(edtPhone.getText().toString().trim());
                address.setCountry(edtCountry.getText().toString().trim());

                AddressDatabase.getInstance(view.getContext()).AddressDAO().updateAddress(address);

                Toast.makeText(view.getContext(), "Add Address successfully", Toast.LENGTH_SHORT).show();
                listAddress = AddressDatabase.getInstance(view.getContext()).AddressDAO().getListAddress();
                addressesAdapter.setData(listAddress);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}