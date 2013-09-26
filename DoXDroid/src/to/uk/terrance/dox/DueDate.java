package to.uk.terrance.dox;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.graphics.Color;

public class DueDate {

    // Due date relative times
    public static int DATE_OTHER = 0;
    public static int DATE_SOON = 1;
    public static int DATE_NOW = 2;
    public static int DATE_OVERDUE = 3;

    // Due date colours
    public static int[] DATE_COLOURS = new int[]{Color.GRAY, Color.rgb(255, 171, 0), Color.rgb(255, 85, 0), Color.rgb(224, 0, 0)};

    // Due date fields
    private Calendar mDate;
    private boolean mTime;

    // Main constructor
    public DueDate(Calendar date, boolean time) {
        mDate = date;
        mTime = time;
    }

    // Getters and setters
    public Calendar getDate() {
        return mDate;
    }
    public void setDate(Calendar date) {
        mDate = date;
    }
    public boolean hasTime() {
        return mTime;
    }
    public void setTime(boolean time) {
        mTime = time;
    }

    // Select colour based on current time
    public int getRelative() {
        Calendar now = Calendar.getInstance();
        long nowDays = (long) Math.floor((double) now.getTimeInMillis() / (1000 * 60 * 60 * 24));
        long dateDays = (long) Math.floor((double) mDate.getTimeInMillis() / (1000 * 60 * 60 * 24));
        // Due time is after current time, or current day count is higher than due date
        if ((mTime && now.after(mDate)) || (!mTime && nowDays - dateDays < 0)) {
            return DATE_OVERDUE;
        // Due date is today
        } else if (nowDays - dateDays == 0) {
            return DATE_NOW;
        // Due date is tomorrow
        } else if (nowDays - dateDays == 1) {
            return DATE_SOON;
        }
        // Due date is not coming up soon
        return DATE_OTHER;
    }

    // Custom string representation
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy" + (mTime ? " hh:mm:ss" : ""), Locale.getDefault());
        return formatter.format(mDate.getTime());
    }

}
