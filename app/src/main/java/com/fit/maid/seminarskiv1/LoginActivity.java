package com.fit.maid.seminarskiv1;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fit.maid.seminarskiv1.data.PregledDetaljaStanaVM;
import com.fit.maid.seminarskiv1.data.PregledLogiranogKorisnikaVM;
import com.fit.maid.seminarskiv1.fragmenti.DetaljiSmjestajaFragment;
import com.fit.maid.seminarskiv1.helper.MyApiRequest;
import com.fit.maid.seminarskiv1.helper.MyRunnableBaza;
import com.fit.maid.seminarskiv1.helper.MySession;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((AppCompatActivity) this).getSupportActionBar().hide();


        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        register = findViewById(R.id.register);

        Button btnLogin=findViewById(R.id.btnLogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_register();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnLogin();
            }
        });

    }

    private void do_register() {
        finish();
        startActivity(new Intent(this,RegistracijaActivity.class));
    }

    private PregledLogiranogKorisnikaVM korisnik= new PregledLogiranogKorisnikaVM();

    private void do_btnLogin() {
        String email=txtEmail.getText().toString();
        String password=txtPassword.getText().toString();

        if(email.equals("") || password.equals("")){
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Email adresa i lozinka su obavezni", Snackbar.LENGTH_LONG).show();
        }else {
           // provjeriKorisnikaTask(email, password);{

                MyRunnableBaza<PregledLogiranogKorisnikaVM> myRunnableBaza= new MyRunnableBaza<PregledLogiranogKorisnikaVM>() {
                    @Override
                    public void Run(PregledLogiranogKorisnikaVM t) {
                        korisnik=t;
                        provjeri(korisnik);
                    }
                };
                MyApiRequest.get(this,"Korisnik/LoginCheck/"+email+"/"+password,myRunnableBaza,false);
            }
        }


    private void provjeriKorisnikaTask(String email, String password){

    }
    private void provjeri(PregledLogiranogKorisnikaVM korisnik){

        if (korisnik.id == 0) {

            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Pogrešan email ili password", Snackbar.LENGTH_LONG).show();
        }else{
        MySession.setKorisnik(this, this.korisnik);
        startActivity(new Intent(this, GlavniActivity.class));
        finish();
        }
    }
}
