package info.greglondon.quadstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button addButton = findViewById(R.id.btnSubmit);
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //TODO validate and process items
            }
        });
    }
}
