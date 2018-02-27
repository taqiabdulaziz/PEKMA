package id.pekma.pekmavii.FragmentNews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import id.pekma.pekmavii.R;

public class DetailActivityNews extends AppCompatActivity {

    TextView newsTxt;
    ImageView homeNewsDetailIv;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        newsTxt = (TextView) findViewById(R.id.homeNewsDetailTxt);
        homeNewsDetailIv = findViewById(R.id.homeNewsDetailIv);


        Intent i=this.getIntent();
        String name=i.getExtras().getString("NAME_KEY");
        String image=i.getExtras().getString("IMAGE_KEY");

        //BIND DATA
        newsTxt.setText(name);
        Picasso.with(context)
                .load(image)
                .into(homeNewsDetailIv);

    }

}
