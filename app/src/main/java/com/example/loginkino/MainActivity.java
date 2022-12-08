package com.example.loginkino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    public NavigationView mNavigationView;
    private ActionBarDrawerToggle toggle;
    private Button logout;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null){
            Intent fail = new Intent(MainActivity.this, MiejscaActivity.class);
            startActivity(fail.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            mNavigationView.setCheckedItem(R.id.nav_reservation);
        }

        //        logout = findViewById(R.id.logout);

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Toast.makeText(MainActivity.this, "Wylogowano", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this, StartActivity.class));
//                finish();
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                mNavigationView.setCheckedItem(R.id.nav_profile);
                break;
            case R.id.nav_tickets:
                Intent tickets = new Intent(MainActivity.this, TicketsActivity.class);
                startActivity(tickets.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                mNavigationView.setCheckedItem(R.id.nav_tickets);
                break;
            case R.id.nav_reservation:
                Intent res = new Intent(MainActivity.this, MiejscaActivity.class);
                startActivity(res.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                mNavigationView.setCheckedItem(R.id.nav_reservation);
                break;
            case R.id.logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}