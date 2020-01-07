package com.example.oekonav.ui.mailbox;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oekonav.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;


/**
 */
public class MailboxFragment extends Fragment {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    MailboxViewModel mailboxViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mailboxViewModel =
                ViewModelProviders.of(this).get(MailboxViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mailbox, container, false);

        return root;
    }
}
