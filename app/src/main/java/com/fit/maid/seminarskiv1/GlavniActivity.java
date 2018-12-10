package com.fit.maid.seminarskiv1;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fit.maid.seminarskiv1.data.PregedGradovaVM;
import com.fit.maid.seminarskiv1.fragmenti.ListaSmjestajaFragment;
import com.fit.maid.seminarskiv1.fragmenti.LokacijaFragment;
import com.fit.maid.seminarskiv1.helper.MyRunnable;
import com.fit.maid.seminarskiv1.helper.MySession;

public class GlavniActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        TextView ime=navigationView.findViewById(R.id.imePrezimeLogiranog);
        TextView email=navigationView.findViewById(R.id.emailAdresaLogiranog);

        ime.setText(MySession.getKorisnik(this).ime+" "+MySession.getKorisnik(this).prezime);
        email.setText(MySession.getKorisnik(this).email);

        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.mjestoZaFragment, ListaSmjestajaFragment.newInstance()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.glavni, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_novo) {

            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.mjestoZaFragment, ListaSmjestajaFragment.newInstance()).commit();


        } else if (id == R.id.nav_lokacija) {
            MyRunnable<PregedGradovaVM.Rows>callback= new MyRunnable<PregedGradovaVM.Rows>() {
                @Override
                public void Run(PregedGradovaVM.Rows grad) {

                    ListaSmjestajaFragment fragment=new ListaSmjestajaFragment();
                    Bundle args= new Bundle();
                    args.putInt("kategorija",0);
                    args.putInt("grad",grad.id);
                    args.putInt("spaseno",0);
                    fragment.setArguments(args);

                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
                    transaction.replace(R.id.mjestoZaFragment, fragment).commit();
                }
            };

            LokacijaFragment fragment= LokacijaFragment.newInstance(callback);
            fragment.show(getFragmentManager(),"tag");

        } else if (id == R.id.nav_spremljeno) {

            ListaSmjestajaFragment fragment=new ListaSmjestajaFragment();
            Bundle args= new Bundle();
            args.putInt("kategorija",0);
            args.putInt("grad",0);
            args.putInt("spaseno",1);
            fragment.setArguments(args);

            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.mjestoZaFragment, fragment).commit();


        } else if (id == R.id.nav_stan) {
                ListaSmjestajaFragment fragment=new ListaSmjestajaFragment();
                Bundle args= new Bundle();
                args.putInt("kategorija",1);
                args.putInt("grad",0);
                args.putInt("spaseno",0);
            fragment.setArguments(args);

            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.mjestoZaFragment, fragment).commit();

        } else if (id == R.id.nav_kuca) {
            ListaSmjestajaFragment fragment=new ListaSmjestajaFragment();
            Bundle args= new Bundle();
            args.putInt("kategorija",2);
            args.putInt("grad",0);
            args.putInt("spaseno",0);
            fragment.setArguments(args);

            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.mjestoZaFragment, fragment).commit();

        } else if (id == R.id.nav_apartman) {
            ListaSmjestajaFragment fragment=new ListaSmjestajaFragment();
            Bundle args= new Bundle();
            args.putInt("kategorija",4);
            args.putInt("grad",0);
            args.putInt("spaseno",0);
            fragment.setArguments(args);

            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.mjestoZaFragment, fragment).commit();

        } else if (id == R.id.nav_soba) {
            ListaSmjestajaFragment fragment=new ListaSmjestajaFragment();
            Bundle args= new Bundle();
            args.putInt("kategorija",3);
            args.putInt("grad",0);
            args.putInt("spaseno",0);
            fragment.setArguments(args);

            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.mjestoZaFragment, fragment).commit();

        }else if (id == R.id.nav_logout) {
            MySession.setKorisnik(this,null);
            finish();
           startActivity(new Intent(this,LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
