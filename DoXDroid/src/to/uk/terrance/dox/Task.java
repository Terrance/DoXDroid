package to.uk.terrance.dox;

public class Task {

	private String id;
	private String title;
	private String desc;
	private int pri;
	private DueDate due;
	private Repeat repeat;

	public Task(String id, String title, String desc, int pri, DueDate due, Repeat repeat) {
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.pri = pri;
		this.due = due;
		this.repeat = repeat;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getPri() {
		return pri;
	}
	public void setPri(int pri) {
		this.pri = pri;
	}
	public DueDate getDue() {
		return due;
	}
	public void setDue(DueDate due) {
		this.due = due;
	}
	public Repeat getRepeat() {
		return repeat;
	}
	public void setRepeat(Repeat repeat) {
		this.repeat = repeat;
	}
	
	public String toString() {
		return title;
	}

}
