package id.pekma.pekmavii.FragmentSchedule;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.pekma.pekmavii.FragmentHome.HomeData;
import id.pekma.pekmavii.FragmentResult.Akademik.FragmentResultOlahraga;
import id.pekma.pekmavii.FragmentResult.Olahraga.Schedule.FragmentOlahraga;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/2/2018.
 */

public class ScheduleFragment extends Fragment{
    private Spinner spinnerCabor,spinnerCaborDetail;
    SendCabol1 SC;
    Context context;
    SendResultOrSched1 SRS;
    int pos = 0;
    int positionCabol = 0;
    List<HomeData> data=new ArrayList<>();
    ImageView backgroundIv;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

            SC = (SendCabol1) getActivity();
            SRS = (SendResultOrSched1) getActivity();

        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }


    public interface SendCabol1 {
        void sendCabolData (int message);
    }

    public interface SendResultOrSched1 {
        void sendResultOrSchedData (int message);
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
//                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                if (position == 0) {
                    pos = 0;

                    spinnerCabor.setPrompt("Akademik");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    }
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                            R.array.olahraga, android.R.layout.simple_spinner_item);

                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter2.notifyDataSetChanged();
                    spinnerCaborDetail.setAdapter(adapter2);



                } else if (position == 1) {
                    pos = 1;
                    spinnerCabor.setPrompt("Olahraga");

                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    }

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                            R.array.olahraga, android.R.layout.simple_spinner_item);

                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter2.notifyDataSetChanged();
                    spinnerCaborDetail.setAdapter(adapter2);

                } else {
                    pos = 2;
                    spinnerCabor.setPrompt("Seni");

                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    }

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
                if (parent.getChildAt(0) != null) {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                }
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
                    Fragment fragment = new FragmentResultOlahraga();
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flfragment2,fragment);
                    fragmentTransaction.commit();

                    SC.sendCabolData(position);
                    SRS.sendResultOrSchedData(0);
                    homeData.cabolData1 = positionCabol;

                    data.add(homeData);
                } else if (pos == 0){
                    if (position == 0){
                        positionCabol = 24 ;
                    } else if (position == 1){
                        positionCabol = 25;
                    } else if (position == 2){
                        positionCabol = 26;
                    } else if (position == 3){
                        positionCabol = 27;
                    } else if (position == 4){
                        positionCabol = 28;
                    } else if (position == 5){
                        positionCabol = 29;
                    } else if (position == 6) {
                        positionCabol = 23;
                    } else if (position == 7){
                        positionCabol = 30;
                    } else if (position == 8){
                        positionCabol = 31;
                    } else {
                        positionCabol = 32;
                    }

                    HomeData homeData = new HomeData();
                    Fragment fragment = new FragmentOlahraga();
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flfragment2,fragment);
                    fragmentTransaction.commit();

                    SC.sendCabolData(positionCabol);
                    SRS.sendResultOrSchedData(0);
                    homeData.cabolData1 = positionCabol;

                } else {
                    if (position == 0){
                        positionCabol = 17;

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
                    Fragment fragment = new FragmentResultOlahraga();
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flfragment2,fragment);
                    fragmentTransaction.commit();

                    SC.sendCabolData(position);
                    SRS.sendResultOrSchedData(0);
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
