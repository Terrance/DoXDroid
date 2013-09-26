package to.uk.terrance.dox;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskContent {

	public static List<Task> ITEMS = new ArrayList<Task>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

	static {
		ArrayList<String> dummyTagsA = new ArrayList<String>();
		dummyTagsA.add("Foo");
		ArrayList<String> dummyTagsB = new ArrayList<String>();
		dummyTagsB.add("Bar");
		addItem(new Task("00001",
				 "Task 1",
				 "This is Task 1.  This is a long description to accompany the task in the details view.",
				 0,
				 new DueDate(new Date(), true),
				 new Repeat(1, true),
				 new ArrayList<String>()));
		addItem(new Task("00002",
				 "Task 2",
				 "This is Task 2.  This is a long description to accompany the task in the details view.",
				 0,
				 new DueDate(new Date(), true),
				 new Repeat(1, true),
				 dummyTagsA));
		addItem(new Task("00003",
				 "3",
				 "This is Task 3.  This is a long description to accompany the task in the details view.",
				 0,
				 new DueDate(new Date(), true),
				 new Repeat(1, true),
				 dummyTagsB));
	}

	private static void addItem(Task task) {
		ITEMS.add(task);
		ITEM_MAP.put(task.getId(), task);
	}

}
