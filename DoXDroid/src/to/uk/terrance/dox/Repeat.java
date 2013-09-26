package to.uk.terrance.dox;

public class Repeat {

	// Number of days until the task repeats
	private int mDays;
	// Repeat from due date instead of completion
	private boolean mFromDue;

	// Main constructor
	public Repeat(int days, boolean fromDue) {
		this.mDays = days;
		this.mFromDue = fromDue;
	}

	// Getters and setters
	public int getDays() {
		return mDays;
	}
	public void setDays(int days) {
		this.mDays = days;
	}
	public boolean isFromDue() {
		return mFromDue;
	}
	public void setFromDue(boolean fromDue) {
		this.mFromDue = fromDue;
	}

	// Custom string representation
	@Override
	public String toString() {
		return "Daily";
	}

}
