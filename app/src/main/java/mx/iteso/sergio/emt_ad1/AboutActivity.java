package mx.iteso.sergio.emt_ad1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView UnderLinedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UnderLinedText = (TextView)findViewById(R.id.textView7);
        SpannableString content = new SpannableString(getText(R.string.privacy_link));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        UnderLinedText.setText(content);
        UnderLinedText.setMovementMethod(LinkMovementMethod.getInstance());
        //String text = "<a href='http://www.google.com'> Google </a>";
        //UnderLinedText.setText(Html.fromHtml(text));

    }

    public void hiperlink_click(View view) {
        String inURL = "http://ulab.mx/EresMiTipo/";
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse(inURL) );
        startActivity( browse );
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



}
