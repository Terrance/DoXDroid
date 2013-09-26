package to.uk.terrance.dox;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskContent {

	// List of dummy tasks.
	public static List<Task> ITEMS = new ArrayList<Task>();
	// A map of dummy items, by ID.
	public static Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

	static {
		ArrayList<String> dummyTagsA = new ArrayList<String>();
		dummyTagsA.add("Foo");
		ArrayList<String> dummyTagsB = new ArrayList<String>();
		dummyTagsB.add("Bar");
		addItem(new Task("00001",
			"Task 1",
			"This is Task 1.  This is a long description to accompany the task in the details view.",
			1,
			new DueDate(Calendar.getInstance(), true),
			new Repeat(1, true),
			new ArrayList<String>()));
		addItem(new Task("00002",
			"Task 2",
			"This is Task 2.  This is a long description to accompany the task in the details view.",
			2,
			new DueDate(Calendar.getInstance(), false),
			new Repeat(3, true),
			dummyTagsA));
		addItem(new Task("00003",
			"Task 3",
			"This is Task 3.  This is a long description to accompany the task in the details view.",
			3,
			new DueDate(Calendar.getInstance(), true),
			new Repeat(7, false),
			dummyTagsB));
	}

	private static void addItem(Task task) {
		ITEMS.add(task);
		ITEM_MAP.put(task.getId(), task);
	}

}
