package id.pekma.pekmavii.FragmentNews;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import id.pekma.pekmavii.R;
import uk.co.senab.photoview.PhotoViewAttacher;

public class DetailActivityNews extends AppCompatActivity {

    TextView newsTxt;
    ImageView homeNewsDetailIv,ivDetail;
    Context context;
    PhotoViewAttacher photoViewAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        TextView titleTxt = (TextView)findViewById(R.id.homeTitleNewsDetailTxt);
        newsTxt = (TextView) findViewById(R.id.homeNewsDetailTxt);
        homeNewsDetailIv = findViewById(R.id.homeNewsDetailIv);
        ivDetail = findViewById(R.id.ivDetail);


        Intent i=this.getIntent();
        String title=i.getExtras().getString("TITLE_KEY");
        String name=i.getExtras().getString("NAME_KEY");
        String image=i.getExtras().getString("IMAGE_KEY");

        //BIND DATA
        titleTxt.setText(title);
        newsTxt.setText(name);
        Picasso.with(context)
                .load(image)
                .fit()
                .centerCrop()
                .into(homeNewsDetailIv);

        Picasso.with(context)
                .load(image)
                .fit()
                .into(ivDetail);

        photoViewAttacher  = new PhotoViewAttacher(ivDetail);
        photoViewAttacher.update();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
