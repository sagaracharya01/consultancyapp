package com.example.pc.consultancyguider;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String Viewname;
    TextView usernav;
    View hview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i=getIntent();
        Viewname=i.getStringExtra("Vname");

        CardView cardView=findViewById(R.id.consultid);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ConsultnacyList.class);
                intent.putExtra("name",Viewname);
                startActivity(intent);


               // Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
        CardView newsView=findViewById(R.id.newsid);
        newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewsField.class);
                startActivity(intent);
                // Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
        final CardView statusView=findViewById(R.id.userviewid);
        statusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,StatusRecycle.class);
                intent.putExtra("data",Viewname);
                startActivity(intent);

                // Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        //................floating button..................

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent intent=new Intent(MainActivity.this,SubmitForm.class);
                startActivity(intent);
            }
        });

        //.......................navigation bar.................

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        hview=navigationView.getHeaderView(0);

        usernav=(TextView)hview.findViewById(R.id.profilename);
        usernav.setText(Viewname);
        navigationView.setNavigationItemSelectedListener(this);
    }


      //.....................backclick........................
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
      //...................menu logout..........................
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //....................logout from mainactivity.................

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
            //Intent intent=new Intent(this,LoginPage.class);
           // startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //.....................navigation item click listenser................

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_rating) {

            Intent intent=new Intent(MainActivity.this,RatingList.class);
            intent.putExtra("name",Viewname);
            startActivity(intent);

        } else if (id == R.id.nav_ranking) {
            Intent intent=new Intent(MainActivity.this,RankingList.class);
            intent.putExtra("name",Viewname);
            startActivity(intent);


        } else if (id == R.id.nav_form) {
            Intent intent=new Intent(MainActivity.this,FormKey.class);
            intent.putExtra("name",Viewname);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //.................function for logout......................
    public void logout(){
        new User(MainActivity.this).removeUser();
        Intent intent=new Intent(MainActivity.this,LoginPage.class);
        startActivity(intent);
        finish();




    }
}
