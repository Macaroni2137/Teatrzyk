package com.example.loginkino;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class ContactFormActivity extends Activity {
    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mMessageEditText;
    private Button mSendButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        mNameEditText = findViewById(R.id.edit_text_name);
        mEmailEditText = findViewById(R.id.edit_text_email);
        mMessageEditText = findViewById(R.id.edit_text_message);
        mSendButton = findViewById(R.id.button_send);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveContactForm(mNameEditText.getText().toString(),
                       mEmailEditText.getText().toString(),
                       mMessageEditText.getText().toString());
            }
        });
    }
    public void saveContactForm(String name, String email, String message) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        ContactFormData formData = new ContactFormData(name, email, message);

        firestore.collection("contact_form").add(formData);
    }

}