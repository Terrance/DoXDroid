package to.uk.terrance.dox;

import java.util.ArrayList;
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
		addItem(new Task("00001", "1", "Item 1", 0, null, null));
		addItem(new Task("00002", "2", "Item 2", 1, null, null));
		addItem(new Task("00003", "3", "Item 3", 2, null, null));
	}

	private static void addItem(Task task) {
		ITEMS.add(task);
		ITEM_MAP.put(task.getId(), task);
	}

}
