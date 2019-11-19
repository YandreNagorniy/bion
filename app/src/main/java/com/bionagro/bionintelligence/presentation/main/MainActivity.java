package com.bionagro.bionintelligence.presentation.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.bionagro.bionintelligence.R;
import com.bionagro.bionintelligence.databinding.ActivityMainBinding;
import com.bionagro.bionintelligence.presentation.calculator.CalculatorFragment;
import com.bionagro.bionintelligence.presentation.contacts.ContactsFragment;
import com.bionagro.bionintelligence.presentation.info.InfoFragment;
import com.bionagro.bionintelligence.presentation.settings.SettingsFragmentMain;

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
        calculatorFragment = new CalculatorFragment();
        settingsFragmentMain = new SettingsFragmentMain();
        contactsFragment = new ContactsFragment();
        infoFragment = new InfoFragment();
        addFragment();

        binding.bottomMenu.setItemIconTintList(null);
        binding.bottomMenu.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_calculator: {
                    showFragment(calculatorFragment);
                    if (calculatorFragment.isAdded())
                        calculatorFragment.refresh();
                    break;
                }
                case R.id.action_settings:
                    showFragment(settingsFragmentMain);
                    break;
                case R.id.action_product:
                    showFragment(infoFragment);
                    break;
               default:
                    showFragment(contactsFragment);
                    break;
            }
            return true;
        });

//        binding.bottomMenu.setOnBottomMenuClickListener(position -> {
//            switch (position) {
//                case 0: {
//                    showFragment(calculatorFragment);
//                    if (calculatorFragment.isAdded())
//                        calculatorFragment.refresh();
//                    break;
//                }
//                case 1:
//                    showFragment(settingsFragmentMain);
//                    break;
//                case 2:
//                    showFragment(infoFragment);
//                    break;
//                case 3:
//                    showFragment(contactsFragment);
//                    break;
//            }
//        });
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
