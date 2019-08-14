package com.example.bionintelligence.presentation.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.bionintelligence.R;
import com.example.bionintelligence.databinding.ActivityMainBinding;
import com.example.bionintelligence.presentation.calculator.CalculatorFragment;
import com.example.bionintelligence.presentation.contacts.ContactsFragment;
import com.example.bionintelligence.presentation.info.InfoFragment;
import com.example.bionintelligence.presentation.settings.SettingsFragmentMain;

public class MainActivity extends AppCompatActivity implements MainView {
    private CalculatorFragment calculatorFragment;
    private ContactsFragment contactsFragment;
    private InfoFragment infoFragment;
    private SettingsFragmentMain settingsFragmentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //todo для создания фрагмента принято деать отдельный статический метод типа getInstance()
        //todo чекни https://stackoverflow.com/questions/9245408/best-practice-for-instantiating-a-new-android-fragment
        calculatorFragment = new CalculatorFragment();
        settingsFragmentMain = new SettingsFragmentMain();
        contactsFragment = new ContactsFragment();
        infoFragment = new InfoFragment();
        addFragment();


        binding.bottomMenu.setOnBottomMenuClickListener(position -> {
            switch (position) {
                case 0: {
                    showFragment(calculatorFragment);
                    if (calculatorFragment.isAdded())
                        calculatorFragment.refresh();
                    break;
                }
                case 1:
                    showFragment(settingsFragmentMain);
                    break;
                case 2:
                    showFragment(infoFragment);
                    break;
                case 3:
                    showFragment(contactsFragment);
                    break;
            }
        });

//        binding.bottomNavigationView
//                .setOnNavigationItemSelectedListener(menuItem -> {
//                    switch (menuItem.getItemId()) {
//                        case R.id.action_calculator: {
//                            showFragment(calculatorFragment);
//                            if (calculatorFragment.isAdded())
//                                calculatorFragment.refresh();
//                            break;
//                        }
//                        case R.id.action_settings:
//                            showFragment(settingsFragmentMain);
//                            break;
//                        case R.id.action_product:
//                            showFragment(infoFragment);
//                            break;
//                        case R.id.action_contacts:
//                            showFragment(contactsFragment);
//                            break;
//                    }
//                    return true;
//                });
    }

    private void addFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, infoFragment).hide(infoFragment)
                .add(R.id.container, contactsFragment).hide(contactsFragment)
                .add(R.id.container, settingsFragmentMain).hide(settingsFragmentMain)
                .add(R.id.container, calculatorFragment)
                .commit();
    }

    private void showFragment(Fragment fragment) {
        for (Fragment f : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().hide(f).commit();
        }
        for (Fragment f : getSupportFragmentManager().getFragments()) {
            if (f == fragment) {
                getSupportFragmentManager().beginTransaction().show(f).commit();
            }
        }
    }
}
