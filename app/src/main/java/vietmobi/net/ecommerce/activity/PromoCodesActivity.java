package vietmobi.net.ecommerce.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.adapter.AddressesAdapter;
import vietmobi.net.ecommerce.adapter.CardAdapter;
import vietmobi.net.ecommerce.adapter.PromoCodeAdapter;
import vietmobi.net.ecommerce.database.AddressDatabase;
import vietmobi.net.ecommerce.models.Card;
import vietmobi.net.ecommerce.models.PromoCode;

public class PromoCodesActivity extends AppCompatActivity {

    public static final int MY_REQUEST_CODE_4 = 4;
    RecyclerView rcvListPromoCode;
    FloatingActionButton btnAddPromoCode;
    ImageView btnBack;
    TextView tvNotification;

    List<PromoCode> listPromoCode;
    PromoCodeAdapter promoCodeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocodes);

        initViews();
        getListPromo();
        addEvents();

    }

    private void getListPromo() {
        listPromoCode = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_promo_code");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PromoCode promoCode = dataSnapshot.getValue(PromoCode.class);
                    listPromoCode.add(promoCode);
                }

                if (listPromoCode.isEmpty()) {
                    tvNotification.setVisibility(View.VISIBLE);
                    return;
                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PromoCodesActivity.this, LinearLayoutManager.VERTICAL, false);
                promoCodeAdapter = new PromoCodeAdapter(listPromoCode, PromoCodesActivity.this);
                promoCodeAdapter.notifyDataSetChanged();
                rcvListPromoCode.setAdapter(promoCodeAdapter);
                rcvListPromoCode.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PromoCodesActivity.this, "Get list users fail", Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void getListPromoFromDatabase(){


    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        btnAddPromoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromoCodesActivity.this, AddPromoCodeActivity.class);
                startActivityForResult(intent, MY_REQUEST_CODE_4);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    private void initViews() {
        rcvListPromoCode = findViewById(R.id.rcvListPromoCode);
        btnAddPromoCode = findViewById(R.id.btnAddPromoCode);
        btnBack = findViewById(R.id.btnBack);
        tvNotification = findViewById(R.id.tvNotification);

        getListPromoFromDatabase();
    }
}