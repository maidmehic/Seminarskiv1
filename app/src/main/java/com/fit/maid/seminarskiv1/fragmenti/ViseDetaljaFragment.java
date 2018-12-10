package com.fit.maid.seminarskiv1.fragmenti;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fit.maid.seminarskiv1.R;
import com.fit.maid.seminarskiv1.data.PregedViseDetaljaVM;
import com.fit.maid.seminarskiv1.data.PregledDetaljaStanaVM;


public class ViseDetaljaFragment extends Fragment {


    private TextView txtBrojTelefona;
    private TextView txtEmailAdresa;
    private CheckBox cbInternet;
    private CheckBox cbKablovska;
    private CheckBox cbKlima;
    private CheckBox cbParking;
    private CheckBox cbBalkon;
    private TextView txtPlacanje;
    private TextView txtVrstaGrijanja;
    private TextView txtGodinaIzgradnje;
    private TextView txtAdresa;
    private TextView txtDatumObjave;

    private PregedViseDetaljaVM podaci;
    private PregledDetaljaStanaVM detaljaStanaVM;
    int Kategorija=0;
    int Grad=0;
    int Spaseno=0;
    private CheckBox cbBlindirana;
    private CheckBox cbNadzor;
    private TextView txtVrstaPoda;
    private TextView txtPrimarnaOrjentacija;
    private TextView txtKvadrata;

    public static ViseDetaljaFragment newInstance(PregedViseDetaljaVM podaci, PregledDetaljaStanaVM detaljiStana, int kategorija, int grad,int spaseno) {
        ViseDetaljaFragment fragment = new ViseDetaljaFragment();
        Bundle args = new Bundle();
        args.putSerializable("podaci",podaci);
        args.putSerializable("detalji",detaljiStana);
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
            podaci=(PregedViseDetaljaVM)getArguments().getSerializable("podaci");
            detaljaStanaVM=(PregledDetaljaStanaVM)getArguments().getSerializable("detalji");
            Kategorija=getArguments().getInt("kat");
            Grad=getArguments().getInt("gra");
            Spaseno=getArguments().getInt("spa");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_vise_detalja, container, false);

        txtDatumObjave = view.findViewById(R.id.txtDatumObjave1);
        txtAdresa = view.findViewById(R.id.txtAdresa1);
        txtGodinaIzgradnje = view.findViewById(R.id.txtGodinaIzgradnje);
        txtVrstaGrijanja = view.findViewById(R.id.txtVrstaGrijanja);
        txtPlacanje = view.findViewById(R.id.txtPlacanje);
        cbBalkon = view.findViewById(R.id.cbBalkon);
        cbParking = view.findViewById(R.id.cbParking);
        cbKlima = view.findViewById(R.id.cbKlima);
        cbKablovska = view.findViewById(R.id.cbKablovska);
        cbInternet = view.findViewById(R.id.cbInternet);
        txtBrojTelefona = view.findViewById(R.id.txtBrojTelefona);
        txtEmailAdresa = view.findViewById(R.id.txtEmail);
        cbBlindirana = view.findViewById(R.id.cbBlindirana);
        cbNadzor = view.findViewById(R.id.cbVideo);
        txtVrstaPoda = view.findViewById(R.id.txtVrstaPoda);
        txtPrimarnaOrjentacija = view.findViewById(R.id.txtOrjentacija);
        txtKvadrata = view.findViewById(R.id.txtKvadrata);

        txtDatumObjave.setText(podaci.datumObjave);
        txtAdresa.setText(podaci.adresa);
        txtGodinaIzgradnje.setText(podaci.godIzgradnje);
        txtVrstaGrijanja.setText(podaci.vrstaGrijanja);
        txtPlacanje.setText(podaci.vrstaPlacanja);
        txtBrojTelefona.setText(podaci.telefon);
        txtEmailAdresa.setText(podaci.email);
        cbBalkon.setChecked(podaci.balkon);
        cbParking.setChecked(podaci.parking);
        cbKlima.setChecked(podaci.klima);
        cbKablovska.setChecked(podaci.kablovska);
        cbInternet.setChecked(podaci.internet);
        cbBlindirana.setChecked(podaci.blindiranaVrata);
        cbNadzor.setChecked(podaci.videoNadzor);
        txtVrstaPoda.setText(podaci.vrstaPoda);
        txtPrimarnaOrjentacija.setText(podaci.primarnaOrjentacija);
        txtKvadrata.setText(podaci.kvadrata);



        ImageButton btnBack= view.findViewById(R.id.btnBack1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnBack();
            }
        });
        txtBrojTelefona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_Nazovi();
            }
        });
        txtEmailAdresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_Email();
            }
        });

        return view;
    }

    private void do_btnBack() {
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.mjestoZaFragment, DetaljiSmjestajaFragment.newInstance(detaljaStanaVM,Kategorija,Grad,Spaseno)).commit();
    }

    private void do_Email() {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[]{txtEmailAdresa.getText().toString()});
        getActivity().startActivity(Intent.createChooser(emailIntent, "Pošaljite mail..."));
    }

    private void do_Nazovi() {
        String uri = "tel:" +txtBrojTelefona.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

}
