package com.fit.maid.seminarskiv1.fragmenti;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fit.maid.seminarskiv1.R;
import com.fit.maid.seminarskiv1.data.NoviSpasenSmjestajVM;
import com.fit.maid.seminarskiv1.data.PregedViseDetaljaVM;
import com.fit.maid.seminarskiv1.data.PregledDetaljaStanaVM;
import com.fit.maid.seminarskiv1.data.PregledSpasenogSmjestajaVM;
import com.fit.maid.seminarskiv1.helper.MyApiRequest;
import com.fit.maid.seminarskiv1.helper.MyRunnableBaza;
import com.fit.maid.seminarskiv1.helper.MySession;


public class DetaljiSmjestajaFragment extends Fragment {


    private TextView txtGrad;
    private TextView txtAdresa;

    private PregledDetaljaStanaVM detaljiStana;
    private int Kategorija=0;
    private int Grad=0;
    private int Spaseno=0;
    private TextView txtNaslov;
    private TextView txtBrojSoba;
    private TextView txtCijena;
    private TextView txtOpis;
    private ImageView slika;
    private ImageButton btnSpasi;


    PregledSpasenogSmjestajaVM spasenSmjestajVM= new PregledSpasenogSmjestajaVM();

    public static DetaljiSmjestajaFragment newInstance(PregledDetaljaStanaVM podaci, int kategorija, int grad,int spaseno) {
        DetaljiSmjestajaFragment fragment = new DetaljiSmjestajaFragment();
        Bundle args= new Bundle();
        args.putSerializable("podaci",podaci);
        args.putInt("kat",kategorija);
        args.putInt("gra",grad);
        args.putInt("spa",spaseno);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            detaljiStana=(PregledDetaljaStanaVM)getArguments().getSerializable("podaci");
            Kategorija=getArguments().getInt("kat");
            Grad=getArguments().getInt("gra");
            Spaseno=getArguments().getInt("spa");
        }

//        if (savedInstanceState!=null){
//            detaljiStana=(PregledDetaljaStanaVM) savedInstanceState.getSerializable("StanjeDetalji");
//            Kategorija=savedInstanceState.getInt("StanjeKat");
//            Grad=savedInstanceState.getInt("StanjeGra");
//            Spaseno=savedInstanceState.getInt("StanjeSpa");
//
//
//            FragmentTransaction transaction=getFragmentManager().beginTransaction();
//            transaction.replace(R.id.mjestoZaFragment, DetaljiSmjestajaFragment.newInstance(detaljiStana,Kategorija,Grad,Spaseno)).commit();
//        }

    }


//    @Override
//    public void onSaveInstanceState(Bundle outState) {//ovo se pozove kada se npr okrene mob
//        outState.putSerializable("StanjeDetalji",detaljiStana);
//        outState.putInt("StanjeKat",Kategorija);
//        outState.putInt("StanjeGra",Kategorija);
//        outState.putInt("StanjeSpa",Kategorija);
//        super.onSaveInstanceState(outState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        View view= inflater.inflate(R.layout.fragment_detalji_smjestaja, container, false);


        slika = view.findViewById(R.id.imgSlikaSmjestaja);
        txtNaslov = view.findViewById(R.id.txtNaslovSmjestaja);
        txtBrojSoba = view.findViewById(R.id.txtBrojSoba);
        txtCijena = view.findViewById(R.id.txtCijena);
        txtOpis = view.findViewById(R.id.txtOpis);
        txtGrad = view.findViewById(R.id.txtGrad);
        txtAdresa = view.findViewById(R.id.txtAdresa);

        txtNaslov.setText(detaljiStana.naziv);
        txtBrojSoba.setText(detaljiStana.brojSoba);
        txtCijena.setText(Double.toString(detaljiStana.cijena));
        txtOpis.setText(detaljiStana.opis);
        txtGrad.setText(detaljiStana.grad);
        txtAdresa.setText(detaljiStana.adresa);

        if(detaljiStana.id==10)
            slika.setImageResource(R.drawable.slikaa);
        if(detaljiStana.id==11)
            slika.setImageResource(R.drawable.slika3a);
        if(detaljiStana.id==12)
            slika.setImageResource(R.drawable.slika3a);
        if(detaljiStana.id==13)
            slika.setImageResource(R.drawable.slika4a);
        if(detaljiStana.id==14)
            slika.setImageResource(R.drawable.slika5a);
        if(detaljiStana.id==15)
            slika.setImageResource(R.drawable.slika6a);
        if(detaljiStana.id==16)
            slika.setImageResource(R.drawable.slika7a);
        if(detaljiStana.id==17)
            slika.setImageResource(R.drawable.slikaa);
        if(detaljiStana.id==18)
            slika.setImageResource(R.drawable.slika3a);
        if(detaljiStana.id==19)
            slika.setImageResource(R.drawable.slika4a);
        if(detaljiStana.id==20)
            slika.setImageResource(R.drawable.slika9a);
        if(detaljiStana.id==23)
            slika.setImageResource(R.drawable.slika10a);
        if(detaljiStana.id==24)
            slika.setImageResource(R.drawable.slika11a);
        if(detaljiStana.id==25)
            slika.setImageResource(R.drawable.slika12a);
        if(detaljiStana.id==26)
            slika.setImageResource(R.drawable.slika13a);
        if(detaljiStana.id==27)
            slika.setImageResource(R.drawable.slika14a);
        if(detaljiStana.id==28)
            slika.setImageResource(R.drawable.slika15a);
        if(detaljiStana.id==29)
            slika.setImageResource(R.drawable.slika16a);
        if(detaljiStana.id==30)
            slika.setImageResource(R.drawable.slika17a);
        if(detaljiStana.id==31)
            slika.setImageResource(R.drawable.slika18a);
        if(detaljiStana.id==32)
            slika.setImageResource(R.drawable.slika19a);




        ImageButton btnBack= view.findViewById(R.id.btnBack);
        btnSpasi = view.findViewById(R.id.btnSpasi);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnBack();
            }
        });
        btnSpasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnSpasi();
            }
        });

        provjeriJelSpasen();

        Button btnViseDetalja=view.findViewById(R.id.btnViseDetalja);
        Button btnOtvoriMapu=view.findViewById(R.id.btnOtvoriMapu);
        btnViseDetalja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnViseDetalja();
            }
        });
        btnOtvoriMapu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnOtvoriMapu();
            }
        });


        return view;
    }

    private void provjeriJelSpasen() {
        MyRunnableBaza<PregledSpasenogSmjestajaVM>myRunnableBaza= new MyRunnableBaza<PregledSpasenogSmjestajaVM>() {
            @Override
            public void Run(PregledSpasenogSmjestajaVM spasenSmjestaj) {
                spasenSmjestajVM=spasenSmjestaj;

                if(spasenSmjestajVM.id!=0){
                    btnSpasi.setImageResource(R.drawable.ic_baseline_star_24px);
                }
            }
        };

        MyApiRequest.get(getActivity(),"SpasenSmjestaj/Index/"+MySession.getKorisnik(getActivity()).id+"/"+detaljiStana.stanId,myRunnableBaza,false);



    }

    private void do_btnSpasi() {

        if(spasenSmjestajVM.id==0){
            NoviSpasenSmjestajVM novi= new NoviSpasenSmjestajVM();
            novi.korisnikId= MySession.getKorisnik(getActivity()).id;
            novi.stanId=detaljiStana.stanId;

           // spasiSmjestaj(novi);
            MyRunnableBaza<PregledSpasenogSmjestajaVM>myRunnableBaza= new MyRunnableBaza<PregledSpasenogSmjestajaVM>() {
                @Override
                public void Run(PregledSpasenogSmjestajaVM spasen) {
                    spasenSmjestajVM=spasen;
                }
            };

            MyApiRequest.post(getActivity(),"SpasenSmjestaj/Add",novi,myRunnableBaza,false);

            Toast.makeText(getActivity(),"Smještaj uspješno spašen",Toast.LENGTH_SHORT).show();
            btnSpasi.setImageResource(R.drawable.ic_baseline_star_24px);
        }else{
            MyRunnableBaza<NoviSpasenSmjestajVM>myRunnableBaza= new MyRunnableBaza<NoviSpasenSmjestajVM>() {
                @Override
                public void Run(NoviSpasenSmjestajVM noviSpasenSmjestajVM) {
                      spasenSmjestajVM.id=0;
                      spasenSmjestajVM.korisnikId=0;
                      spasenSmjestajVM.stanId=0;
                }
            };
            MyApiRequest.delete(getActivity(),"SpasenSmjestaj/Remove/"+MySession.getKorisnik(getActivity()).id+"/"+detaljiStana.stanId,myRunnableBaza,false);
            Toast.makeText(getActivity(),"Spašen smještaj uklonjen",Toast.LENGTH_SHORT).show();
            btnSpasi.setImageResource(R.drawable.ic_baseline_star_border_24px);
        }


    }

    private void do_btnBack() {
        ListaSmjestajaFragment fragment=new ListaSmjestajaFragment();
        Bundle args= new Bundle();
        args.putInt("kategorija",Kategorija);
        args.putInt("grad",Grad);
        args.putInt("spaseno",Spaseno);
        fragment.setArguments(args);

        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.mjestoZaFragment, fragment).commit();
    }

    private void do_btnOtvoriMapu() {

        String lok=txtGrad.getText().toString()+" "+txtAdresa.getText().toString();

        Uri lokUri=Uri.parse("geo:0,0?q="+lok);//parsirali smo string u geo search query URI

        Intent intent= new Intent(Intent.ACTION_VIEW,lokUri);

        if(intent.resolveActivity(getActivity().getPackageManager())!=null){ //ako smo nasli aktivity koji moze podrzati nas intent
            startActivity(intent);
        }
        else{
            Log.d("Implicintni intenti","Ne moze pronaci activity koji moze podrzati intent");
        }
    }

    private PregedViseDetaljaVM podaci= new PregedViseDetaljaVM();

    private void dobaviPodatkeIPokreniFragment(int detaljiId){
        MyRunnableBaza<PregedViseDetaljaVM> myRunnableBaza= new MyRunnableBaza<PregedViseDetaljaVM>() {
            @Override
            public void Run(PregedViseDetaljaVM t) {
                podaci=t;
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.mjestoZaFragment, ViseDetaljaFragment.newInstance(podaci,detaljiStana,Kategorija,Grad,Spaseno)).commit();

            }
        };
        MyApiRequest.get(getActivity(),"ViseDetalja/Index/"+detaljiId,myRunnableBaza,false);
    }

    private void do_btnViseDetalja() {

        dobaviPodatkeIPokreniFragment(detaljiStana.id);
    }

}
