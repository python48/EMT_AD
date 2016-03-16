package mx.iteso.sergio.emt_ad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_profile:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_info:
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_map:
                Intent intent3 = new Intent(this, MapsActivity.class);
                startActivity(intent3);
                return true;
            case R.id.action_about:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public void req_click(View view) {
        Intent intent1 = new Intent(this, InfoRequirements.class);
        startActivity(intent1);
    }

    public void res_click(View view) {
    }

    public void proc_click(View view) {
    }

    public void benef_click(View view) {
    }
}
