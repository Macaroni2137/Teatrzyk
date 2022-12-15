package com.example.loginkino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class OcenyActivity extends AppCompatActivity {

    private static final String TAG = "OcenyActivity";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oceny);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

// Create an onClickListener for each ImageButton in the layout
        ImageButton movie1 = (ImageButton) findViewById(R.id.movie1);
        movie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the ID of the movie that is associated with this ImageButton
                String movieID = String.valueOf(movie1.getId());

                // Query the Firebase Firestore database to retrieve the ratings for this movie
                db.collection("movies").document(movieID).collection("ratings")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                // Calculate the average of the ratings
                                double sum = 0;
                                int count = 0;
                                for (DocumentSnapshot document : queryDocumentSnapshots) {
                                    sum += document.getDouble("rating");
                                    count++;
                                }
                                double average = sum / count;

                                // Display the average on the screen
                                // ...
                            }
                        });
            }
        });

        ImageButton movie2 = (ImageButton) findViewById(R.id.movie2);
        movie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the ID of the movie that is associated with this ImageButton
                String movieID = String.valueOf(movie2.getId());

                // Query the Firebase Firestore database to retrieve the ratings for this movie
                db.collection("movies").document(movieID).collection("ratings")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                // Calculate the average of the ratings
                                double sum = 0;
                                int count = 0;
                                for (DocumentSnapshot document : queryDocumentSnapshots) {
                                    sum += document.getDouble("rating");
                                    count++;
                                }
                                double average = sum / count;

                                // Display the average on the screen
                                // ...
                            }
                        });
            }
        });
        ImageButton movie1Button = findViewById(R.id.movie1);
        final TextView movie1Details = findViewById(R.id.movie1_details);

        movie1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the details for movie1 in the TextView
                String message = "Listy do M\nŚrednia ocen: 9.4\nŁączna liczba ocen: 5642";
                movie1Details.setText(message);
            }
        });

        ImageButton movie2Button = findViewById(R.id.movie2);
        final TextView movie2Details = findViewById(R.id.movie2_details);

        movie2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the details for movie2 in the TextView
                String message = "Kapitan Ameryka\nŚrednia ocen: 8.3\nŁączna liczba ocen: 6520";
                movie2Details.setText(message);
            }
        });

        ImageButton movie3Button = findViewById(R.id.movie3);
        final TextView movie3Details = findViewById(R.id.movie3_details);

        movie3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the details for movie2 in the TextView
                String message = "Dr Strange\nŚrednia ocen: 7.9\nŁączna liczba ocen: 7220";
                movie3Details.setText(message);
            }
        });

        ImageButton movie4Button = findViewById(R.id.movie4);
        final TextView movie4Details = findViewById(R.id.movie4_details);

        movie4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the details for movie2 in the TextView
                String message = "Venom\nŚrednia ocen: 4.2\nŁączna liczba ocen: 8140";
                movie4Details.setText(message);
            }
        });
    }
}

