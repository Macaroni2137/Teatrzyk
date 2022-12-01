package com.example.loginkino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MiejscaActivity extends AppCompatActivity {

    private Button submitButton;
    private DatePicker datePicker;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miejsca);

        db = FirebaseFirestore.getInstance();

        submitButton = findViewById(R.id.submit_button);
        datePicker = findViewById(R.id.date_picker);

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
                            Map<String, Integer> data = new HashMap<>();
                            data.put("year", year);
                            data.put("month", month);
                            data.put("dayOfMonth", dayOfMonth);

                            db.collection("rezerwacje").add(data)
                                    .addOnSuccessListener((result -> {
                                        Toast.makeText(MiejscaActivity.this, "Zarezerwowano miejsce", Toast.LENGTH_SHORT).show();
                                    }));
                        } else {
                            Toast.makeText(MiejscaActivity.this, "Ten termin jest już zarezerwowany", Toast.LENGTH_SHORT).show();
                        }
                    });


        });


    }


}