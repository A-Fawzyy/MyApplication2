package e.lifez.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class s4register extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    String info, pass;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s4register);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setLogo(getDrawable(R.drawable.ic_action_name));

        auth = FirebaseAuth.getInstance();
        Bundle bundle = getIntent().getExtras();
        String info = bundle.getString("cinfo");
        final String[] arr = info.split("%");
        System.out.println("a7eeh"+ arr[0]);

        mDatabase = FirebaseDatabase.getInstance().getReference().child(arr[0]);
        Button finish = findViewById(R.id.s4_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((EditText)findViewById(R.id.s4_pass)).getText().toString().equals(( (EditText)findViewById(R.id.s2_email)).getText().toString()) ){
                    pass = ((EditText)findViewById(R.id.s4_pass)).getText().toString();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Password mismatch!", Toast.LENGTH_SHORT).show();

                }

                if (TextUtils.isEmpty(arr[2])) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mDatabase.child("Last Name").setValue(arr[1]);
                mDatabase.child("Email").setValue(arr[2]);
                mDatabase.child("Contact Number").setValue(arr[3]);
                mDatabase.child("Current Address").setValue(arr[4]);
                mDatabase.child("Marital Status").setValue(arr[5]);
                mDatabase.child("Nationality").setValue(arr[6]);
                mDatabase.child("Employer Name").setValue(arr[7]);
                mDatabase.child("Engagement Date").setValue(arr[8]);
                mDatabase.child("Password").setValue(pass);

                auth.createUserWithEmailAndPassword(arr[2], pass)
                        .addOnCompleteListener(s4register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(s4register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(s4register.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(s4register.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
