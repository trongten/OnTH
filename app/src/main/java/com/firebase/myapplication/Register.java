package com.firebase.myapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //
        mAuth = FirebaseAuth.getInstance();

        EditText email = findViewById(R.id.email_gis);
        EditText password = findViewById(R.id.password_gis);
        EditText repass = findViewById(R.id.repassword_gis);
        Button register = findViewById(R.id.register_gis);
        Button login = findViewById(R.id.login_gis);

        if(password.getText().toString().equalsIgnoreCase(repass.getText().toString())){

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            createAccount(email.getText().toString(),password.getText().toString());

            }
        });
        }else{
            Toast.makeText(Register.this, "Password ko trung failed.",
                    Toast.LENGTH_SHORT).show();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),Login.class);
                startActivity(i);
            }
        });

    }


    public void createAccount(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i  = new Intent(getBaseContext(),Login.class);
                            startActivity(i);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }


    private void updateUI(FirebaseUser user) {
        if(user != null){
            Toast.makeText(Register.this, "createUserWithEmail:success",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Register.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }


}