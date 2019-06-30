package info.greglondon.quadstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import android.app.AlertDialog;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    EditText taskInput;
    DBHandler db;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = (EditText) findViewById(R.id.taskInput);
        db = new DBHandler(this,null,null,1);

        //TODO printDB causing issues - fix
        //printDB();
        //TODO fix this method
        //getAllTasks();

        /*
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //TODO validate and process items
                Log.v("EditText", txtAmount.getText().toString());
            }
        });
        */
    }
    /*
    public void btnDeleteClicked(View view){
        //TODO update to delete by id
        String inputText = taskInput.getText().toString();
        db.deleteTask(inputText);
        showMessage("Success", "Task deleted");
        taskInput.setText("");
    }
    */

    public void btnViewTasksClicked(View view){

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
        //TODO fix printDB()
        //printDB();
        //taskText.setText("Item added");
        showMessage("Success", "Task added");
        taskInput.setText("");
        return true;

    }
    /*
    public void printDB() {
        try{
            String dbString = dbHandler.dbToString();
            taskText.setText(dbString);
            taskInput.setText("");
        }catch(Exception e){
            Log.v(TAG, e.toString());
            taskText.setText("printDB Failed");
        }

    }
    */

    public void showMessage(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

    public void getAllTasks(){
        //db = new DBHandler(this,null,null,1);
        Cursor res = db.getAllTasks();
        if(res.getCount() == 0) {
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //buffer.append("ID :"+ res.getString(0)+"\n");
            buffer.append("Task :"+ res.getString(1)+"\n");
        }

        // Show all data
        showMessage("Tasks", buffer.toString());
    }
}
