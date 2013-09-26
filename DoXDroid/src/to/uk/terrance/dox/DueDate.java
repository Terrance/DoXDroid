package to.uk.terrance.dox;

import java.util.Date;

public class DueDate {

	// Due date fields
	private Date mDate;
	private boolean mTime;

	// Main constructor
	public DueDate(Date date, boolean time) {
		this.mDate = date;
		this.mTime = time;
	}

	// Getters and setters
	public Date getDate() {
		return mDate;
	}
	public void setDate(Date date) {
		this.mDate = date;
	}
	public boolean hasTime() {
		return mTime;
	}
	public void setTime(boolean time) {
		this.mTime = time;
	}

	// Custom string representation
	@Override
	public String toString() {
		return "Today";
	}

}
