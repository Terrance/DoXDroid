package to.uk.terrance.dox;

public class Repeat {

	private int days;
	private boolean fromDue;

	public Repeat(int days, boolean fromDue) {
		this.days = days;
		this.fromDue = fromDue;
	}

	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public boolean isFromDue() {
		return fromDue;
	}
	public void setFromDue(boolean fromDue) {
		this.fromDue = fromDue;
	}

}
