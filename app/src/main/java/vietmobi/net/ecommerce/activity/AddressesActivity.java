package vietmobi.net.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.adapter.AddressesAdapter;
import vietmobi.net.ecommerce.database.AddressDatabase;
import vietmobi.net.ecommerce.models.Address;

public class AddressesActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;
    FloatingActionButton btnAddAddress;
    RecyclerView rcvListAddress;
    AddressesAdapter addressesAdapter;
    List<Address> listAddress;

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
        rcvListAddress.setAdapter(addressesAdapter);
        rcvListAddress.setLayoutManager(linearLayoutManager);

    }

    private void addEvents() {
        btnBack.setOnClickListener(this);
        btnAddAddress.setOnClickListener(this);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        rcvListAddress = findViewById(R.id.rcvListAddress);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnAddAddress:
                Intent intent = new Intent(this, AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}