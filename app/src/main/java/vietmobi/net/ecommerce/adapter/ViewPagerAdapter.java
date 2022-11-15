package vietmobi.net.ecommerce.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import vietmobi.net.ecommerce.fragment.BagFragment;
import vietmobi.net.ecommerce.fragment.FavoriteFragment;
import vietmobi.net.ecommerce.fragment.HomeFragment;
import vietmobi.net.ecommerce.fragment.ProfileFragment;
import vietmobi.net.ecommerce.fragment.ShopFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ShopFragment();
            case 2:
                return new BagFragment();
            case 3:
                return new FavoriteFragment();
            case 4:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
