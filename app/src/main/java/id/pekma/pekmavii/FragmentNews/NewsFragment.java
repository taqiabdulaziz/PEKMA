package id.pekma.pekmavii.FragmentNews;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
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
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import id.pekma.pekmavii.FragmentHome.HomeFragment;
import id.pekma.pekmavii.MainActivity;
import id.pekma.pekmavii.R;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;


/**
 * Created by Muhammad Taqi on 2/2/2018.
 */

public class NewsFragment extends Fragment {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public String homenewstxt;
    public String homenewstitleTxt;
    public String homenewsiv;
    SendMessageTitle SMT;
    SendMessage SM;
    SendMessageiv SMI;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View rootview;
    private RecyclerView recyclerView;
    private AdapterNews mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_news, null);
        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.flfragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AsyncFetch().execute();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            SMT = (SendMessageTitle) getActivity();
            SM = (SendMessage) getActivity();
            SMI = (SendMessageiv)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    //INTERFACE
    public interface SendMessage {
        void sendNewsData(String message);
    }

    public interface SendMessageiv {
        void sendNewsIvData(String ivMessage);
    }

    public interface SendMessageTitle {
        void sendNewsTitleData(String titleMessage);
    }

    public class AsyncFetch extends AsyncTask<String,String,String>{
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

                url = new URL("https://taqiabdulaziz.com/berita.php");

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

            List<NewsData> data=new ArrayList<>();

            try {

                JSONArray jsonArray = new JSONArray(result);

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    JSONObject json_data_home = jsonArray.getJSONObject(0);

                    NewsData newsData = new NewsData();

                    newsData.title = json_data.optString("title");
                    newsData.title = json_data_home.optString("title");
                    newsData.desc=json_data.optString("ndesc");
                    newsData.deschome=json_data_home.optString("ndesc");
                    newsData.newsImage=json_data.optString("nimg");
                    newsData.newsImageHome=json_data_home.optString("nimg");

                    String mnews = newsData.getDeschome();
                    String mnewsiv = newsData.getNewsImageHome();
                    String mnewstitle = newsData.getTitle();

                    homenewstitleTxt = mnewstitle;
                    homenewstxt = mnews;
                    homenewsiv = mnewsiv;

                    data.add(newsData);

                }

                recyclerView = getView().findViewById(R.id.rvNews);
                mAdapter = new AdapterNews(getActivity(), data);
                recyclerView.setItemAnimator(new FadeInLeftAnimator());
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                recyclerView.setHasFixedSize(true);
                AnimationSet set = new AnimationSet(true);

                Animation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(500);
                set.addAnimation(animation);

                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f
                );
                animation.setDuration(100);
                set.addAnimation(animation);
                LayoutAnimationController controller;
                controller = new LayoutAnimationController(set, 0.5f);
                recyclerView.setLayoutAnimation(controller);

                SMT.sendNewsTitleData(homenewstitleTxt);
                SM.sendNewsData(homenewstxt);
                SMI.sendNewsIvData(homenewsiv);

            } catch (JSONException e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
