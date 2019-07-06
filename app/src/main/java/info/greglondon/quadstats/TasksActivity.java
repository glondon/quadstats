package info.greglondon.quadstats;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
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

        ListView listView = (ListView)findViewById(R.id.taskList);
        db = new DBHandler(this,null,null,1);
        ArrayList<String> tasks = new ArrayList<>();
        Cursor data = db.getAllTasks();
        if(data.getCount() == 0)
            Toast.makeText(TasksActivity.this, "No tasks found", Toast.LENGTH_SHORT).show();
        else{
            while(data.moveToNext()){
                tasks.add(data.getString(1));
                tasks.add(Integer.toString(data.getInt(0)));
                tasks.add(data.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
                listView.setAdapter(listAdapter);
            }
        }
    }

}
