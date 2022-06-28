package mx.tc.j2se.tasks;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello Training Center!\n");

		TaskImpl noActive = new TaskImpl();
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
	//	System.out.println("next time3: " + noActive.nextTimeAfter(0));
		System.out.println("next time4: " + noActive.nextTimeAfter(20));
		System.out.println("next time5: " + noActive.nextTimeAfter(-7));

	}
}
