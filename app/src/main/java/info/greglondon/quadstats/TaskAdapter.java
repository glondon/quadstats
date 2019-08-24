package info.greglondon.quadstats;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    private final Context context;
    private final ArrayList<Task> itemsArrayList;
    private DBHandler db;

    public TaskAdapter(Context context, ArrayList<Task> itemsArrayList) {

        super(context, R.layout.content_tasks, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.content_tasks, parent, false);

        TextView taskView = rowView.findViewById(R.id.taskInput);
        TextView dateView = rowView.findViewById(R.id.taskDate);
        TextView idView = rowView.findViewById(R.id.taskId);
        final ImageView deleteView = rowView.findViewById(R.id.taskDelete);

        taskView.setText(itemsArrayList.get(position).getTask());
        dateView.setText(itemsArrayList.get(position).getCreatedOn());
        idView.setText(String.valueOf(itemsArrayList.get(position).getId())); //TODO may not need
        deleteView.setId(itemsArrayList.get(position).getId());

        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Confirm Deletion");
                b.setMessage("Are you sure?");
                b.setCancelable(false);
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db = new DBHandler(context,null,null, 1);
                        String msg;
                        if(db.deleteTask(deleteView.getId())){
                            msg = "Task Deleted";
                            TasksActivity.adapter.remove(itemsArrayList.get(position));
                            TasksActivity.adapter.notifyDataSetChanged();
                        }
                        else
                            msg = "Problem Deleting Task";

                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                b.show();

            }
        });

        return rowView;
    }

}
