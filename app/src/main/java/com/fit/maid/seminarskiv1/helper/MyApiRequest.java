package com.fit.maid.seminarskiv1.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.lang.reflect.Type;

/**
 * Created by mrmai on 11/25/2018.
 */

public class MyApiRequest {

    public static <T> void request(final Activity activity, final String urlAction, final MyUrlConnection.HttpMethod httpMethod, final Object postObject, final MyRunnableBaza<T> myRunnableBaza, final boolean progres) {

        new AsyncTask<Void, Void, MyApiResult>() {

            private ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                if(progres==true) {
                    dialog = ProgressDialog.show(activity, "Učitavanje", "Molimo sačekajte...");
                }
            }

            @Override
            protected MyApiResult doInBackground(Void... voids) {

                String jsonPostObject=postObject==null?null:MyGson.build().toJson(postObject);

                return MyUrlConnection.request(MyConfig.baseUri+urlAction,httpMethod,jsonPostObject,"application/json");
            }

            @Override
            protected void onPostExecute(MyApiResult result) {
                if(progres==true) { dialog.dismiss();}

                if(result.isException){
                    View parentLayout=activity.findViewById(android.R.id.content);

                    if(result.resultCode==0){
                        Snackbar.make(parentLayout,"Greska u komunikaciji sa serverom",Snackbar.LENGTH_SHORT).show();

                    }
                    else {
                         Snackbar.make(parentLayout,result.errorMessage,Snackbar.LENGTH_SHORT).show();
                         }
                }

                else {

                    Type genericType=myRunnableBaza.getGenericType();

                    try {
                        T x= MyGson.build().fromJson(result.value,genericType);
                        myRunnableBaza.Run(x);
                    }catch (Exception e){
                        View parentLayout=activity.findViewById(android.R.id.content);
                        Snackbar.make(parentLayout,"Greska: "+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                    }

                }
            }
        }.execute();

    }

    public static <T>void get(final Activity activity,final String urlAction, final MyRunnableBaza<T>myCallBack,boolean progres){
        request(activity,urlAction,MyUrlConnection.HttpMethod.GET,null,myCallBack,progres);
    }

    public static <T>void delete(final Activity activity,final String urlAction, final MyRunnableBaza<T>myCallBack,boolean progres){
        request(activity,urlAction,MyUrlConnection.HttpMethod.DELETE,null,myCallBack,progres);
    }

    public static <T>void post(final Activity activity,final String urlAction,Object postObject, final MyRunnableBaza<T>myCallBack,boolean progres){
        request(activity,urlAction,MyUrlConnection.HttpMethod.POST,postObject,myCallBack,progres);
    }

    public static <T>void put(final Activity activity,final String urlAction,Object postObject, final MyRunnableBaza<T>myCallBack,boolean progres){
        request(activity,urlAction,MyUrlConnection.HttpMethod.PUT,postObject,myCallBack,progres);
    }
}
