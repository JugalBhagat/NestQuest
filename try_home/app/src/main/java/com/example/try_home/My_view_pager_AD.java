package com.example.try_home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.try_home.Frags.Frag_Dashboard;
import com.example.try_home.Frags.Frag_Profile;

public class My_view_pager_AD extends FragmentStateAdapter {
    public My_view_pager_AD(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 0:
                return new Frag_Dashboard();
            case 1:
                return new Frag_Profile();
            default:
                return new Frag_Dashboard();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
