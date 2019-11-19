package com.bionagro.bionintelligence.presentation.contacts;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bionagro.bionintelligence.R;
import com.bionagro.bionintelligence.databinding.FragmentContactsBinding;

import java.util.Objects;

public class ContactsFragment extends Fragment implements View.OnClickListener {
    FragmentContactsBinding binding;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ivInst.setOnClickListener(this);
        binding.ivTelegram.setOnClickListener(this);
        binding.ivFacebook.setOnClickListener(this);
        binding.ivVk.setOnClickListener(this);
        binding.ivTwitter.setOnClickListener(this);
        binding.ivYouTube.setOnClickListener(this);
        binding.tvContactsLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_inst: {
                Uri uri = Uri.parse("http://instagram.com/_u/bion_agro");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.instagram.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/bion_agro")));
                }
            }
            break;
            case R.id.iv_telegram: {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://telegram.me/Bion_Agro"));
                final String appName = "org.telegram.messenger";
                intent.setPackage(appName);
                startActivity(intent);
            }
            break;
            case R.id.iv_facebook: {
                try {
                    Objects.requireNonNull(getActivity()).getPackageManager()
                            .getPackageInfo("com.facebook.katana", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/BionAgro1"));
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/BionAgro1"));
                }
                startActivity(intent);
            }
            break;
            case R.id.iv_vk: {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("vkontakte://profile/%d", -186830163)));
                startActivity(intent);
            }
            break;
            case R.id.iv_twitter: {
                try {
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=hJG8sGmUaoMlQkI"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/hJG8sGmUaoMlQkI"));
                }
                startActivity(intent);
            }
            break;
            case R.id.iv_youTube: {
                intent = new Intent(Intent.ACTION_VIEW);
                try {
                    intent.setPackage("com.google.android.youtube");
                    intent.setData(Uri.parse("https://www.youtube.com/channel/UCQBnYpLPuMEJKIxOet-oxvw"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    intent.setData(Uri.parse("https://www.youtube.com/channel/UCQBnYpLPuMEJKIxOet-oxvw"));
                    startActivity(intent);
                }
            }
            break;
            case R.id.tv_contactsLocation: {
                intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://goo.gl/maps/xNTX79gHJP5UhfPR6"));
                startActivity(intent);
            }
        }
    }
}
