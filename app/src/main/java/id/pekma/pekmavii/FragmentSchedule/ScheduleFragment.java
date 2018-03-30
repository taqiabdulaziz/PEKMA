package id.pekma.pekmavii.FragmentSchedule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import id.pekma.pekmavii.FragmentHome.AdapterHome;
import id.pekma.pekmavii.FragmentHome.AdapterHomeLatestMatch;
import id.pekma.pekmavii.FragmentHome.HomeData;
import id.pekma.pekmavii.FragmentHome.HomeFragment;
import id.pekma.pekmavii.FragmentNews.NewsFragment;
import id.pekma.pekmavii.FragmentResult.Akademik.FragmentAkademik;
import id.pekma.pekmavii.FragmentResult.Olahraga.FragmentOlahraga;
import id.pekma.pekmavii.MainActivity;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/2/2018.
 */

public class ScheduleFragment extends Fragment{
    private Spinner spinnerCabor,spinnerCaborDetail;
    SendCabol SC;
    int pos = 0;
    int positionCabol = 0;
    List<HomeData> data=new ArrayList<>();

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//
//            SC = (SendCabol) getActivity();
//
//        } catch (ClassCastException e) {
//            throw new ClassCastException("Error in retrieving data. Please try again");
//        }
//
//    }

    public interface SendCabol {
        void sendCabolData (int message);
    }

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

                if (pos == 1) {
                    if (position == 0){
                        positionCabol = 8;
                    } else if (position == 1){
                        positionCabol = 9;
                    } else if (position == 2){
                        positionCabol = 10;
                    } else if (position == 3){
                        positionCabol = 11;
                    } else if (position == 4){
                        positionCabol = 12;
                    } else if (position == 5){
                        positionCabol = 13;
                    } else if (position == 6){
                        positionCabol = 14;
                    } else if (position == 7){
                        positionCabol = 15;
                    } else {
                        positionCabol = 16;
                    }
                    HomeData homeData = new HomeData();
                    Fragment fragment = new FragmentOlahraga();
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flfragment2,fragment);
                    fragmentTransaction.commit();

                    SC.sendCabolData(position);
                    homeData.cabolData1 = positionCabol;

                    data.add(homeData);
                } else if (pos == 0){

                    if (position == 0){
                        positionCabol = 1 ;
                        Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                    } else if (position == 1){
                        positionCabol = 2;
                    } else if (position == 2){
                        positionCabol = 3;
                    } else if (position == 3){
                        positionCabol = 4;
                    } else if (position == 4){
                        positionCabol = 5;
                    } else if (position == 5){
                        positionCabol = 6;
                    } else if (position == 6){
                        positionCabol = 7;
                    } else if (position == 7){
                        positionCabol = 8;
                    } else if (position == 9){
                        positionCabol = 9;
                    } else {
                        positionCabol = 10;
                    }

                    HomeData homeData = new HomeData();
                    Fragment fragment = new FragmentAkademik();
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flfragment2,fragment);
                    fragmentTransaction.commit();

//                    SC.sendCabolData(position);
                    homeData.cabolData1 = positionCabol;

                } else {
                    if (position == 0){
                        positionCabol = 17;
                        Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                    } else if (position == 1){
                        positionCabol = 18;
                    } else if (position == 2){
                        positionCabol = 19;
                    } else if (position == 3){
                        positionCabol = 20;
                    } else if (position == 4){
                        positionCabol = 21;
                    } else if (position == 5){
                        positionCabol = 22;
                    } else if (position == 6){
                        positionCabol = 23;
                    } else if (position == 7){
                        positionCabol = 24;
                    } else if (position == 8){
                        positionCabol = 25;
                    } else {
                        positionCabol = 26;
                    }

                    HomeData homeData = new HomeData();
                    Fragment fragment = new FragmentAkademik();
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flfragment2,fragment);
                    fragmentTransaction.commit();

                    SC.sendCabolData(position);
                    homeData.cabolData1 = positionCabol;
                    System.out.println(positionCabol + "KONTOL");

                }



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
