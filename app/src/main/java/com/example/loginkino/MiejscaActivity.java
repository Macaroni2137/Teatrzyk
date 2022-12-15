package com.example.loginkino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MiejscaActivity extends AppCompatActivity {

    private Button submitButton;
    private DatePicker datePicker;
    private FirebaseFirestore db;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_miejsca);

            db = FirebaseFirestore.getInstance();

            submitButton = findViewById(R.id.submit_button);
            datePicker = findViewById(R.id.date_picker);
            Intent intent = getIntent();
            String userEmail = intent.getStringExtra("userEmail");

            submitButton.setOnClickListener(view -> {
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int dayOfMonth = datePicker.getDayOfMonth();

                db.collection("rezerwacje")
                        .whereEqualTo("year", year)
                        .whereEqualTo("month", month)
                        .whereEqualTo("dayOfMonth", dayOfMonth)
                        .get()
                        .addOnSuccessListener(snapshot -> {
                            if (snapshot.size() == 0) {
                                Map<String, Object> data = new HashMap<>();

                                data.put("year", year);
                                data.put("month", month);
                                data.put("dayOfMonth", dayOfMonth);

                                data.put("email", userEmail);

                                db.collection("rezerwacje").add(data)
                                        .addOnSuccessListener((result -> {
                                            Toast.makeText(MiejscaActivity.this, "Zarezerwowano miejsce", Toast.LENGTH_SHORT).show();
                                        }));
                            } else {
                                Toast.makeText(MiejscaActivity.this, "Ten termin jest już zarezerwowany", Toast.LENGTH_SHORT).show();
                            }
                        });


            });

            Button btn = (Button)findViewById(R.id.movies_button);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MiejscaActivity.this, OcenyActivity.class));
                }
            });

            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView contact = (TextView) findViewById(R.id.contact_text);

            contact.setOnClickListener((v)->{
                startActivity(new Intent( MiejscaActivity.this , ContactFormActivity.class));
                finish();
            });

        }

    }