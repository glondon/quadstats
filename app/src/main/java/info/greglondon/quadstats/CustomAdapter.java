package info.greglondon.quadstats;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Confirm Deletion");
                b.setMessage("Are you sure?");
                b.setCancelable(false);
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db = new DBHandler(context,null,null, 1);
                        int id = deleteView.getId();
                        String msg;
                        msg = db.deleteTask(id) ? "Deleted Task" : "Problem Deleting Task";
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        //TODO refresh list immediately after deletion
                        try{
                            TasksActivity.adapter.notifyDataSetChanged(); //TODO not working
                        }
                        catch (Throwable e){
                            e.getMessage();
                        }


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
