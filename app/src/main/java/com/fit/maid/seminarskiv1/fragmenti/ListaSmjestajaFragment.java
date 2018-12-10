package com.fit.maid.seminarskiv1.fragmenti;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fit.maid.seminarskiv1.R;
import com.fit.maid.seminarskiv1.data.PregledDetaljaStanaVM;
import com.fit.maid.seminarskiv1.data.PregledStanovaVM;
import com.fit.maid.seminarskiv1.helper.MyApiRequest;
import com.fit.maid.seminarskiv1.helper.MyRunnableBaza;
import com.fit.maid.seminarskiv1.helper.MySession;

import org.w3c.dom.Text;

public class ListaSmjestajaFragment extends Fragment {

    private ListView stanoviListView;
    private int Kategorija=0;
    private int Grad=0;
    private int Spaseno=0;
    private TextView txtPoruka;

    public static ListaSmjestajaFragment newInstance() {
        ListaSmjestajaFragment fragment = new ListaSmjestajaFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_lista_smjestaja, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        stanoviListView = view.findViewById(R.id.stanoviListView);
        txtPoruka = view.findViewById(R.id.porukaPrazno);

        if (getArguments() != null) {
            Kategorija = getArguments().getInt("kategorija");
            Grad = getArguments().getInt("grad");
            Spaseno=getArguments().getInt("spaseno");

            if (Kategorija != 0) {
                napuniPodatkeTaskPoKategoriji();
            }

            if (Grad != 0) {
                napuniPodatkeTaskPoGradu();
            }
            if(Spaseno!=0){
                napuniPodatkeTaskSpaseno();
            }

            if (Kategorija == 0 && Grad == 0 &&Spaseno==0) {
                napuniPodatkeTask();
            }
        }else{
            napuniPodatkeTask();
        }
        return view;
    }

    private void napuniPodatkeTaskSpaseno() {
        MyRunnableBaza<PregledStanovaVM> myRunnableBaza= new MyRunnableBaza<PregledStanovaVM>() {
            @Override
            public void Run(PregledStanovaVM t) {
                if(t.podaci.size()==0){
                    txtPoruka.setText("Nema spašenog smještaja");
                }else{
                napuniPodatke(t);}
            }
        };
        MyApiRequest.get(getActivity(),"Stan/PregledSpasenogSmjestaja/"+ MySession.getKorisnik(getActivity()).id,myRunnableBaza,false);
    }

    private void napuniPodatkeTaskPoGradu() {
        MyRunnableBaza<PregledStanovaVM> myRunnableBaza= new MyRunnableBaza<PregledStanovaVM>() {
            @Override
            public void Run(PregledStanovaVM t) {
                napuniPodatke(t);
            }
        };
        MyApiRequest.get(getActivity(),"Stan/PregledPoGradovima/"+Grad,myRunnableBaza,false);
    }

    private void napuniPodatkeTaskPoKategoriji(){
        MyRunnableBaza<PregledStanovaVM> myRunnableBaza= new MyRunnableBaza<PregledStanovaVM>() {
            @Override
            public void Run(PregledStanovaVM t) {
                napuniPodatke(t);
            }
        };
        MyApiRequest.get(getActivity(),"Stan/PregledPoKategorijama/"+Kategorija,myRunnableBaza,false);
    }

    private void napuniPodatkeTask(){
        MyRunnableBaza<PregledStanovaVM> myRunnableBaza= new MyRunnableBaza<PregledStanovaVM>() {
            @Override
            public void Run(PregledStanovaVM t) {
                napuniPodatke(t);
            }
        };
        MyApiRequest.get(getActivity(),"Stan/Index",myRunnableBaza,true);
    }


   private PregledDetaljaStanaVM podaci= new PregledDetaljaStanaVM();

   private void dobaviPodatkeIPokreniFragment(int stanId){
       MyRunnableBaza<PregledDetaljaStanaVM> myRunnableBaza= new MyRunnableBaza<PregledDetaljaStanaVM>() {
           @Override
           public void Run(PregledDetaljaStanaVM t) {
                podaci=t;

               FragmentTransaction transaction=getFragmentManager().beginTransaction();
               transaction.replace(R.id.mjestoZaFragment, DetaljiSmjestajaFragment.newInstance(podaci,Kategorija,Grad,Spaseno)).commit();

//               DetaljiSmjestajaFragment fragment= DetaljiSmjestajaFragment.newInstance(podaci);
//               fragment.show(getFragmentManager(),"tag");
           }
       };
       MyApiRequest.get(getActivity(),"DetaljiStana/Index/"+stanId,myRunnableBaza,false);
    }

    private void napuniPodatke(final PregledStanovaVM t) {


        stanoviListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return t.podaci.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if(convertView==null){
                    LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView=inflater.inflate(R.layout.stan_stavka,parent,false);
                }
                PregledStanovaVM.Rows stan=t.podaci.get(position);

                ImageView slika=convertView.findViewById(R.id.imgSlika);
                TextView naslov=convertView.findViewById(R.id.txtNaslov);
                TextView datumObjave=convertView.findViewById(R.id.txtDatumObjave);
                TextView cijena=convertView.findViewById(R.id.txtCijena);

                naslov.setText(stan.naziv);
                datumObjave.setText(stan.datumObjave);
                cijena.setText(Double.toString(stan.cijena));

               if(stan.id==10)
                   slika.setImageResource(R.drawable.slika);
                if(stan.id==11)
                    slika.setImageResource(R.drawable.slika2);
                if(stan.id==12)
                    slika.setImageResource(R.drawable.slika3);
                if(stan.id==13)
                    slika.setImageResource(R.drawable.slika4);
                if(stan.id==14)
                    slika.setImageResource(R.drawable.slika5);
                if(stan.id==15)
                    slika.setImageResource(R.drawable.slika6);
                if(stan.id==16)
                    slika.setImageResource(R.drawable.slika7);
                if(stan.id==17)
                    slika.setImageResource(R.drawable.slika8);
                if(stan.id==18)
                    slika.setImageResource(R.drawable.slika);
                if(stan.id==19)
                    slika.setImageResource(R.drawable.slika2);
                if(stan.id==23)
                    slika.setImageResource(R.drawable.slika9);
                if(stan.id==25)
                    slika.setImageResource(R.drawable.slika10);
                if(stan.id==26)
                    slika.setImageResource(R.drawable.slika11);
                if(stan.id==27)
                    slika.setImageResource(R.drawable.slika12);
                if(stan.id==28)
                    slika.setImageResource(R.drawable.slika13);
                if(stan.id==29)
                    slika.setImageResource(R.drawable.slika14);
                if(stan.id==30)
                    slika.setImageResource(R.drawable.slika15);
                if(stan.id==31)
                    slika.setImageResource(R.drawable.slika16);
                if(stan.id==32)
                    slika.setImageResource(R.drawable.slika17);
                if(stan.id==33)
                    slika.setImageResource(R.drawable.slika18);
                if(stan.id==34)
                    slika.setImageResource(R.drawable.slika19);



                return convertView;
            }
        });

        stanoviListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PregledStanovaVM.Rows stan=t.podaci.get(position);
                dobaviPodatkeIPokreniFragment(stan.id);
            }
        });
    }

}
