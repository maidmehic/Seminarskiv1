package com.fit.maid.seminarskiv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fit.maid.seminarskiv1.data.PregledLogiranogKorisnikaVM;
import com.fit.maid.seminarskiv1.helper.MyApiRequest;
import com.fit.maid.seminarskiv1.helper.MyRunnableBaza;
import com.fit.maid.seminarskiv1.helper.MySession;

public class RegistracijaActivity extends AppCompatActivity {

    private EditText ime;
    private EditText prezime;
    private EditText email;
    private EditText lozinka;
    private PregledLogiranogKorisnikaVM novi= new PregledLogiranogKorisnikaVM();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        ((AppCompatActivity) this).getSupportActionBar().hide();


        ime = findViewById(R.id.txtIme);
        prezime = findViewById(R.id.txtPrezime);
        email = findViewById(R.id.txtEmailNovi);
        lozinka = findViewById(R.id.txtLozinka);

        Button btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnRegister();
            }
        });


    }
    private PregledLogiranogKorisnikaVM izBaze;

    private void do_btnRegister() {
        novi.ime=ime.getText().toString();
        novi.prezime=prezime.getText().toString();
        novi.email=email.getText().toString();
        novi.password=lozinka.getText().toString();

        MyRunnableBaza<PregledLogiranogKorisnikaVM>myRunnableBaza= new MyRunnableBaza<PregledLogiranogKorisnikaVM>() {
            @Override
            public void Run(PregledLogiranogKorisnikaVM pregledLogiranogKorisnikaVM) {
                izBaze=pregledLogiranogKorisnikaVM;
                postaviKorisnika();
            }
        };

        MyApiRequest.post(this,"Korisnik/Add",novi,myRunnableBaza,false);
    }

    private void postaviKorisnika() {
        MySession.setKorisnik(this,izBaze);
        finish();
        startActivity(new Intent(this,GlavniActivity.class));
    }
}
