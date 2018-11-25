package info.greglondon.quadstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    EditText taskInput;
    TextView taskText;
    DBHandler dbHandler;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = (EditText) findViewById(R.id.taskInput);
        taskText = (TextView) findViewById(R.id.taskText);
        dbHandler = new DBHandler(this,null,null,1);

        //TODO printDB causing issues - fix
        //printDB();

        /*
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //TODO validate and process items
                Log.v("EditText", txtAmount.getText().toString());
            }
        });
        */
    }

    public void btnDeleteClicked(View view){
        //TODO update to delete by id
        String inputText = taskInput.getText().toString();
        dbHandler.deleteTask(inputText);
    }

    public void btnAddClicked(View view){
        //TODO add validation
        try{
            Tasks task = new Tasks(taskInput.getText().toString());
            dbHandler.addTask(task);
            //TODO fix printDB()
            //printDB();
            taskText.setText("Item added");
        } catch (Exception e){
            Log.v(TAG, e.toString());

        }


    }

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
}
