package com.example.loginkino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class StartActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;

    private FirebaseAuth auth;
    private TextView register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);


        auth = FirebaseAuth.getInstance();

        register = findViewById(R.id.register);

        login.setOnClickListener(v -> {
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();
            loginUser(txt_email, txt_password);

        });

        register.setOnClickListener((v)->{
                startActivity(new Intent( StartActivity.this , RegisterActivity.class));
                finish();
        });
        email.setOnClickListener((v)->{
            startActivity(new Intent( StartActivity.this , MiejscaActivity.class));
            finish();
        });

    }
    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(StartActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(StartActivity.this, "Zalogowano pomy??lnie", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                    finish();
                    String userEmail = auth.getCurrentUser().getEmail();
                    // Utw??rz nowe intent i przejd?? do pliku MiejscaActivity, przekazuj??c email jako dodatkowy parametr
                    Intent intent = new Intent(StartActivity.this, MiejscaActivity.class);
                    intent.putExtra("userEmail", userEmail);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(StartActivity.this, "Logowanie nieudane, sprawd?? wpisane dane", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}