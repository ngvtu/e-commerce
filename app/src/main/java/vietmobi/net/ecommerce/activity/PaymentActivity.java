package vietmobi.net.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.activity.main.ItemsAdapter;
import vietmobi.net.ecommerce.adapter.AddressesAdapter;
import vietmobi.net.ecommerce.adapter.CardAdapter;
import vietmobi.net.ecommerce.database.AddressDatabase;
import vietmobi.net.ecommerce.database.CardDatabase;
import vietmobi.net.ecommerce.models.Card;

public class PaymentActivity extends AppCompatActivity {

    RecyclerView rcvListCard;
    FloatingActionButton btnAddCard;
    ImageView btnBack;
    TextView tvNotification;

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
                final android.app.Dialog dialog = new android.app.Dialog(view.getContext());
                String[] itemsCard = {"Master Card", "VISA"};

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottom_sheet_add_card);

                TextInputLayout layout_name_card = dialog.findViewById(R.id.layout_old_password);
                TextInputLayout layout_number_card = dialog.findViewById(R.id.layout_number_card);
                TextInputLayout layout_expire_date = dialog.findViewById(R.id.layout_expire_date);
                TextInputLayout layout_card_type = dialog.findViewById(R.id.layout_card_type);
                TextInputLayout layout_CVV = dialog.findViewById(R.id.layout_CVV);
                TextInputEditText edtNameCard = dialog.findViewById(R.id.edtNameCard);
                TextInputEditText edtNumberCard = dialog.findViewById(R.id.edtNumberCard);
                TextInputEditText edtExpireCard = dialog.findViewById(R.id.edtExpireCard);
                TextInputEditText edtCVV = dialog.findViewById(R.id.edtCVV);
                AppCompatAutoCompleteTextView edtTypeCard = dialog.findViewById(R.id.edtTypeCard);
                TextView btnAddCard = dialog.findViewById(R.id.btnAddCard);

                ArrayAdapter<String> adapterItemsCard;
                adapterItemsCard = new ArrayAdapter<String>(PaymentActivity.this, R.layout.line_list_type_card, itemsCard);

                edtTypeCard.setAdapter(adapterItemsCard);

                edtTypeCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = parent.getItemAtPosition(position).toString();
                        edtTypeCard.setText(item);
                    }
                });

                btnAddCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nameCard = edtNameCard.getText().toString().trim();
                        String numberCard = edtNumberCard.getText().toString().trim();
                        String expireCard = edtExpireCard.getText().toString().trim();
                        String CVV = edtCVV.getText().toString().trim();
                        String typeCard = edtTypeCard.getText().toString().trim();

                        Card card = new Card();
                        card.setCardName(nameCard);
                        card.setNumberCard(numberCard);
                        card.setExpiryDate(expireCard);
                        card.setCVV(CVV);
                        if (typeCard.equals("Master Card")) {
                            card.setMasterCard(true);
                            card.setVisa(false);
                        }
                        if (typeCard.equals("VISA")) {
                            card.setMasterCard(false);
                            card.setVisa(true);
                        }

                        CardDatabase.getInstance(v.getContext()).cardDAO().insertCard(card);
                        Toast.makeText(PaymentActivity.this, "Add Card complete", Toast.LENGTH_SHORT).show();

                        listCard = CardDatabase.getInstance(v.getContext()).cardDAO().getListCard();
                        if (listCard == null) {
                            return;
                        }

                        cardAdapter.setData(listCard);
                        dialog.dismiss();
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
    }

    private void addListCard() {
        listCard = new ArrayList<>();

        tvNotification.setVisibility(View.GONE);
        listCard = CardDatabase.getInstance(this).cardDAO().getListCard();

        if (listCard.isEmpty()) {
            tvNotification.setVisibility(View.VISIBLE);
            return;
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cardAdapter = new CardAdapter(listCard, this);
        rcvListCard.setAdapter(cardAdapter);
        rcvListCard.setLayoutManager(linearLayoutManager);
    }

    private void initViews() {
        rcvListCard = findViewById(R.id.rcvListCard);
        btnAddCard = findViewById(R.id.btnAddCard);
        btnBack = findViewById(R.id.btnBack);
        tvNotification = findViewById(R.id.tvNotification);
    }
}