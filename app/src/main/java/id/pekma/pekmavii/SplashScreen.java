package id.pekma.pekmavii;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SplashScreen extends AppCompatActivity {
    ImageView splash;
    CoordinatorLayout coordinatorLayout;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splash = findViewById(R.id.splashImg);
        context = this;

        Picasso.with(context)
                .load(R.drawable.splashscreen)
                .fit()
                .into(splash);

        coordinatorLayout = findViewById(R.id.coordinator);
        checkConn();
    }

    private void checkConn() {
        if (!DetectConnection.checkInternetConnection(this)) {

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Check Your Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Try Again", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkConn();

                        }
                    });
            snackbar.show();
//            Intent a = new Intent(getApplicationContext(),NoInternet.class);
//            startActivity(a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            }, 2000);
        }
    }

    public static class DetectConnection {
        static boolean checkInternetConnection(Context context) {

            ConnectivityManager con_manager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);

            return (con_manager.getActiveNetworkInfo() != null
                    && con_manager.getActiveNetworkInfo().isAvailable()
                    && con_manager.getActiveNetworkInfo().isConnected());
        }
    }
}
