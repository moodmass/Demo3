package com.hy.mdms.demo3;

import android.app.Fragment;

/**
 * Created by Administrator on 2016/9/6.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
