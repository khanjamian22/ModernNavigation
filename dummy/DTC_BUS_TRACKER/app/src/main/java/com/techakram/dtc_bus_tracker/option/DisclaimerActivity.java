package com.techakram.dtc_bus_tracker.option;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.techakram.dtc_bus_tracker.R;
public class DisclaimerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public ListView lv1;
    public String[] helpline = {"Women's Helpline" ,"Toll Free Number", "DTC Central Control Room"
            ,"DTC East Control Room", "DTC West Control Room", "DTC North Control Room",
            "DTC South Control Room", "DTC Rural Control Room", "FOR First Aid"};
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        lv1 = (ListView) findViewById(R.id.lv1);
        lv1.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, helpline));
        lv1.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.disclaimer,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy( );
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long arg3) {
        switch (arg2) {
            case 0:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:1091"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},0);
                    return;
                }
                startActivity(callIntent);
                break;
            case 1:
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                callIntent1.setData(Uri.parse("tel:1800118181"));
                startActivity(callIntent1);
                break;
            case 2:
                Intent callIntent2 = new Intent(Intent.ACTION_CALL);
                callIntent2.setData(Uri.parse("tel:9818999098"));
                startActivity(callIntent2);
                break;
            case 3:
                Intent callIntent3 = new Intent(Intent.ACTION_CALL);
                callIntent3.setData(Uri.parse("tel:9818999108"));
                startActivity(callIntent3);
                break;
            case 4:
                Intent callIntent4 = new Intent(Intent.ACTION_CALL);
                callIntent4.setData(Uri.parse("tel:9818999105"));
                startActivity(callIntent4);
                break;
            case 5:
                Intent callIntent5 = new Intent(Intent.ACTION_CALL);
                callIntent5.setData(Uri.parse("tel:9871383511"));
                startActivity(callIntent5);
                break;
            case 6:
                Intent callIntent6 = new Intent(Intent.ACTION_CALL);
                callIntent6.setData(Uri.parse("tel:9871383525"));
                startActivity(callIntent6);
                break;
            case 7:
                Intent callIntent7 = new Intent(Intent.ACTION_CALL);
                callIntent7.setData(Uri.parse("tel:9871383523"));
                startActivity(callIntent7);
                break;
            case 8:
                Intent callIntent8 = new Intent(Intent.ACTION_CALL);
                callIntent8.setData(Uri.parse("tel:8193812807"));
                startActivity(callIntent8);
                break;
        }
    }
}