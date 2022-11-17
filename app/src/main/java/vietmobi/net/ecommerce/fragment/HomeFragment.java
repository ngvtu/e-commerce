package vietmobi.net.ecommerce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vietmobi.net.ecommerce.R;


public class HomeFragment extends Fragment {

    ImageView imvBgHome;
    TextView btnViewAllItemNew, btnViewAllItemSale;
    RecyclerView rcvListItemSale, rcvListItemNew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);
        return view;
    }

    private void initViews(View view) {
        imvBgHome = view.findViewById(R.id.imvBgHome);
        btnViewAllItemNew = view.findViewById(R.id.btnViewAllItemNew);
        btnViewAllItemSale = view.findViewById(R.id.btnViewAllItemSale);
        rcvListItemSale = view.findViewById(R.id.rcvListItemSale);
        rcvListItemNew = view.findViewById(R.id.rcvListItemNew);
    }
}