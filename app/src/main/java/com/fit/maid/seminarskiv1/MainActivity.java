package com.fit.maid.seminarskiv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fit.maid.seminarskiv1.data.PregledLogiranogKorisnikaVM;
import com.fit.maid.seminarskiv1.helper.MySession;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        PregledLogiranogKorisnikaVM x = MySession.getKorisnik(this);


        if(x==null){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }else{
            startActivity(new Intent(this,GlavniActivity.class));
            finish();
        }
    }
}
