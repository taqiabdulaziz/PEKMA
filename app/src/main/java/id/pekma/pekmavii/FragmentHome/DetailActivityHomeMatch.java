package id.pekma.pekmavii.FragmentHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import id.pekma.pekmavii.R;

import static com.squareup.picasso.Picasso.*;

public class DetailActivityHomeMatch extends AppCompatActivity {

    TextView playerAtext,playerBtext,jurAtext,jurBtext,titledetail,msDateTxt,msTimeTxt,resultpaTxt,resultpbTxt,ideventTxt,keterangan,namavenueTxt;
    ImageView jurAciv,jurBciv;
    ImageView venue;
    Context context;
    int akun,bc,kbn,pajak,manset,penilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home_match);
        Toolbar toolbar = findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setElevation(0);

        venue = findViewById(R.id.venue);
        resultpaTxt = findViewById(R.id.skorA);
        resultpbTxt = findViewById(R.id.skorB);
        msTimeTxt = findViewById(R.id.msTime);
        msDateTxt = findViewById(R.id.msDate);
        playerAtext = findViewById(R.id.playerAtxt);
        playerBtext = findViewById(R.id.playerBtxt);
        jurAtext = findViewById(R.id.JurAtxt);
        jurBtext = findViewById(R.id.JurBtxt);
        jurAciv = findViewById(R.id.jurAciv);
        jurBciv = findViewById(R.id.jurBciv);
        ideventTxt = findViewById(R.id.typeSport);
        namavenueTxt = findViewById(R.id.namaVenue);

        //RECEIVE DATA
        Intent i=this.getIntent();
        String resultpa = i.getExtras().getString("RESULTPA");
        String resultpb = i.getExtras().getString("RESULTPB");
        String msDate = i.getExtras().getString("MSDATE");
        String mstime = i.getExtras().getString("MSTIME");
        String playerAname=i.getExtras().getString("NAME_KEY_A");
        String playerBname=i.getExtras().getString("NAME_KEY_B");
        String idevent = i.getExtras().getString("IDEVENT");
        String jurAname = i.getExtras().getString("NAME_KEY_A_JUR");
        String jurBname = i.getExtras().getString("NAME_KEY_B_JUR");
        String namaLomba = i.getExtras().getString("CAT");
        String namavenue = i.getExtras().getString("LOC");

        //BIND DATA

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date1 = null;

        try {
            date1 = sdf.parse(mstime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date1.getTime();
        sdf.applyPattern("HH:mm");

        //RESULT
        if (resultpa == null || resultpb == null){
            resultpaTxt.setText("-");
            resultpbTxt.setText("-");
        } else {
            resultpaTxt.setText(resultpa);
            resultpbTxt.setText(resultpb);
        }

        msTimeTxt.setText(sdf.format(time));
        msDateTxt.setText(msDate);
        playerAtext.setText(playerAname);
        playerBtext.setText(playerBname);
        jurAtext.setText(jurAname);
        jurBtext.setText(jurBname);

        System.out.println(idevent + "ID EVENTNWOI");

        switch (jurAname) {
            case "Akuntansi":

                with(context).load(R.drawable.maskot_akun).into(jurAciv);
                break;
            case "Pajak":

                with(context).load(R.drawable.maskot_pajak).into(jurAciv);
                break;
            case "Bea Cukai":

                with(context).load(R.drawable.maskot_bc).into(jurAciv);
                break;
            case "Manajemen Keuangan":

                with(context).load(R.drawable.maskot_mankeu).into(jurAciv);
                break;
            default:

                with(context).load(R.drawable.maskot_sekre).into(jurAciv);
                break;
        }

        switch (jurBname) {
            case "Akuntansi":

                with(context).load(R.drawable.maskot_akun).into(jurBciv);
                break;
            case "Pajak":

                with(context).load(R.drawable.maskot_pajak).into(jurBciv);
                break;
            case "Bea Cukai":

                with(context).load(R.drawable.maskot_bc).into(jurBciv);
                break;
            case "Manajemen Keuangan":

                with(context).load(R.drawable.maskot_mankeu).into(jurBciv);
                break;
            default:

                with(context).load(R.drawable.maskot_sekre).into(jurBciv);
                break;
        }

        ideventTxt.setText(namaLomba);
        namavenueTxt.setText(namavenue);

        switch (idevent) {
            case "24":
                with(context)
                        .load(R.drawable.venue_sc)
                        .fit()
                        .into(venue);

                break;
            case "25":
                with(context)
                        .load(R.drawable.venue_sc)
                        .fit()
                        .into(venue);
                break;
            case "26":
                with(context)
                        .load(R.drawable.venue_sc)
                        .fit()
                        .into(venue);
                break;
            case "27":
                with(context)
                        .load(R.drawable.venue_sc)
                        .fit()
                        .into(venue);
                break;
            case "28":
                with(context)
                        .load(R.drawable.venue_minsoc)
                        .fit()
                        .into(venue);
                break;
            case "29":
                with(context)
                        .load(R.drawable.venue_gedungg)
                        .fit()
                        .into(venue);
                break;
            case "30":
                with(context)
                        .load(R.drawable.venue_sc)
                        .fit()
                        .into(venue);
                break;
            case "15":
                with(context)
                        .load(R.drawable.venue_sc)
                        .fit()
                        .into(venue);
                break;
            default:
                with(context)
                        .load(R.drawable.venue_sc)
                        .fit()
                        .into(venue);
                break;
        }
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
