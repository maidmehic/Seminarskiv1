package com.fit.maid.seminarskiv1.fragmenti;


import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.fit.maid.seminarskiv1.R;
import com.fit.maid.seminarskiv1.data.PregedGradovaVM;
import com.fit.maid.seminarskiv1.data.PregledDetaljaStanaVM;
import com.fit.maid.seminarskiv1.helper.MyApiRequest;
import com.fit.maid.seminarskiv1.helper.MyRunnable;
import com.fit.maid.seminarskiv1.helper.MyRunnableBaza;

public class LokacijaFragment extends DialogFragment {

    private ListView gradovi;
    private SearchView pretraga;

    private MyRunnable<PregedGradovaVM.Rows>callback;

    public static LokacijaFragment newInstance(MyRunnable<PregedGradovaVM.Rows> callback) {
        LokacijaFragment fragment = new LokacijaFragment();
        Bundle args = new Bundle();
        args.putSerializable("callback",callback);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
    callback=(MyRunnable<PregedGradovaVM.Rows>)getArguments().getSerializable("callback");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_lokacija, container, false);

        gradovi = view.findViewById(R.id.lvGradovi);
        pretraga = view.findViewById(R.id.svLokacija);



        napuniPodatkeTask();

        pretraga.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                napuniPodatkeTask();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                napuniPodatkeTask();
                return true;
            }
        });

        return view;
    }

    private void napuniPodatkeTask() {

            MyRunnableBaza<PregedGradovaVM> myRunnableBaza= new MyRunnableBaza<PregedGradovaVM>() {
                @Override
                public void Run(PregedGradovaVM t) {
                    napuniPodatke(t);
                }
            };
            MyApiRequest.get(getActivity(),"Grad/Index/"+pretraga.getQuery().toString(),myRunnableBaza,false);

    }

    private void napuniPodatke(final PregedGradovaVM t) {
        gradovi.setAdapter(new BaseAdapter() {
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
            public View getView(int position, View view, ViewGroup parent) {

                if(view==null){
                    LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.grad_stavka,parent,false);
                }

                TextView naziv=view.findViewById(R.id.firstLine);

                PregedGradovaVM.Rows grad=t.podaci.get(position);
                naziv.setText(grad.naziv);
                return view;
            }
        });

        gradovi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PregedGradovaVM.Rows grad=t.podaci.get(position);
                callback.Run(grad);
                dismiss();
            }
        });
    }

}
