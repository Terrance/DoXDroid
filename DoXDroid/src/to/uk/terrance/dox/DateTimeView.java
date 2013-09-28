package to.uk.terrance.dox;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTimeView {

    // Date picker fields
    private Calendar mCalendar;
    private Calendar mCalendarTemp;
    private boolean mTime;

    // Main constructor
    public DateTimeView(final Context context, final TextView editField, final View buttonDueSet, final View buttonDueClear) {
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                int year = mCalendarTemp.get(Calendar.YEAR);
                int month = mCalendarTemp.get(Calendar.MONTH);
                int day = mCalendarTemp.get(Calendar.DAY_OF_MONTH);
                // Store selected values
                mCalendar.set(Calendar.HOUR_OF_DAY, hour);
                mCalendar.set(Calendar.MINUTE, minute);
                mTime = true;
                // Update text field with new value
                editField.setText(pad(day) + "/" + pad(month) + "/" + year + " " + pad(hour) + ":" + pad(minute));
            }
        };
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Store the values so far
                mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, day);
                mCalendar.set(Calendar.HOUR_OF_DAY | Calendar.MINUTE | Calendar.SECOND | Calendar.MILLISECOND, 0);
                // Update text field with new value
                editField.setText(pad(day) + "/" + pad(month) + "/" + year);
                buttonDueClear.setEnabled(true);
                mTime = false;
                // Create time dialog
                int hour = mCalendarTemp.get(Calendar.HOUR_OF_DAY);
                int minute = mCalendarTemp.get(Calendar.MINUTE);
                new TimePickerDialog(context, time, hour, minute, false).show();
            }
        };
        // Add dialog trigger to set button
        buttonDueSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Default dialog to previously set time, or current time if not set
                mCalendarTemp = Calendar.getInstance();
                if (mCalendar != null) {
                    mCalendarTemp.setTime(mCalendar.getTime());
                }
                // Create date dialog
                int year = mCalendarTemp.get(Calendar.YEAR);
                int month = mCalendarTemp.get(Calendar.MONTH);
                int day = mCalendarTemp.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(context, date, year, month, day).show();
            }
        });
        // Add clear trigger to clear button
        buttonDueClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset calendar and clear field
                mCalendar = null;
                mTime = false;
                editField.setText("");
                buttonDueClear.setEnabled(false);
            }
        });
        buttonDueClear.setEnabled(false);
    }

    // Shorthand for adding leading zeros
    public String pad(int num) {
        return num < 10 ? "0" + num : String.valueOf(num);
    }

    // Return the selected date
    public Calendar getCal() {
        return mCalendar;
    }
    // Return the selected date
    public boolean hasTime() {
        return mTime;
    }

}
