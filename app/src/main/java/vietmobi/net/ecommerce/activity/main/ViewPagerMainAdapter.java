package vietmobi.net.ecommerce.activity.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import vietmobi.net.ecommerce.activity.main.BagFragment;
import vietmobi.net.ecommerce.activity.main.FavoriteFragment;
import vietmobi.net.ecommerce.activity.main.HomeFragment;
import vietmobi.net.ecommerce.activity.main.ProfileFragment;
import vietmobi.net.ecommerce.activity.main.ShopFragment;

public class ViewPagerMainAdapter extends FragmentStatePagerAdapter {
    public ViewPagerMainAdapter(@NonNull FragmentManager fm, int behavior) {
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
