package vietmobi.net.ecommerce.activity.oder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vietmobi.net.ecommerce.R;

public class MyOrdersActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView btnBack;
    ColorStateList def;
    ViewPager viewPager;
    TextView item1, item2, item3, select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        initViews();
        addEvents();
    }

    private void addEvents() {
        btnBack.setOnClickListener(this);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        select.setOnClickListener(this);
        def = item2.getTextColors();

        ViewPagerOderAdapter viewPagerOderAdapter = new ViewPagerOderAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerOderAdapter);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        select.animate().x(0).setDuration(100);
                        item1.setTextColor(Color.WHITE);
                        item2.setTextColor(def);
                        item3.setTextColor(def);
                        viewPager.setCurrentItem(0);

                        break;
                    case 1:
                        item1.setTextColor(def);
                        item2.setTextColor(Color.WHITE);
                        item3.setTextColor(def);
                        int size = item2.getWidth();
                        select.animate().x(size).setDuration(100);
                        viewPager.setCurrentItem(1);

                        break;
                    case 2:
                        item1.setTextColor(def);
                        item3.setTextColor(Color.WHITE);
                        item2.setTextColor(def);
                        int size2 = item2.getWidth() * 2;
                        select.animate().x(size2).setDuration(100);
                        viewPager.setCurrentItem(2);

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        select = findViewById(R.id.select);
        viewPager = findViewById(R.id.viewPager);
    }

    @Override
    public void onClick(View view) {
        ViewPagerOderAdapter viewPagerOderAdapter = new ViewPagerOderAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerOderAdapter);
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.item1:
                select.animate().x(0).setDuration(100);
                item1.setTextColor(Color.WHITE);
                item2.setTextColor(def);
                item3.setTextColor(def);
                viewPager.setCurrentItem(0);

                break;
            case R.id.item2:
                item1.setTextColor(def);
                item2.setTextColor(Color.WHITE);
                item3.setTextColor(def);
                int size = item2.getWidth();
                select.animate().x(size).setDuration(100);
                viewPager.setCurrentItem(0);
                break;
            case R.id.item3:
                item1.setTextColor(def);
                item3.setTextColor(Color.WHITE);
                item2.setTextColor(def);
                int size2 = item2.getWidth() * 2;
                select.animate().x(size2).setDuration(100);
                viewPager.setCurrentItem(2);
                break;
        }
    }
}