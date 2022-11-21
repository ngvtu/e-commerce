package vietmobi.net.ecommerce.activity.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.models.Items;


public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageView imvBgHome;
    TextView btnViewAllItemNew, btnViewAllItemSale;
    RecyclerView rcvListItemSale, rcvListItemNew;


    private List<Items> listItems;
    ItemsAdapter itemsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);
        addItemsNew();
        addItemsSale();


        addEvents();
        return view;
    }



    private void addItemsSale() {
        listItems = new ArrayList<>();
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        itemsAdapter = new ItemsAdapter(listItems, getActivity());
        rcvListItemSale.setAdapter(itemsAdapter);
        rcvListItemSale.setLayoutManager(linearLayoutManager);
    }

    private void addItemsNew() {
        listItems = new ArrayList<>();
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));
        listItems.add(new Items(R.drawable.img, "Shirt", "LIME", 30F, 40F, 30F));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        itemsAdapter = new ItemsAdapter(listItems, getActivity());
        rcvListItemNew.setAdapter(itemsAdapter);
        rcvListItemNew.setLayoutManager(linearLayoutManager);
    }

    private void addEvents() {
        btnViewAllItemNew.setOnClickListener(this);
        btnViewAllItemSale.setOnClickListener(this);

    }

    private void initViews(View view) {
        imvBgHome = view.findViewById(R.id.imvBgHome);
        btnViewAllItemNew = view.findViewById(R.id.btnViewAllItemNew);
        btnViewAllItemSale = view.findViewById(R.id.btnViewAllItemSale);
        rcvListItemSale = view.findViewById(R.id.rcvListItemSale);
        rcvListItemNew = view.findViewById(R.id.rcvListItemNew);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnViewAllItemNew:
                Intent intent = new Intent(getContext(), ViewAllItemsNew.class);
                startActivity(intent);
                break;
            case R.id.btnViewAllItemSale:
                Intent intent2 = new Intent(getContext(), ViewAllItemsSale.class);
                startActivity(intent2);
                break;
        }
    }
}