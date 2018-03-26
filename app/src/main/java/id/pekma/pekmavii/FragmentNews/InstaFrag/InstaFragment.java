package id.pekma.pekmavii.FragmentNews.InstaFrag;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import id.pekma.pekmavii.FragmentNews.NewsFragment;
import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/13/2018.
 */

public class InstaFragment extends Fragment implements NewsFragment.SendMessage {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    public String access_token = "4204332056.1677ed0.be993a78c6f5455c93fa94f0f567cece";

    TextView mnewst,mnewst1,mnewstitle,mnewstitle1,toolbarTitle;
    String title,desc,imageview;
    ImageView mnewsiv,iconpekmasmall;
    Button buttonNewsHome;

    private BottomSheetBehavior mBottomSheetBehavior1;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_insta, container, false);
//        MainActivity mainActivity = (MainActivity) getActivity();
//
//        final String myDataFromActivity = mainActivity.getMyData();
//        final String myTitleDataFromActivity = mainActivity.getMyTitleString();
//        final String myIvDataFromActivity = mainActivity.getMyIvData();
//
//
//        this.title = myTitleDataFromActivity;
//        this.desc = myDataFromActivity;
//        this.imageview = myIvDataFromActivity;
//
//        buttonNewsHome = rootview.findViewById(R.id.openNews);
//        iconpekmasmall = rootview.findViewById(R.id.iconpekmasmall);
//        mnewstitle = rootview.findViewById(R.id.homenewstxt);
//        mnewsiv = rootview.findViewById(R.id.ivHomeNews);
//        Picasso.with(getContext())
//                .load(myIvDataFromActivity)
//                .fit()
//                .centerCrop()
//                .into(mnewsiv);
//        Picasso.with(getContext())
//                .load(R.drawable.ic_w)
//                .fit()
//                .into(iconpekmasmall);
//        mnewstitle.setText(myTitleDataFromActivity);
//
//        buttonNewsHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getContext(),DetailActivityNews.class);
//
//                //PACK DATA TO SEND
//                i.putExtra("TITLE_KEY",title);
//                i.putExtra("NAME_KEY",desc);
//                i.putExtra("IMAGE_KEY",imageview);
//
//                startActivity(i);
//            }
//        });
        return rootview;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

//        if (getArguments()!=null) {
//            Bundle bundle = getArguments();
//            String mnews1 = bundle.getString("txtnews");
//            new AsyncFetch().execute();
//        }
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
        HttpsURLConnection conn;
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

                url = new URL("https://api.instagram.com/v1/users/self/media/recent?access_token=" + access_token);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpsURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line +"\n");

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
            //this method will be running on UI thread

            pdLoading.dismiss();
            List<InstaData> data=new ArrayList<>();

            pdLoading.dismiss();

            try {


                JSONObject jsonObject = new JSONObject(result);
                JSONArray dataInsta = jsonObject.getJSONArray("data");
                for (int i=0;i<dataInsta.length();i++){
                    InstaData instaData = new InstaData();
                    JSONObject data1 = dataInsta.getJSONObject(i);
                    JSONObject images = data1.getJSONObject("images");
                    JSONObject low_resolution = images.getJSONObject("low_resolution");
                    JSONObject caption =  data1.optJSONObject("caption");

                    instaData.image = low_resolution.optString("url");

                    if (caption != null){
                        instaData.description = caption.optString("text");
                    } else {
                        instaData.description = "";
                    }

                    System.out.println(low_resolution.optString("url"));
                    assert caption != null;

                    System.out.println(instaData.description);

                    data.add(instaData);

                }
//                for (int i=0;i<jsonArray.length();i++){
//                JSONObject json_data = jsonArray.getJSONObject(i);
//                instaData.description = ww.getString("")
//                homeData.playerA = json_data.getString("playera");
//                homeData.playerB = json_data.getString("playerb");
//                homeData.msDate = json_data.getString("msdate");
//                homeData.mstime = json_data.getString("mstime");
//                homeData.resultpa = json_data.getString("resultpa");
//                homeData.resultpb = json_data.getString("resultpb");
//                homeData.jurA = json_data.getString("jurA");
//                homeData.jurB = json_data.getString("jurB");
//                homeData.idevent = json_data.getInt("idevent");

//                data.add(homeData);


                RecyclerView recyclerView = getView().findViewById(R.id.rvInsta);
//                RecyclerView recyclerViewL = getView().findViewById(R.id.rvHomeLatest);
                AdapterInsta mAdapter = new AdapterInsta(getActivity(), data);
//                AdapterHomeLatestMatch mAdapterL = new AdapterHomeLatestMatch(getActivity(), data);

                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
//                recyclerViewL.setAdapter(mAdapterL);
//                recyclerViewL.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

            } catch (JSONException e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
