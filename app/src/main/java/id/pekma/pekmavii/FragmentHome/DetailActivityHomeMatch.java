package id.pekma.pekmavii.FragmentHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

    TextView playerAtext,playerBtext,jurAtext,jurBtext,titledetail,msDateTxt,msTimeTxt,resultpaTxt,resultpbTxt;
    CircleImageView jurAciv,jurBciv;
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

        switch (jurAname) {
            case "D4 Akuntansi":
            case "D3 Akuntansi":
            case "D4 Akuntansi Tugas belajar":
            case "D3 Manajemen Aset":
            case "D3 Akuntansi Tugas Belajar":

                with(context).load(R.drawable.maskot_akun).into(jurAciv);
                break;
            case "D1 Pajak":
            case "D3 Pajak":
            case "D3 Pajak Tugas Belajar":
            case "D3 Penilai":

                with(context).load(R.drawable.maskot_pajak).into(jurAciv);
                break;
            case "D1 maskot_bc":
            case "D3 maskot_bc":

                with(context).load(R.drawable.maskot_bc).into(jurAciv);
                break;
            default:

                with(context).load(R.drawable.maskot_mankeu).into(jurAciv);
                break;
        }

        switch (jurBname) {
            case "D4 Akuntansi":
            case "D3 Akuntansi":
            case "D4 Akuntansi Tugas belajar":
            case "D3 Manajemen Aset":
            case "D3 Akuntansi Tugas Belajar":

                with(context).load(R.drawable.maskot_akun).into(jurBciv);
                break;
            case "D1 Pajak":
            case "D3 Pajak":
            case "D3 Pajak Tugas Belajar":
            case "D3 Penilai":

                with(context).load(R.drawable.maskot_pajak).into(jurBciv);
                break;
            case "D1 maskot_bc":
            case "D3 maskot_bc":

                with(context).load(R.drawable.maskot_bc).into(jurBciv);
                break;
            default:

                with(context).load(R.drawable.maskot_mankeu).into(jurBciv);
                break;
        }

        if (idevent == "0"){
            with(context)
                    .load(R.drawable.venue_sc)
                    .fit()
                    .into(venue);
        } else if (idevent == "1") {
            with(context)
                    .load(R.drawable.venue_sc)
                    .fit()
                    .into(venue);
        } else if (idevent == "2"){
            with(context)
                    .load(R.drawable.venue_sc)
                    .fit()
                    .into(venue);
        } else if (idevent == "3"){
            with(context)
                    .load(R.drawable.venue_sc)
                    .fit()
                    .into(venue);
        } else if (idevent == "4") {
            with(context)
                    .load(R.drawable.venue_gedungj)
                    .fit()
                    .into(venue);
        } else if (idevent == "5") {
            with(context)
                    .load(R.drawable.venue_sc)
                    .fit()
                    .into(venue);
        } else if (idevent == "6") {
            with(context)
                    .load(R.drawable.venue_mini_soccer)
                    .fit()
                    .into(venue);
        } else if (idevent == "7"){
            with(context)
                    .load(R.drawable.venue_sc)
                    .fit()
                    .into(venue);
        } else {
            with(context)
                    .load(R.drawable.venue_sc)
                    .fit()
                    .into(venue);
        }
    }
}
