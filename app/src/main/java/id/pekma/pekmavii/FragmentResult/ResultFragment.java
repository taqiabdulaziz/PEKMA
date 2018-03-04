package id.pekma.pekmavii.FragmentResult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.pekma.pekmavii.FragmentNews.NewsFragment;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/2/2018.
 */

public class ResultFragment extends Fragment {

    Button btn1,btn2,btn3;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview =  inflater.inflate(R.layout.fragment_result, null);
        btn1 = rootview.findViewById(R.id.btn1);
        btn2 = rootview.findViewById(R.id.btn2);
        btn3 = rootview.findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment;
                fragment = new FragmentOlahraga();
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.flfragment2,fragment);
                ft.commit();
                ft.addToBackStack(null);
            }
        });
        return rootview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void click(){
    }


}
