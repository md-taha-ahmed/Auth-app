package com.example.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
public class Home extends AppCompatActivity {
    Button logout;
    TextView name,mail,id;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout=findViewById(R.id.logout);
        mail=findViewById(R.id.email);
        name=findViewById(R.id.name);
        id=findViewById(R.id.user_id);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc= GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            name.setText(account.getDisplayName());
            mail.setText(account.getEmail());
            id.setText(account.getId());
        }
        logout.setOnClickListener(view -> SignOut());
    }
    private  void SignOut(){
        gsc.signOut().addOnCompleteListener(task -> {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });
    }
}