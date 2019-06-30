package info.greglondon.quadstats;

import java.util.Date;

public class Tasks {

    private int id;
    private String task;
    private String createdOn;

    public Tasks(int id, String task, String createdOn) {
        this.id = id;
        this.task = task;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
