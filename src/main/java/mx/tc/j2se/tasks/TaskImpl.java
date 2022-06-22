package mx.tc.j2se.tasks;
import java.lang.IllegalArgumentException;

/**
 * TaskImpl
 * V.1.2
 * 06.20.22
 * Javier Ramirez Lomas
 */

public class TaskImpl implements Task {

    private String title;
    private int startTime;
    private int endTime;
    private int period;
    private int nextTime;
    private boolean isActive;
    private boolean isRepetitive;


    public TaskImpl () {
        /* class constructor with no arguments */
    }

    public TaskImpl (String title, int time) {
        /* class constructor. Name of the task and start time are specified. Inactive task declared */
        this.title = title;
        this.startTime = time;
        isActive = false;
        isRepetitive = false;
    }

    public TaskImpl (String title, int iniTime, int endTime, int interval) {
        /* class constructor. Name of the task, start time and end time are specified.
         * Inactive task declared. Repetitive task declared */
        this.title = title;
        this.startTime = iniTime;
        if (endTime > iniTime) {
            this.endTime = endTime;
        } else {
            throw new IllegalArgumentException("Task cannot ends before it starts");
        }
        if (interval > 0) {
            this.period = interval;
        } else {
            throw new IllegalArgumentException("Task must be performed one time");
        }
        isActive = false;
        isRepetitive = true;
    }

    /* Gets the name of the task */
    @Override
    public String getTitle() {
        return title;
    }

    /* Assigns the name to the task */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /* Gets the activeness status of the task */
    @Override
    public boolean isActive() {
        return isActive;
    }

    /* Assigns the activeness status to the task */
    @Override
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /* Gets the start time of the task */
    @Override
    public int getTime() {
        return startTime;
    }

    /* Assigns the start time of the task, change Activeness status in case of a repetitive task */
    @Override
    public void setTime(int time) {
        if (isRepetitive) {
            this.isRepetitive = false;
        }
        this.startTime = time;
        /* End time should be match to Start time in order to avoid confusion? */
        this.endTime = time;
    }

    /* Gets start time or time of execution of the Task */
    @Override
    public int getStartTime() {
        return startTime;
    }

    /* Gets the end time of the task */
    @Override
    public int getEndTime() {
        if (!isRepetitive) {
            return startTime;
        } else {
            return endTime;
        }
    }

    /* Gets the period of the task */
    @Override
    public int getRepeatInterval() {
        if (!isRepetitive) {
            return 0;
        } else {
            return period;
        }

    }

    /* Assigns start time, end time and an interval. Sets isRepetitive a true status */
    @Override
    public void setTime(int start, int end, int interval) {
        this.startTime = start;
        if (end > start) {
            this.endTime = end;
        } else {
            throw new IllegalArgumentException("Task cannot ends before it starts");
        }
        if (interval > 0) {
            this.period = interval;
        } else {
            throw new IllegalArgumentException("Task must be performed one time");
        }
        isRepetitive = true;
    }

    /* Gets the status of isRepetitive */
    @Override
    public boolean isRepeated() {
        return isRepetitive;
    }

    /* Verify the next time a task would be executed */
    @Override
    public int nextTimeAfter(int current) {
        if (current == startTime){
            System.out.println("Task is running right now!!");
        }
        if (current < startTime) {
            this.nextTime = startTime - current;
        }
        if ((current > startTime) && (current < endTime)) {
            isActive = true;
            int lap = current - startTime;
            int unoAnt = lap / period;
            nextTime = startTime + (period * (unoAnt + 1));
        }
        if ((!isRepetitive) && (current > startTime)) {
            System.out.println("Non Repetitive Task already executed on " + startTime );
            this.nextTime = -1;
        }

        if ((current > endTime) && (isRepetitive)) {
            isActive = false;
            System.out.println("Repetitive task is not active anymore");
            this.nextTime = -1;
        }
        return nextTime;
    }
}
