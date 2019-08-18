package info.greglondon.quadstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import android.app.AlertDialog;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    private EditText taskInput;
    private DBHandler db;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        db = new DBHandler(this,null,null, 1);
    }

    public void btnViewTasksClicked(View view){
        Intent intent = new Intent(MainActivity.this, TasksActivity.class);
        startActivity(intent);
    }

    public boolean btnAddClicked(View view){
        String value = taskInput.getText().toString().trim();
        if(value.length() == 0){
            showMessage("FAILURE", "No task entered");
            return false;
        }
        if(value.length() > 100){
            showMessage("FAILURE", "Task too long");
            return false;
        }

        Task task = new Task(0, value, "");
        db.addTask(task);
        showMessage("Success", "Task added");
        taskInput.setText("");
        return true;

    }

    public void showMessage(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
