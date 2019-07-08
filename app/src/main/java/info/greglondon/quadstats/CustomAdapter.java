package info.greglondon.quadstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Task> {

    private final Context context;
    private final ArrayList<Task> itemsArrayList;

    public CustomAdapter(Context context, ArrayList<Task> itemsArrayList) {

        super(context, R.layout.content_tasks, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.content_tasks, parent, false);

        TextView taskView = (TextView) rowView.findViewById(R.id.taskInput);
        TextView dateView = (TextView) rowView.findViewById(R.id.taskDate);
        TextView idView = (TextView) rowView.findViewById(R.id.taskId);

        taskView.setText(itemsArrayList.get(position).getTask());
        dateView.setText(itemsArrayList.get(position).getCreatedOn());
        idView.setText(String.valueOf(itemsArrayList.get(position).getId()));

        return rowView;
    }

}
