package id.pekma.pekmavii.FragmentHome;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import id.pekma.pekmavii.FragmentNews.AdapterNews;
import id.pekma.pekmavii.FragmentNews.DetailActivityNews;
import id.pekma.pekmavii.FragmentNews.NewsData;
import id.pekma.pekmavii.FragmentNews.NewsDataHome;
import id.pekma.pekmavii.FragmentNews.NewsFragment;
import id.pekma.pekmavii.FragmentResult.ResultFragment;
import id.pekma.pekmavii.FragmentSchedule.ScheduleFragment;
import id.pekma.pekmavii.MainActivity;
import id.pekma.pekmavii.R;
import id.pekma.pekmavii.VenueActivity.V_GedungG;

/**
 * Created by Muhammad Taqi on 2/13/2018.
 */

public class HomeFragment extends Fragment implements NewsFragment.SendMessage{
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    TextView mnewst,mnewst1,mnewstitle,mnewstitle1,toolbarTitle;
    String title,desc,imageview;
    ImageView mnewsiv,IvGedJ,IvMinsoc,IvPlasma,ivSc,ivVoliParma,IvLapfutParma,IvGedG,IvJoggingTrack;
    Button viewAllUpcom,viewAllLatest;
    CardView cardViewHome;
    LinearLayout newsHome;
    CardView gedungJ,miniSoccer,plasma,studentCenter,voliParma,gedungG,joggingTrack,lapfutparma;
    Fragment fragment;
    List<HomeData> data=new ArrayList<>();

    private BottomSheetBehavior mBottomSheetBehavior1;
    private boolean refreshState = false;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        final MainActivity mainActivity = (MainActivity) getActivity();
        final String myDataFromActivity = mainActivity.getMyData();
        final String myTitleDataFromActivity = mainActivity.getMyTitleString();
        final String myIvDataFromActivity = mainActivity.getMyIvData();

        //BIAR CARDVIEW ADA CORNER NYA
        cardViewHome = rootview.findViewById(R.id.cvNewsHome);
        cardViewHome.setPreventCornerOverlap(false);

        this.title = myTitleDataFromActivity;
        this.desc = myDataFromActivity;
        this.imageview = myIvDataFromActivity;

        System.out.println(myIvDataFromActivity +"DESC NEWS");

        mnewstitle = rootview.findViewById(R.id.homenewstxt);
        mnewsiv = rootview.findViewById(R.id.ivHomeNews);
        newsHome = rootview.findViewById(R.id.newsHome);
//        swipeRefreshLayout = rootview.findViewById(R.id.swipeHome);

        //CARDVIEW HOME
        gedungJ = rootview.findViewById(R.id.gedungJ);
        miniSoccer = rootview.findViewById(R.id.miniSoccer);
        plasma = rootview.findViewById(R.id.plasma);
        studentCenter = rootview.findViewById(R.id.studentCenter);
        voliParma = rootview.findViewById(R.id.voliParma);
        gedungG = rootview.findViewById(R.id.gedungG);
        joggingTrack = rootview.findViewById(R.id.joggingTrack);
        lapfutparma = rootview.findViewById(R.id.lapanganFutsalParma);

        //IMAGEVIEW VENUE
        IvGedG = rootview.findViewById(R.id.IvGedG);
        IvGedJ = rootview.findViewById(R.id.IvGedJ);
        IvJoggingTrack = rootview.findViewById(R.id.IvJoggingTrack);
        IvLapfutParma = rootview.findViewById(R.id.IvLapfutParma);
        IvMinsoc = rootview.findViewById(R.id.IvMinsoc);
        IvPlasma = rootview.findViewById(R.id.IvPlasma);
        ivSc = rootview.findViewById(R.id.ivSc);
        ivVoliParma = rootview.findViewById(R.id.ivVoliParma);

        //Button
        viewAllUpcom = rootview.findViewById(R.id.viewAllUpcom);
        viewAllLatest = rootview.findViewById(R.id.viewAllLatest);

        //LOAD KE IMAGEVIEW VENUE!
//        Picasso.with(getContext()).load(R.drawable.venue_gedungg).fit().into(IvGedG);
//        Picasso.with(getContext()).load(R.drawable.venue_gedungj).fit().into(IvGedJ);
//        Picasso.with(getContext()).load(R.drawable.venue_joggingtrack).fit().into(IvJoggingTrack);
//        Picasso.with(getContext()).load(R.drawable.venue_lapanganfutsalparma).fit().into(IvLapfutParma);
//        Picasso.with(getContext()).load(R.drawable.venue_mini_soccer).fit().into(IvMinsoc);
//        Picasso.with(getContext()).load(R.drawable.venue_plasma).fit().into(IvPlasma);
//        Picasso.with(getContext()).load(R.drawable.venue_sc).fit().into(ivSc);
//        Picasso.with(getContext()).load(R.drawable.venue_voli_parma).fit().into(ivVoliParma);

        viewAllUpcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ScheduleFragment();
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flfragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        viewAllLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ResultFragment();
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flfragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

//        gedungJ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), V_GedungG.class);
//                startActivity(i);
//            }
//        });


        //HOME NEWS ONCLICKLISTENER
        newsHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),DetailActivityNews.class);

                //PACK DATA TO SEND
                i.putExtra("TITLE_KEY",title);
                i.putExtra("NAME_KEY",desc);
                i.putExtra("IMAGE_KEY",imageview);

                startActivity(i);
            }
        });
        Picasso.with(getContext())
                .load(myIvDataFromActivity)
                .fit()
                .centerCrop()
                .into(mnewsiv);

        mnewstitle.setText(myTitleDataFromActivity);

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(true);
//                fragment = new HomeFragment();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.detach(this).attach(this).commit();
////                Fragment frg = null;
////                frg = getFragmentManager().findFragmentByTag("FLFRAGMENT");
////                final FragmentTransaction ft = getFragmentManager().beginTransaction();
////                ft.detach(frg);
////                ft.attach(frg);
////                ft.commit();
//            }
//        });
//
//        swipeRefreshLayout.setRefreshing(false);

        return rootview;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        if (getArguments()!=null) {
            Bundle bundle = getArguments();
            String mnews1 = bundle.getString("txtnews");
            new AsyncFetch().execute();
        }
        super.onCreate(savedInstanceState);
        new AsyncFetch().execute();

    }

    @Override
    public void sendNewsData(String message) {
        Log.e("hello",message);
    }

    @SuppressLint("StaticFieldLeak")
    public class AsyncFetch extends AsyncTask<String,String,String> {
        ProgressDialog pdLoading = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data

                url = new URL("https://pekma.id/news.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            int ideventRaw,ideventTrue;
            //this method will be running on UI thread


            try {

                JSONArray jsonArray = new JSONArray(result);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    HomeData homeData = new HomeData();
                    homeData.playerA = json_data.getString("playera");
                    homeData.playerB = json_data.getString("playerb");
                    homeData.msDate = json_data.getString("msdate");
                    homeData.mstime = json_data.getString("mstime");
                    homeData.resultpa = json_data.getString("resultpa");
                    homeData.resultpb = json_data.getString("resultpb");
                    homeData.jurA = json_data.getString("jurA");
                    homeData.jurB = json_data.getString("jurB");
                    homeData.done = json_data.getString("done");
                    homeData.category = json_data.optString("cat");
                    homeData.loc = json_data.optString("loc");
                    homeData.category = json_data.optString("category");
                    ideventRaw = json_data.getInt("idcat");
                    ideventTrue = ideventRaw - 8;

                    homeData.idevent = ideventTrue;

                    data.add(homeData);

                }

                RecyclerView recyclerView = getView().findViewById(R.id.rvHome);
                RecyclerView recyclerViewL = getView().findViewById(R.id.rvHomeLatest);

                final AdapterHome mAdapter = new AdapterHome(getActivity(), data);
                final AdapterHomeLatestMatch mAdapterL = new AdapterHomeLatestMatch(getActivity(), data);

                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                recyclerViewL.setAdapter(mAdapterL);
                recyclerViewL.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

                recyclerView.setNestedScrollingEnabled(false);
                recyclerViewL.setNestedScrollingEnabled(false);

            } catch (JSONException e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            }

            Picasso.with(getContext()).load(R.drawable.venue_gedungg).fit().into(IvGedG);
            Picasso.with(getContext()).load(R.drawable.venue_gedungj).fit().into(IvGedJ);
            Picasso.with(getContext()).load(R.drawable.venue_joggingtrack).fit().into(IvJoggingTrack);
            Picasso.with(getContext()).load(R.drawable.venue_lapanganfutsalparma).fit().into(IvLapfutParma);
            Picasso.with(getContext()).load(R.drawable.venue_mini_soccer).fit().into(IvMinsoc);
            Picasso.with(getContext()).load(R.drawable.venue_plasma).fit().into(IvPlasma);
            Picasso.with(getContext()).load(R.drawable.venue_sc).fit().into(ivSc);
            Picasso.with(getContext()).load(R.drawable.venue_voli_parma).fit().into(ivVoliParma);

            pdLoading.dismiss();
        }
    }
}
