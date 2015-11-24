package com.example.caimogu.webcrawler;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherListView extends ListActivity {
    public TeacherManager tm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_teacher_list_view);
        tm = new TeacherManager();
        Log.i("Caimogu","TeacherListView----onCreate");
        if(tm == null)
        {
            Intent intent = this.getIntent();
            Log.i("Caimogu","teacherMana--is null--onCreate");
            tm = (TeacherManager)intent.getSerializableExtra("teacherMana");
        }

        SimpleAdapter sadapter = new SimpleAdapter(this,getData(),R.layout.activity_teacher_list_view,
                                    new String[]{"id","name"},
                                    new int[]{R.id.tid,R.id.tname});
        setListAdapter(sadapter);

    }

    public List<Map<String,Object>> getData()
    {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("id", "G1");
//        map.put("name", "google 1");
//        list.add(map);

        for(Teacher t:tm.list)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", "ID:"+t.getId());
            map.put("name", "Name:"+t.getName());
            list.add(map);
            //System.out.println(t.getId()+"---"+t.getName());
        }

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teacher_list_view, menu);
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
}
