package info.greglondon.quadstats;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Task> {

    private final Context context;
    private final ArrayList<Task> itemsArrayList;
    DBHandler db;

    public CustomAdapter(Context context, ArrayList<Task> itemsArrayList) {

        super(context, R.layout.content_tasks, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.content_tasks, parent, false);

        TextView taskView = (TextView) rowView.findViewById(R.id.taskInput);
        TextView dateView = (TextView) rowView.findViewById(R.id.taskDate);
        TextView idView = (TextView) rowView.findViewById(R.id.taskId);
        final ImageView deleteView = (ImageView) rowView.findViewById(R.id.taskDelete);

        taskView.setText(itemsArrayList.get(position).getTask());
        dateView.setText(itemsArrayList.get(position).getCreatedOn());
        idView.setText(String.valueOf(itemsArrayList.get(position).getId())); //TODO may not need
        deleteView.setId(itemsArrayList.get(position).getId());

        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DBHandler(context,null,null, 1);
                //TODO add confirm you want to delete
                int id = deleteView.getId();
                String msg;
                msg = db.deleteTask(id) ? "Deleted Task" : "Problem Deleting Task";
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                //TODO refresh list immediately after deletion

            }
        });

        return rowView;
    }

}
