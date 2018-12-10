package com.fit.maid.seminarskiv1.helper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by mrmai on 11/6/2018.
 */

public class MyFragmentUtils {

    public static void openAsReplace(Activity activity, int id, Fragment fragment){
        FragmentManager fragmentManager=activity.getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(id,fragment).commit();
    }

}
