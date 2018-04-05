package e.lifez.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

public class s3register extends AppCompatActivity {
    StringBuilder info = new StringBuilder("");
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3register);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setLogo(getDrawable(R.drawable.ic_action_name));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.next, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.next){
            Intent intent = new Intent(this, s4register.class);
            info.append(((EditText)findViewById(R.id.fname)).getText().toString().trim()+ "%");
            info.append(((EditText)findViewById(R.id.s4_pass)).getText().toString() + "%");
            info.append(((EditText)findViewById(R.id.s2_email)).getText().toString()+ "%");
            info.append(((EditText)findViewById(R.id.cnumber)).getText().toString()+ "%");
            info.append(((EditText)findViewById(R.id.caddress)).getText().toString()+ "%");
            info.append(((Spinner)findViewById(R.id.mstatus)).getSelectedItem().toString()+ "%");
            info.append(((EditText)findViewById(R.id.mnationality)).getText().toString()+ "%");
            info.append(((EditText)findViewById(R.id.ename)).getText().toString()+ "%");
            info.append(((EditText)findViewById(R.id.edate)).getText().toString()+ "%");
            intent.putExtra("cinfo", info.toString());
            startActivity(intent);
        }
        return true;
    }
}
