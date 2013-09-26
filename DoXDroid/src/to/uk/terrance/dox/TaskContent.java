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
		addItem(new Task("00001", "1", "Item 1", 0, new DueDate(new Date(), true), new Repeat(1, true), new ArrayList<String>()));
		addItem(new Task("00002", "2", "Item 2", 1, new DueDate(new Date(), false), new Repeat(3, true), new ArrayList<String>()));
		addItem(new Task("00003", "3", "Item 3", 2, new DueDate(new Date(), true), new Repeat(7, false), new ArrayList<String>()));
	}

	private static void addItem(Task task) {
		ITEMS.add(task);
		ITEM_MAP.put(task.getId(), task);
	}

}
