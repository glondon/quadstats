package info.greglondon.quadstats;

public class Tasks {

    private int _id;
    private String _task;
    //TODO add date property

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

    public int get_id() {
        return _id;
    }

    public String get_task() {
        return _task;
    }
}
