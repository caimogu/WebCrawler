package com.example.caimogu.webcrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private MyHttpClient myHttpClient;
    private ListView listView;
    private TeacherManager teacherMana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHttpClient = new MyHttpClient();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void send(View view)
    {
        Log.i("Caimogu","send()......");
        new Thread()
        {
            @Override
            public void run()
            {
                try {
                    myHttpClient.getClient();
                    teacherMana = new TeacherManager();
                    Log.i("Caimogu", "Intent............");
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,TeacherListView.class);
                    //intent.setAction("TeacherListView");
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
    public void showList(View v)
    {
        new Thread()
        {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TeacherListView.class);
                //intent.setAction("TeacherListView");
                Bundle bundle = new Bundle();
                bundle.putSerializable("teacherMana", teacherMana);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }.start();

    }

}
