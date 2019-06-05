package android.shopping_mall.pic.per.shoppingmall;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
//    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;


    RadioGroup radioGroup;

    private List<Fragment> fragmentList;

    private int position = 0;

    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ButterKnife.bind(this);
        initFragment();
        initRadioGroupListener();
    }

    private void initRadioGroupListener() {
        radioGroup.check(R.id.rb_home);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home :
                        position = 0;
                        break;
                    case R.id.rb_type :
                        position = 1;
                        break;
                    case R.id.rb_community :
                        position = 2;
                        break;
                    case R.id.rb_cart :
                        position = 3;
                        break;
                    case R.id.rb_user :
                        position = 4;
                        break;
                    default :
                        position = 0;
                        break;
                }

                Fragment baseFragment = getFragment(position);
                switchFragment(tempFragment, baseFragment);
            }
        });
    }

    private void switchFragment(Fragment fragment, Fragment nextFragment) {
        if (tempFragment == nextFragment) {
            return;
        }

        tempFragment = nextFragment;
        if (nextFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment != null) {
                transaction.hide(fragment);
            }

            if (!nextFragment.isAdded()) {
                transaction.add(R.id.frameLayout, nextFragment).commit();
            } else {
                transaction.show(nextFragment).commit();
            }
        }
    }

    private Fragment getFragment(int position) {
        if (fragmentList != null && fragmentList.size() > 0) {
            Fragment fragment = fragmentList.get(position);

            return fragment;
        }

        return null;
    }

    private void initFragment() {
        fragmentList = new ArrayList<>(5);

        // 添加具体的fragment
    }
}
