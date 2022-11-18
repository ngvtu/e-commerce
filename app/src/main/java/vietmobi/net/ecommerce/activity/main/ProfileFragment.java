package vietmobi.net.ecommerce.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.activity.AddressesActivity;
import vietmobi.net.ecommerce.activity.oder.MyOrdersActivity;
import vietmobi.net.ecommerce.activity.MyReviewsActivity;
import vietmobi.net.ecommerce.activity.PaymentActivity;
import vietmobi.net.ecommerce.activity.PromoCodesActivity;
import vietmobi.net.ecommerce.activity.settings.SettingsActivity;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    CardView cvMyOder, cvAddresses, cvPromoCodes, cvPaymentMethods, cvMyReviews, cvSettings;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        cvMyOder.setOnClickListener(this);
        cvAddresses.setOnClickListener(this);
        cvPromoCodes.setOnClickListener(this);
        cvPaymentMethods.setOnClickListener(this);
        cvMyReviews.setOnClickListener(this);
        cvSettings.setOnClickListener(this);
    }

    private void initViews(View view) {
        cvMyOder = view.findViewById(R.id.cvMyOder);
        cvAddresses = view.findViewById(R.id.cvAddresses);
        cvPromoCodes = view.findViewById(R.id.cvPromoCodes);
        cvPaymentMethods = view.findViewById(R.id.cvPaymentMethods);
        cvMyReviews = view.findViewById(R.id.cvMyReviews);
        cvSettings = view.findViewById(R.id.cvSettings);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvMyOder:
                Intent intent = new Intent(getContext(), MyOrdersActivity.class);
                startActivity(intent);
                break;
            case R.id.cvAddresses:
                Intent intent2 = new Intent(getContext(), AddressesActivity.class);
                startActivity(intent2);
                break;
            case R.id.cvPromoCodes:
                Intent intent3 = new Intent(getContext(), PromoCodesActivity.class);
                startActivity(intent3);
                break;
            case R.id.cvPaymentMethods:
                Intent intent4 = new Intent(getContext(), PaymentActivity.class);
                startActivity(intent4);
                break;
            case R.id.cvMyReviews:
                Intent intent5 = new Intent(getContext(), MyReviewsActivity.class);
                startActivity(intent5);
                break;
            case R.id.cvSettings:
                Intent intent6 = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent6);
                break;
        }
    }

}