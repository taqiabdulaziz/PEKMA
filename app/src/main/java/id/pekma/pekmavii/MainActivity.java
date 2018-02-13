package id.pekma.pekmavii;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import id.pekma.pekmavii.FragmentHome.HomeFragment;
import id.pekma.pekmavii.NavDrawContent.FixturesFragment;
import id.pekma.pekmavii.FragmentNews.NewsFragment;
import id.pekma.pekmavii.NavDrawContent.OtherFragment;
import id.pekma.pekmavii.NavDrawContent.ResultFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        setTitle("PEKMA");
        changeFragment(0);
    }


    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Home:
                    changeFragment(1);
                    break;
                case R.id.Fixtures:
                    changeFragment(2);
                    break;
                case R.id.Result:
                    changeFragment(3);
                    break;
                case R.id.News:
                    changeFragment(4);
                    break;
                case R.id.Other:
                    changeFragment(5);
                    break;
            }
            return true;
        }

    };

    private void changeFragment(int position) {
        Fragment fragment;
        if (position == 1){
            fragment = new HomeFragment();
        } else if  (position == 2) {
            fragment = new FixturesFragment();
        } else if (position == 3){
            fragment = new ResultFragment();
        } else if (position == 4) {
            fragment = new NewsFragment();
        } else {
            fragment = new OtherFragment();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }



}
