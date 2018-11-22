package info.greglondon.quadstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txtAmount = (EditText)findViewById(R.id.txtInput);
        final Button addButton = findViewById(R.id.btnSubmit);

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //TODO validate and process items
                Log.v("EditText", txtAmount.getText().toString());
            }
        });
    }
}
