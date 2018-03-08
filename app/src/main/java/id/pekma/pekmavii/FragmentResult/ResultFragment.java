package id.pekma.pekmavii.FragmentResult;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.pekma.pekmavii.FragmentNews.NewsFragment;
import id.pekma.pekmavii.FragmentResult.Olahraga.FragmentOlahraga;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/2/2018.
 */

public class ResultFragment extends Fragment implements NewsFragment.SendMessage{
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private Spinner spinnerCabor,spinnerCaborDetail;
    
    int pos = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview =  inflater.inflate(R.layout.fragment_result, null);
        spinnerCaborDetail = rootview.findViewById(R.id.spinnerDetailCabor);
        spinnerCabor = rootview.findViewById(R.id.spinnerCabor);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.cabang_olahraga, android.R.layout.simple_spinner_item);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCabor.setPrompt("ww");
        spinnerCabor.setAdapter(adapter);
        spinnerCaborDetail.setPrompt("ww2");

        //SELECTED ITEM LISTENER......
        spinnerCabor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                if (position == 0) {
                    pos = 0;
                    Toast.makeText(getContext(), "Akademik" + pos, Toast.LENGTH_SHORT).show();
                    spinnerCabor.setPrompt("Akademik");
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                            R.array.akademik, android.R.layout.simple_spinner_item);

                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter2.notifyDataSetChanged();
                    spinnerCaborDetail.setAdapter(adapter2);



                } else if (position == 1) {
                    pos = 1;
                    spinnerCabor.setPrompt("Olahraga");
                    Toast.makeText(getContext(), "Olahraga"+ pos, Toast.LENGTH_SHORT).show();
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                            R.array.olahraga, android.R.layout.simple_spinner_item);

                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter2.notifyDataSetChanged();
                    spinnerCaborDetail.setAdapter(adapter2);

                } else {
                    pos = 2;
                    spinnerCabor.setPrompt("Seni");
                    Toast.makeText(getContext(), "Seni"+ pos, Toast.LENGTH_SHORT).show();
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                            R.array.seni, android.R.layout.simple_spinner_item);

                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter2.notifyDataSetChanged();
                    spinnerCaborDetail.setAdapter(adapter2);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCaborDetail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
