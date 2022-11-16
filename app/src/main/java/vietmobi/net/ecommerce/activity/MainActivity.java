package vietmobi.net.ecommerce.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vietmobi.net.ecommerce.R;
import vietmobi.net.ecommerce.adapter.ViewPagerAdapter;
import vietmobi.net.ecommerce.fragment.BagFragment;
import vietmobi.net.ecommerce.fragment.FavoriteFragment;
import vietmobi.net.ecommerce.fragment.HomeFragment;
import vietmobi.net.ecommerce.fragment.ProfileFragment;
import vietmobi.net.ecommerce.fragment.ShopFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        addEvents();
    }

    private void addEvents() {
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
//                        HomeFragment homeFragment = (HomeFragment) viewPager.getAdapter().instantiateItem(viewPager, 0);
                        break;
                    case R.id.shop:
                        viewPager.setCurrentItem(1);
//                        ShopFragment shopFragment = (ShopFragment) viewPager.getAdapter().instantiateItem(viewPager, 1);
                        break;
                    case R.id.bag:
                        viewPager.setCurrentItem(2);
//                        BagFragment bagFragment = (BagFragment) viewPager.getAdapter().instantiateItem(viewPager, 2);
                        break;
                    case R.id.favorite:
                        viewPager.setCurrentItem(3);
//                        FavoriteFragment favoriteFragment = (FavoriteFragment) viewPager.getAdapter().instantiateItem(viewPager, 3);
                        break;
                    case R.id.profile:
                        viewPager.setCurrentItem(4);
//                        ProfileFragment profileFragment = (ProfileFragment) viewPager.getAdapter().instantiateItem(viewPager, 4);
                        break;
                }
                return true;
            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(4);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigation.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigation.getMenu().findItem(R.id.shop).setChecked(true);
                        break;
                    case 2:
                        bottomNavigation.getMenu().findItem(R.id.bag).setChecked(true);
                        break;
                    case 3:
                        bottomNavigation.getMenu().findItem(R.id.favorite).setChecked(true);
                        break;
                    case 4:
                        bottomNavigation.getMenu().findItem(R.id.profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        bottomNavigation = findViewById(R.id.bottomNavigation);
        viewPager = findViewById(R.id.viewPager);
    }
}