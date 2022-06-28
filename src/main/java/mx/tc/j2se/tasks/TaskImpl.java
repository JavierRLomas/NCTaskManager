package mx.tc.j2se.tasks;
import java.io.IOException;
import java.lang.IllegalArgumentException;

/**
 * TaskImpl
 * V.1.3
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

    /**
     * class constructor with no arguments
     */
    public TaskImpl () { }

    /**
     * class constructor. Name of the task and start time are specified. Inactive task declared
     * @param title title of the task
     * @param time  start time of the task
     */
    public TaskImpl (String title, int time) {
        this.title = title;
        this.startTime = time;
        isActive = false;
        isRepetitive = false;
    }

    /**
     * class constructor. Name of the task and start time are specified. Inactive task declared
     * @param title     title of the task
     * @param iniTime   start time of the task
     * @param endTime   end time of the task
     * @param interval  interval of repetition
     */
    public TaskImpl (String title, int iniTime, int endTime, int interval) {
        /* class constructor. Name of the task, start time and end time are specified.
         * Inactive task declared. Repetitive task declared */
        this.title = title;
        this.startTime = iniTime;
        if (endTime > iniTime) {
            this.endTime = endTime;
        } else {
            throw new IllegalArgumentException("Invalid Argument: Task cannot ends before it starts");
        }
        if (interval > 0) {
            this.period = interval;
        } else {
            throw new IllegalArgumentException("Invalid Argument: Interval must be greater than zero");
        }
        isActive = false;
        isRepetitive = true;
    }

    /**
     *  gets the title of the task
     * @return title of the task
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * assigns the title to the task
     * @param title title of the task
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the activeness status of the task
     * @return boolean value of isActive
     */
    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * sets the activeness status of the task
     * @param active    boolean value of isActive
     */
    @Override
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
     * gets the start time of the task
     * @return startTime of the task
     */
    @Override
    public int getTime() {
        return startTime;
    }

    /**
     *  Assigns the start time of the task, change Activeness status in case of a repetitive task
     * @param time  start time of the task
     */
    @Override
    public void setTime(int time) {
        if (isRepetitive) {
            this.isRepetitive = false;
        }
        this.startTime = time;
        /* End time should be match to Start time in order to avoid confusion? */
        this.endTime = time;
    }

    /**
     *  Gets start time or time of execution of the Task
     * @return start time of the task
     */
    @Override
    public int getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the task
     * @return  end time of the task. If the task is non-repetitive returns start time
     */
    @Override
    public int getEndTime() {
        if (isRepetitive) {
            return endTime;
        } else {
            System.out.println("Is not a repetitive task. No finalization time");
            return startTime;
        }
    }

    /**
     * Gets the end time of the task
     * @return  interval of the task or zero if the task is non-repetitive
     */
    @Override
    public int getRepeatInterval() {
        if (!isRepetitive) {
            return 0;
        } else {
            return period;
        }

    }

    /**
     *  Assigns start time, end time and an interval. Sets isRepetitive a true status
     * @param start     start time of the task
     * @param end       end time of the task
     * @param interval  interval of repetition
     */
    @Override
    public void setTime(int start, int end, int interval) {
        this.startTime = start;
        if (end > start) {
            this.endTime = end;
        } else {
            throw new IllegalArgumentException("Invalid Argument: Task cannot ends before it starts");
        }
        if (interval > 0) {
            this.period = interval;
        } else {
            throw new IllegalArgumentException("Invalid Argument: Interval must be greater than zero");
        }
        isRepetitive = true;
    }

    /**
     * gets the status of isRepetitive
     * @return boolean value of isRepetitive
     */
    @Override
    public boolean isRepeated() {
        return isRepetitive;
    }

    /**
     *  Verify the next time a task would be executed
     * @param current time selected to verify next event
     */
    @Override
    public int nextTimeAfter(int current) {
        if (current <= 0){
            nextTime = -1;
            throw new IllegalArgumentException("Invalid Argument: Cannot go before today");
        } else if (isActive) {
            System.out.println("Task is active.");
            if (isRepetitive) {
                if (current == startTime) {
                    nextTime = startTime + period;
                    System.out.println("Task starts today and next will be on: " + nextTime);
                } else if (current < startTime) {
                    nextTime = startTime;
                    System.out.println("Next time will be on " + nextTime);
                } else if (current < endTime) {
                    int lap = current - startTime;
                    int unoAnt = lap / period;
                    nextTime = startTime + (period * (unoAnt + 1));
                    System.out.println("next time = " + nextTime);
                } else if (current > endTime) {
                    System.out.println("The task will not be executed anymore");
                    nextTime = -1;
                } else {
                    nextTime = -1;
                    throw new IllegalArgumentException("Invalid Argument");
                }
            } else {
                System.out.println("Task is non repetitive.");
                if (current > startTime) {
                    System.out.println("Task has already executed on " + startTime);
                    nextTime = -1;
                } else if (current < startTime) {
                    nextTime = startTime;
                    System.out.println("First execution on " + nextTime);
                } else {
                    System.out.println("There is no next time.");
                    nextTime = -1;
                }
            }
        } else {
            nextTime = -1;
            throw new IllegalArgumentException("Invalid Argument: Task is not active");
        }
        return nextTime;
    }
}
