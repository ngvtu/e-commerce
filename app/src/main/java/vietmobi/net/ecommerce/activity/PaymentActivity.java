package vietmobi.net.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.adapter.AddressesAdapter;
import vietmobi.net.ecommerce.adapter.CardAdapter;
import vietmobi.net.ecommerce.database.AddressDatabase;
import vietmobi.net.ecommerce.database.CardDatabase;
import vietmobi.net.ecommerce.models.Card;

public class PaymentActivity extends AppCompatActivity {

    RecyclerView rcvListCard;
    FloatingActionButton btnAddCard;
    ImageView btnBack;

    List<Card> listCard;
    CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initViews();
        addListCard();
        addEvents();
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentActivity.this, "Add jeje ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addListCard() {
        listCard = new ArrayList<>();
        listCard = CardDatabase.getInstance(this).cardDAO().getListCard();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cardAdapter = new CardAdapter(listCard, this);
        rcvListCard.setAdapter(cardAdapter);
        rcvListCard.setLayoutManager(linearLayoutManager);
    }

    private void initViews() {
        rcvListCard = findViewById(R.id.rcvListCard);
        btnAddCard = findViewById(R.id.btnAddCard);
        btnBack = findViewById(R.id.btnBack);
    }
}