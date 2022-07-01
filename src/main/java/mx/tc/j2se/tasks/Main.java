package mx.tc.j2se.tasks;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello Training Center!\n");

	/*	Test p1

		Task noActive = new TaskImpl();
		noActive.setActive(true);
		System.out.println("Title: " + noActive.getTitle());
		noActive.setTitle("New dent");
		System.out.println("New Title: " + noActive.getTitle());
		System.out.println("isActive: " + noActive.isActive());
		System.out.println("is Rep: " + noActive.isRepeated());
		noActive.setTime(5, 12, 4);
		System.out.println("is Rep: " + noActive.isRepeated());
		System.out.println("start time: " + noActive.getTime());
		System.out.println("endtime: " + noActive.getEndTime());
		System.out.println("interval:" + noActive.getRepeatInterval());
		System.out.println("next time: " + noActive.nextTimeAfter(3));
		System.out.println("next time2: " + noActive.nextTimeAfter(9));
		System.out.println("next time3: " + noActive.nextTimeAfter(0));
		System.out.println("next time4: " + noActive.nextTimeAfter(20));
		System.out.println("next time5: " + noActive.nextTimeAfter(-7));
*/

		//Test p2

		ArrayTaskList lista = new ArrayTaskListImpl();
		Task objeto = new TaskImpl("objeto", 7, 20, 5);
		objeto.setActive(true);
		lista.add(objeto);
		System.out.println("lenght: " + lista.size());

		Task objeto2 = new TaskImpl("objeto2", 5, 18, 3);
		objeto2.setActive(true);
		lista.add(objeto2);
		System.out.println("lenght: " + lista.size());

		Task objeto3 = new TaskImpl("objeto3", 2);
		objeto3.setActive(true);
		lista.add(objeto3);
		System.out.println("lenght: " + lista.size());

		Task objeto4 = new TaskImpl("objeto4", 9);
		objeto4.setActive(true);
		lista.add(objeto4);
		System.out.println("lenght: " + lista.size());

		for (int i = 0; i < lista.size(); i++) {
			lista.getTask(i+1);
		}

		lista.getTask(6);
		System.out.println("%%%%%%%%%%%%%%");
		lista.incoming(80, 100);


		System.out.println("\n \n %%%%%%%%%%%%%%");

		System.out.println("Nexttime ob: " + objeto.nextTimeAfter(-5) + "\n");
		System.out.println("Nexttime ob: " + objeto3.nextTimeAfter(8) + "\n");
		System.out.println("Nexttime ob: " + objeto4.nextTimeAfter(1) + "\n");

		lista.remove(objeto3);


	}
}
