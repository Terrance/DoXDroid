package to.uk.terrance.dox;

public class Repeat {

    // Number of days until the task repeats
    private int mDays;
    // Repeat from due date instead of completion
    private boolean mFromDue;

    // Main constructor
    public Repeat(int days, boolean fromDue) {
        mDays = days;
        mFromDue = fromDue;
    }

    // Getters and setters
    public int getDays() {
        return mDays;
    }
    public void setDays(int days) {
        mDays = days;
    }
    public boolean isFromDue() {
        return mFromDue;
    }
    public void setFromDue(boolean fromDue) {
        mFromDue = fromDue;
    }

    // Custom string representation
    @Override
    public String toString() {
        String out = mDays + " days";
        switch (mDays) {
            case 1: 
                out = "Daily";
                break;
            case 7: 
                out = "Weekly";
                break;
            case 14: 
                out = "Fortnightly";
                break;
        }
        return out + " from " + (mFromDue ? "due date" : "completion");
    }

}
