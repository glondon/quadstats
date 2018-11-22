package info.greglondon.quadstats;

import java.util.Date;

public class Tasks {

    private int _id;
    private String _task;
    private Date _created_on;

    public Tasks(){

    }

    public Tasks(String task) {
        this._task = task;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_task(String _task) {
        this._task = _task;
    }

    public void set_created(Date _created_on) {
        //TODO will need to parse
        this._created_on = _created_on;
    }

    public int get_id() {
        return _id;
    }

    public String get_task() {
        return _task;
    }

    public Date get_created_on() {
        return _created_on;
    }
}
