package id.pekma.pekmavii;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import id.pekma.pekmavii.FragmentHome.HomeFragment;
import id.pekma.pekmavii.FragmentNews.NewsFragment;
import id.pekma.pekmavii.FragmentNews.TabbedNews;
import id.pekma.pekmavii.FragmentSchedule.ScheduleFragment;
import id.pekma.pekmavii.NavDrawContent.FixturesFragment;
import id.pekma.pekmavii.NavDrawContent.OtherFragment;
import id.pekma.pekmavii.FragmentResult.ResultFragment;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener,
        NewsFragment.SendMessage,
        NewsFragment.SendMessageiv,
        NewsFragment.SendMessageTitle,
        ResultFragment.SendCabol,
        ResultFragment.SendResultOrSched,
        ScheduleFragment.SendCabol1,
        ScheduleFragment.SendResultOrSched1
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    public SignInButton btnSignIn;
    public ImageView imgProfilePic,ivNavBar;
    SwipeRefreshLayout swipeRefreshLayout;
    Fragment fragment;
    public int pos,scheduleOrResult;
    public CardView cardViewHomeNews;
    public TextView txtName, txtEmail,mnews;
    String title = null;
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
    int myCabolData,myResOrSched;
    String myString,myIvString,myTitleString;
    NavigationView navigationView;
    public FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View hView = navigationView.getHeaderView(0);
        btnSignIn = findViewById(R.id.footer_item_2);
        swipeRefreshLayout = findViewById(R.id.swipeHome);
        ivNavBar = hView.findViewById(R.id.headernavbar);
        swipeRefreshLayout.setRefreshing(false);

//        txtName = findViewById(R.id.txtName);


        mAuth = FirebaseAuth.getInstance();


//        Typeface khandBold = Typeface.createFromAsset(getApplication().getAssets(), "font/adisans_bold.otf");
//
//        mTitle.setTypeface(khandBold);

        Picasso.with(getApplicationContext()).load(R.drawable.headernavbar).fit().into(ivNavBar);

        btnSignIn.setOnClickListener(this);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(0);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customizing G+ button
        btnSignIn.setSize(SignInButton.COLOR_DARK);
        btnSignIn.setColorScheme(0);
        btnSignIn.setVisibility(View.GONE);

        btnSignIn.setScopes(gso.getScopeArray());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("PEKMA");
        changeFragment(4);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                refreshHandler();
                swipeRefreshLayout.setEnabled(true);
                swipeRefreshLayout.setRefreshing(true);
//                commitFragment();

                changeFragment(pos);

                swipeRefreshLayout.setRefreshing(false);


//                if (swipeRefreshLayout.isRefreshing()){
//                    if (swipeRefreshLayout.isEnabled()){
//                        swipeRefreshLayout.setEnabled(false);
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                } else {
//                    swipeRefreshLayout.setEnabled(true);
//                }
            }
        });


    }

//    private void refreshHandler() {
//        Handler refreshHandler = new Handler();
//        refreshHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                swipeRefreshLayout.setRefreshing(true);
//                fragment = new HomeFragment();
////                FragmentManager fragmentManager = getSupportFragmentManager();
////                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.replace(R.id.flfragment,fragment);
////                fragmentTransaction.commit();
////                fragmentTransaction.addToBackStack(null);
//                swipeRefreshLayout.setEnabled(true);
//                swipeRefreshLayout.setRefreshing(true);
//            }
//        }, 2000);
//    }


    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();


            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            NavigationView navigationView = findViewById(R.id.nav_view);







            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }



    private void changeFragment(int position) {

        if (position == 1) {
            fragment = new HomeFragment();
            pos = 1;
            title = "Home";
        } else if (position == 2) {
            fragment = new FixturesFragment();
            pos = 2;
            title = "Fixtures";
        } else if (position == 3) {
            fragment = new ResultFragment();
            pos = 3;
            title = "Result";
        } else if (position == 4) {
            fragment = new TabbedNews();
            pos = 4;
            title = "Feed";
        } else if (position == 5){
            fragment = new OtherFragment();
            pos = 5;
        } else if (position == 6){
            pos = 6;
        } else if (position == 7){
            fragment = new ScheduleFragment();
            pos = 7;
            title = "Schedule";
        }

        commitFragment();
    }

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
            case R.id.footer_item_2:
                break;

            case R.id.Gallery:
                changeFragment(6);
                break;
            case R.id.Schedule:
                changeFragment(7);
                break;
            case R.id.Logout:
                signOut();
                finish();
                startActivity(getIntent());
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void showItem() {
        navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.Logout).setVisible(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.footer_item_2:
                signIn();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loding");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }


    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            showItem();
        } else {
            btnSignIn.setVisibility(View.GONE);
            hideItem();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.settings) {
            Intent i = new Intent(getApplicationContext(),SettingActivity.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }

    private void hideItem(){
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.Logout).setVisible(false);
    }

    @Override
    public void sendNewsData(String message) {
        Log.d("LOG","hello" + message);
        myString = message;
    }

    @Override
    public void sendNewsIvData(String ivMessage) {
        Log.d("LOG","hello" + ivMessage);
        myIvString = ivMessage;
    }

    @Override
    public void sendCabolData(int cabolData) {
        Log.d("LOG","hello" + cabolData);
        myCabolData = cabolData;
    }

    @Override
    public void sendResultOrSchedData(int resOrSched) {
        myResOrSched = resOrSched;
    }

    @Override
    public void sendNewsTitleData(String titleMessage) {
        Log.d("LOG","hello" + titleMessage);
        myTitleString = titleMessage;
    }

    public int getCabolData() {
        return myCabolData ;
    }

    public int getMyResOrSched() {
        return myResOrSched ;
    }


    public String getMyTitleString() {
        return myTitleString;
    }

    public String getMyData() {
        return myString;
    }

    public String getMyIvData(){
        return myIvString;
    }

    public void commitFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flfragment,fragment);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
        setTitle(title);

    }

}
