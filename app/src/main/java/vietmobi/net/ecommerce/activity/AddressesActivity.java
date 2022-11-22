package vietmobi.net.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vietmobi.net.ecommerce.R;

public class AddressesActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;
    FloatingActionButton btnAddAddress;
    RecyclerView rcvListAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresses);

        initViews();
        addEvents();
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