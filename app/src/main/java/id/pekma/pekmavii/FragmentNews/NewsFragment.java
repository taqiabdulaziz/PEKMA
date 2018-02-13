package id.pekma.pekmavii.FragmentNews;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import id.pekma.pekmavii.R;

/**
 * Created by Muhammad Taqi on 2/2/2018.
 */

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView recyclerView;
    private AdapterNews mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_news, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.flFragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AsyncFetch().execute();


    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new AsyncFetch2().execute();
            }
        }, 2500);
    }


    public class AsyncFetch extends AsyncTask<String,String,String>{
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

                url = new URL("https://taqiabdulaziz.com/news1.json");

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
            //this method will be running on UI thread

            pdLoading.dismiss();
            List<NewsData> data=new ArrayList<>();

            pdLoading.dismiss();

            try {

                JSONObject jsonObject = new JSONObject(result);
                JSONObject rssObject = jsonObject.getJSONObject("rss");
                JSONObject channelObject = rssObject.getJSONObject("channel");
                JSONArray jsonArray = channelObject.getJSONArray("item");
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    NewsData newsData = new NewsData();
                    newsData.title=json_data.getString("title");

                    JSONObject descObject = json_data.getJSONObject("description");
                    JSONObject imgObject = descObject.getJSONObject("img");
                    newsData.desc=descObject.optString("#text");
                    newsData.newsImage=imgObject.optString("-src");


                    data.add(newsData);

                }


                // Extract data from json and store into ArrayList as class objects
//                for(int i=0;i<jArray.length();i++){
//                    JSONObject json_data = jArray.getJSONObject(i);
//                    NewsData newsData = new NewsData();
//                    newsData.title= json_data.getString("title");
//                    newsData.desc= json_data.optString("link");
//                    newsData.newsImage= json_data.optString("-src");
//                    data.add(newsData);
//                }

                // Setup and Handover data to recyclerview
                recyclerView = getView().findViewById(R.id.rvNews);
                mAdapter = new AdapterNews(getActivity(), data);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            } catch (JSONException e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
    public class AsyncFetch2 extends AsyncTask<String,String,String>{
        SwipeRefreshLayout swipeRefreshLayout;
        ProgressDialog pdLoading = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data

                url = new URL("https://taqiabdulaziz.com/news2.json");

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
            //this method will be running on UI thread
            mSwipeRefreshLayout.setRefreshing(false);
            pdLoading.dismiss();
            List<NewsData> data=new ArrayList<>();

            pdLoading.dismiss();

            try {

                JSONObject jsonObject = new JSONObject(result);
                JSONObject rssObject = jsonObject.getJSONObject("rss");
                JSONObject channelObject = rssObject.getJSONObject("channel");
                JSONArray jsonArray = channelObject.getJSONArray("item");
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    NewsData newsData = new NewsData();
                    newsData.title=json_data.getString("title");

                    JSONObject descObject = json_data.getJSONObject("description");
                    JSONObject imgObject = descObject.getJSONObject("img");
                    newsData.desc=descObject.optString("#text");
                    newsData.newsImage=imgObject.optString("-src");


                    data.add(newsData);

                }


                // Extract data from json and store into ArrayList as class objects
//                for(int i=0;i<jArray.length();i++){
//                    JSONObject json_data = jArray.getJSONObject(i);
//                    NewsData newsData = new NewsData();
//                    newsData.title= json_data.getString("title");
//                    newsData.desc= json_data.optString("link");
//                    newsData.newsImage= json_data.optString("-src");
//                    data.add(newsData);
//                }

                // Setup and Handover data to recyclerview
                recyclerView = getView().findViewById(R.id.rvNews);
                mAdapter = new AdapterNews(getActivity(), data);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            } catch (JSONException e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
}
