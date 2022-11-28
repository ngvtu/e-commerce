package vietmobi.net.ecommerce.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import vietmobi.net.ecommerce.R;

public class AddItemsActivity extends AppCompatActivity {
    ImageView btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
    }
}