package mx.tc.j2se.tasks;
import java.lang.IllegalArgumentException;

/**
 * ArrayTaskListImpl
 * V.1.1
 * 06.28.22
 * Javier Ramírez Lomas
 */
public class ArrayTaskListImpl implements ArrayTaskList {

    private Task[] array;
    private Task[] temp;
    private boolean success;
    private int cont;
    private int size;

    /**
     * constructor. Initializes variables
     */
    public ArrayTaskListImpl(){
        cont = 0;
        size = 1;
        array = new TaskImpl[1];
        array[0] = new TaskImpl();
    }

    /** adds a new task object to the array
     * @param task  name of the object to be inserted
     */
    @Override
    public void add(Task task) {
        if (cont == size) { growSize(); }
        array[cont] = task;
        cont++;
    }

    /** grows array by 1 element
     */
    public void growSize() {
        if (cont == size) {
            temp = new TaskImpl [size + 1];
            {
                for (int i = 0; i < size; i++) {
                    temp[i] = array[i];
                }
            }
        }
        array = temp;
        size = size + 1;
    }

    /** shrinks array after removing an element
     * @param i     index from the removed task
     */
    public void redo(int i) {
        for ( int x = i; x < cont ; x++) {
            array[x] = array[x + 1];
        }
        if (cont > 0) {
            temp = new TaskImpl[cont];
            for (int y = 0; y < cont; y++) {
                temp [y] = array [y];
            }
        size = cont;
        array = temp;
        }
    }

    /** removes an specific task if it is stored in the task list
     * @param task  task to remove
     * @return
     */
    @Override
    public boolean remove(Task task) {
        success = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == task) {
                array[i] = null;
                cont--;
                redo(i);
                success = true;
            }
        }
        return success;
    }

    /** returns size of array
     * @return  array length
     */
    @Override
    public int size() {
        return array.length;
    }

    /** gets the task in the selected index
     * @param index     index of the selected task. Must be greater than zero
     * @return
     */
    @Override
    public Task getTask(int index) {
        if (index > 0 ) {
            int n = index - 1;
            System.out.println("Title: " + array[n].getTitle());
            System.out.println("    Start time: " + array[n].getTime());
            if (array[n].isRepeated()) {
                System.out.println("    End time: " + array[n].getEndTime());
                System.out.println("    Interval: " + array[n].getRepeatInterval());
            }
            return array[index - 1];
        } else {
            throw new IllegalArgumentException("Invalid Argument. Index must be greater than zero");
        }
    }

    /** select tasks in the range selected
     * @param from  start time of the range
     * @param to    end time of the range
     * @return      task array. Not in version 1.1
     */
    @Override
    public ArrayTaskList incoming(int from, int to) {
        temp = new TaskImpl [size];
        int j = 0;
        for (Task task : array) {
            if (!task.isRepeated()) {
                if (((task.getStartTime()) > from) && ((task.getStartTime()) < to)) {
                    temp[j] = task;
                    j++;
                }
            } else if (((task.getStartTime()) > from) && ((task.getStartTime()) < to)) {
                    temp[j] = task;
                    j++;
                } else if (((task.nextTimeAfter(from)) > from) && ((task.nextTimeAfter(from)) < to)) {
                    temp[j] = task;
                    j++;
                }

        }
        for (int y = 0; y < j; y++) {
            System.out.println("Title: " + temp[y].getTitle() );
            System.out.println("    Start time: " + temp[y].getTime() );
            if (temp[y].isRepeated()) {
                System.out.println("    End time: " + temp[y].getEndTime() );
                System.out.println("    Interval: " + temp[y].getRepeatInterval() );
            }
        }
        return null;
    }
}
