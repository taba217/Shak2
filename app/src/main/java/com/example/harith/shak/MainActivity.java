package com.example.harith.shak;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.harith.shak.Fragment.FragmentOne;
import com.example.harith.shak.Fragment.PagerAdapter;
import com.example.harith.shak.db.DataBaseHelper;
import com.example.harith.shak.db.LecAdapter;
import com.example.harith.shak.db.Users;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         helper = new DataBaseHelper(this);
         helper.insertUser();
         displayDataBaseInfo();


        LoadFragment(new FragmentOne());

        TabLayout tabLayout =  findViewById(R.id.tab);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager =  findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Toast.makeText(MainActivity.this, "" + tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    private void LoadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag, fragment)
                .commit();
    }

    public void displayDataBaseInfo() {
        DataBaseHelper helper = new DataBaseHelper(this);


        try {

            SQLiteDatabase database = helper.getReadableDatabase();

            Cursor c = database.rawQuery("SELECT * FROM users",null);


            Toast.makeText(MainActivity.this,"NUM " + c.getCount() ,Toast.LENGTH_LONG).show();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
