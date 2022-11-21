package vietmobi.net.ecommerce.activity.main;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vietmobi.net.ecommerce.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomNavigationView bottomNavigation;
    ViewPager viewPager;
    Toolbar toolbar;
    private boolean doubleBackToExitPressedOnce = false;
    private MenuItem menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getSupportActionBar();
        setSupportActionBar(toolbar);
        addEvents();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
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
        ViewPagerMainAdapter viewPagerAdapter = new ViewPagerMainAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        super.onCreateOptionsMenu(menu);
        menuItem = menu.findItem(R.id.itemSearch);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);

        SearchView searchView = (SearchView) menuItem.getActionView();
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        menuItem.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return true;
    }

    private void initViews() {
        bottomNavigation = findViewById(R.id.bottomNavigation);
        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
    }

    @Override
    public void onClick(View view) {

    }
}