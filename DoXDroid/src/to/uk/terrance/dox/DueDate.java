package to.uk.terrance.dox;

import java.util.Date;

public class DueDate {

	private Date date;
	private boolean time;
	
	public DueDate(Date date, boolean time) {
		this.date = date;
		this.time = time;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean hasTime() {
		return time;
	}
	public void setTime(boolean time) {
		this.time = time;
	}

}
