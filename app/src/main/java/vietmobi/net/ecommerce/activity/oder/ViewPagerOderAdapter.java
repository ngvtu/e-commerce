package vietmobi.net.ecommerce.activity.oder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import vietmobi.net.ecommerce.activity.main.BagFragment;
import vietmobi.net.ecommerce.activity.main.FavoriteFragment;
import vietmobi.net.ecommerce.activity.main.HomeFragment;
import vietmobi.net.ecommerce.activity.main.ProfileFragment;
import vietmobi.net.ecommerce.activity.main.ShopFragment;

public class ViewPagerOderAdapter extends FragmentStatePagerAdapter {

    public ViewPagerOderAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DeliveredFragment();
            case 1:
                return new ProcessingFragment();
            case 2:
                return new CancelledFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
