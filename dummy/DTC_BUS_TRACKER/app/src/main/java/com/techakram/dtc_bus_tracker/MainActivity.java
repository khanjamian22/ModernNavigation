package com.techakram.dtc_bus_tracker;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import com.techakram.dtc_bus_tracker.adapter.CustomListAdapter;
import com.techakram.dtc_bus_tracker.fragment.fragment_find_bus;
import com.techakram.dtc_bus_tracker.fragment.fragment_find_route;
import com.techakram.dtc_bus_tracker.fragment.fragment_find_stop;
import com.techakram.dtc_bus_tracker.option.AboutActivity;
import com.techakram.dtc_bus_tracker.option.DisclaimerActivity;
import com.techakram.dtc_bus_tracker.option.TourismActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
public class MainActivity extends AppCompatActivity {
    androidx.viewpager.widget.ViewPager mViewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.myViewPager);
        tabLayout = findViewById(R.id.myTabLayout);
        toolbar = findViewById(R.id.autoToolBar);
        setSupportActionBar(toolbar);
        setUpViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
    }
private void setUpViewPager(ViewPager viewPager)
{
    com.techakram.dtc_bus_tracker.ViewPager viewPager1=new com.techakram.dtc_bus_tracker.ViewPager(getSupportFragmentManager());
      viewPager1.addFragments(new fragment_find_bus(),"Find Bus");
      viewPager1.addFragments(new fragment_find_route(),"Find Route");
      viewPager1.addFragments(new fragment_find_stop(),"Find Stop");
      viewPager.setAdapter(viewPager1);
}
//For option menu...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater( ).inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId( )) {
            case R.id.rate:
                Toast.makeText(this, "Rate Us", Toast.LENGTH_LONG).show( );
                return true;
            case R.id.share:
                Intent localIntent = new Intent("android.intent.action.SEND");
                localIntent.setType("text/plain");
                localIntent.putExtra("android.intent.extra.SUBJECT", "Delhi Bus (DTC) Navigator");
                localIntent.putExtra("android.intent.extra.TEXT", "New to Delhi? Having difficulty in travelling? Now, travel across DELHI (NCR) through DTC Buses with 'Delhi Bus Navigator' - Get detail about every Bus Route of the DTC Network.");
                startActivity(Intent.createChooser(localIntent, "Share Via"));
                return true;
            case R.id.about:
                Intent about = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(about);
                return true;
            case R.id.disclaimer:
                Intent disclaimer = new Intent(MainActivity.this, DisclaimerActivity.class);
                startActivity(disclaimer);
                return true;
            case R.id.tourism:
                Intent tour = new Intent(MainActivity.this, TourismActivity.class);
                startActivity(tour);
                return true;
            case R.id.nearbyPlaces:
                Intent map = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(map);
                return true;
            case R.id.exit:
                this.finish( );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public static class DummySectionFragment extends Fragment {
        public static final String ARG_SECTION_NUMBER = "section_number";
        int position;
        Intent i;
        View rootView;
        EditText etSearch;
        ListView lv;
        CustomListAdapter clAdapter;
        DatabaseHelper myDbHelper;
        ArrayList<String> bno = new ArrayList<String>( );
        ArrayList<String> source = new ArrayList<String>( );
        ArrayList<String> destination = new ArrayList<String>( );
        AutoCompleteTextView actv1, actv2;
        LinkedList<String> ll;
        ArrayAdapter<String> arrayAdapter;
        Button btn;
        ImageButton ibSrc, ibDest;
        AutoCompleteTextView actv3;
        ImageButton bFind;
        ListView lvStops;
        LinkedList<String> llSearch, llStops = new LinkedList<String>( );
        ArrayAdapter<String> aa1, aa2;
        public DummySectionFragment()
        {
            // Empty constructor...........
        }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case 100:
                    if (resultCode == Activity.RESULT_OK) {
                        ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        String first = matches.get(0);
                        String uc = first.toUpperCase( );
                        actv1.setText(uc);
                        break;
                    }
                case 150:
                    if (resultCode == Activity.RESULT_OK) {
                        ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        String first = matches.get(0);
                        String uc = first.toUpperCase( );
                        actv2.setText(uc);
                    }
                    break;
                default:
                    break;
            }
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            position = getArguments().getInt(ARG_SECTION_NUMBER);
            myDbHelper = new DatabaseHelper(getActivity());
            try {
                myDbHelper.createDatabase();
            } catch (IOException e) {
                throw new Error("Unable to create database.");
            }
            try {
                myDbHelper.openDatabase();
            } catch (SQLException sqle) {
                throw sqle;
            }
            switch (position) {
                case 0:
                    rootView = inflater.inflate(R.layout.fragment_find_bus, container, false);
                    etSearch = (EditText) rootView.findViewById(R.id.etSearch1);
                    lv = (ListView) rootView.findViewById(R.id.lv1);
                    Cursor c = myDbHelper.getAllBusNumber( );
                    c.moveToFirst( );
                    for (int i = 0; i <= c.getCount( ) - 1; i++) {
                        bno.add(c.getString(0));
                        source.add(c.getString(1));
                        destination.add(c.getString(2));
                        c.moveToNext( );
                    }
                    c.close( );
                    clAdapter = new CustomListAdapter(getActivity( ), bno, source, destination);
                    lv.setAdapter(clAdapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                            Intent i = new Intent("com.techakram.dtc_bus_tracker.ui.ROUTEACTIVITY");
                            i.putExtra("bno", "" + lv.getItemAtPosition(arg2));
                            startActivity(i);
                        }
                    });
                    etSearch.addTextChangedListener(new TextWatcher( ) {
                        @Override
                        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                            try {
                                bno.clear( );
                                source.clear( );
                                destination.clear( );
                                clAdapter.clear( );
                                Cursor c;
                                lv.setAdapter(null);
                                if (etSearch.getText( ).toString( ).contentEquals("")) {
                                    c = myDbHelper.getAllBusNumber( );
                                    c.moveToFirst( );
                                    for (int i = 0; i <= c.getCount( ) - 1; i++) {
                                        bno.add(c.getString(0));
                                        source.add(c.getString(1));
                                        destination.add(c.getString(2));
                                        c.moveToNext( );
                                    }
                                    c.close( );
                                } else {
                                    String search = etSearch.getText( ).toString( );
                                    c = myDbHelper.searchBusNumber(search);
                                    c.moveToFirst( );
                                    for (int i = 0; i <= c.getCount( ) - 1; i++) {
                                        bno.add(c.getString(0));
                                        source.add(c.getString(1));
                                        destination.add(c.getString(2));
                                        c.moveToNext( );
                                    }
                                    c.close( );
                                }
                                clAdapter = new CustomListAdapter(getActivity( ), bno, source, destination);
                                lv.setAdapter(clAdapter);
                            } catch (Exception e) {
                                Toast.makeText(getActivity( ), "ERROR: " + e.getMessage( ), Toast.LENGTH_SHORT).show( );
                            }
                        }
                        @Override
                        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                        }
                        @Override
                        public void afterTextChanged(Editable arg0) {
                        }
                    });
                    break;
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_find_route, container, false);
                    actv1 = (AutoCompleteTextView) rootView.findViewById(R.id.actvSource);
                    actv2 = (AutoCompleteTextView) rootView.findViewById(R.id.actvDestination);
                    btn = (Button) rootView.findViewById(R.id.bFind);
                    ibSrc = (ImageButton) rootView.findViewById(R.id.speakSource);
                    ibDest = (ImageButton) rootView.findViewById(R.id.speakDestination);
                    ll = myDbHelper.getAutoFill( );
                    arrayAdapter = new ArrayAdapter<String>(getActivity( ), android.R.layout.simple_dropdown_item_1line, ll);
                    actv1.setAdapter(arrayAdapter);
                    actv2.setAdapter(arrayAdapter);
                    btn.setOnClickListener(new View.OnClickListener( ) {

                        @Override
                        public void onClick(View v) {
                            InputMethodManager localInputMethodManager = (InputMethodManager) getActivity( ).getSystemService("input_method");
                            localInputMethodManager.hideSoftInputFromWindow(actv1.getWindowToken( ), 0);
                            localInputMethodManager.hideSoftInputFromWindow(actv2.getWindowToken( ), 0);
                            if (actv1.getText( ).toString( ).equals("") && actv2.getText( ).toString( ).equals("")) {
                                Toast.makeText(getActivity( ), "Source and Destination cannot be blank", Toast.LENGTH_SHORT).show( );
                            } else if (actv1.getText( ).toString( ).equals("")) {
                                Toast.makeText(getActivity( ), "Source cannot be blank", Toast.LENGTH_SHORT).show( );
                            } else if (actv2.getText( ).toString( ).equals("")) {
                                Toast.makeText(getActivity( ), "Destination cannot be blank", Toast.LENGTH_SHORT).show( );
                            } else if (actv1.getText( ).toString( ).equalsIgnoreCase(actv2.getText( ).toString( ))) {
                                Toast.makeText(getActivity( ), "Source and Destination cannot be same", Toast.LENGTH_LONG).show( );
                            } else {
                                Intent i = new Intent("com.techakram.dtc_bus_tracker.ui.VIEWROUTEACTIVITY");
                                i.putExtra("source", actv1.getText( ).toString( ));
                                i.putExtra("destination", actv2.getText( ).toString( ));
                                startActivity(i);
                            }
                        }
                    });
                    ibSrc.setOnClickListener(new View.OnClickListener( ) {

                        @Override
                        public void onClick(View arg0) {
                            Intent i1 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                            i1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                            i1.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak The Source!!");
                            startActivityForResult(i1, 100);

                        }


                    });
                    ibDest.setOnClickListener(new View.OnClickListener( ) {

                        @Override
                        public void onClick(View arg0) {
                            Intent i2 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                            i2.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                            i2.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak The Destination!!");
                            startActivityForResult(i2, 150);
                        }


                    });
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_find_stop, container, false);
                    actv3 = (AutoCompleteTextView) rootView.findViewById(R.id.actvStop);
                    bFind = (ImageButton) rootView.findViewById(R.id.bFindStop);
                    lvStops = (ListView) rootView.findViewById(R.id.lvStops);
                    llSearch = myDbHelper.getAutoStop( );
                    aa1 = new ArrayAdapter<String>(getActivity( ), android.R.layout.simple_dropdown_item_1line, llSearch);
                    actv3.setAdapter(aa1);
                    aa2 = new ArrayAdapter<String>(getActivity( ), android.R.layout.simple_list_item_1, llStops);
                    bFind.setOnClickListener(new View.OnClickListener( ) {

                        @Override
                        public void onClick(View v) {
                            String stop = actv3.getText( ).toString( );
                            InputMethodManager imm = (InputMethodManager) getActivity( ).getSystemService("input_method");
                            imm.hideSoftInputFromWindow(actv3.getWindowToken( ), 0);
                            llStops.clear( );
                            aa2.clear( );
                            lvStops.setAdapter(null);
                            if (stop.contentEquals(""))
                                Toast.makeText(getActivity( ), "Enter Stop Name", Toast.LENGTH_LONG).show( );
                            else {
                                llStops = myDbHelper.searchStop(stop);
                                if (llStops.isEmpty( ))
                                    Toast.makeText(getActivity( ), "Nothing found", Toast.LENGTH_LONG).show( );
                                else {
                                    aa2 = new ArrayAdapter<String>(getActivity( ), android.R.layout.simple_list_item_1, llStops);
                                    lvStops.setAdapter(aa2);
                                }
                            }
                        }
                    });
                    lvStops.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                             i = new Intent("com.techakram.dtc_bus_tracker.ui.ROUTEACTIVITY");
                            i.putExtra("bno", "" + lvStops.getItemAtPosition(arg2).toString( ));
                            startActivity(i);
                        }
                    });
                    break;
                default:
                    break;
            }
            return rootView;
        }
     }
}