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
import android.app.ListActivity;

public class TasksActivity extends ListActivity {

    private DBHandler db;
    public static TaskAdapter adapter;
    private ArrayList<Task> tasks;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        adapter = new TaskAdapter(this, generateData());
        setListAdapter(adapter);
    }

    private ArrayList<Task> generateData(){
        tasks = new ArrayList<Task>();
        db = new DBHandler(this,null,null,1);
        Cursor data = db.getAllTasks();
        if(data.getCount() == 0)
            Toast.makeText(TasksActivity.this, "No tasks found", Toast.LENGTH_LONG).show();
        else{
            while(data.moveToNext())
                tasks.add(new Task(data.getInt(0), data.getString(1), data.getString(2)));
        }

        return tasks;
    }

}
