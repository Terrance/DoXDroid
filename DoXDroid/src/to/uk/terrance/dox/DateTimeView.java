package to.uk.terrance.dox;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTimeView {
    
    // Date picker fields
    private Calendar calendar;
    private Calendar calendarTemp;

    // Main constructor
    public DateTimeView(final Context context, final TextView editField, final Button button) {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND | Calendar.MILLISECOND, 0);
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                int year = calendarTemp.get(Calendar.YEAR);
                int month = calendarTemp.get(Calendar.MONTH);
                int day = calendarTemp.get(Calendar.DAY_OF_MONTH);
                // Store selected values
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                // Update text field with new value
                editField.setText(pad(day) + "/" + pad(month) + "/" + year + " " + pad(hour) + ":" + pad(minute));
            }
            // Shorthand for adding leading zeros
            public String pad(int num) {
                return num < 10 ? "0" + num : String.valueOf(num);
            }
        };
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Store the values so far (will discard if time dialog is cancelled)
                calendarTemp.set(Calendar.YEAR, year);
                calendarTemp.set(Calendar.MONTH, month);
                calendarTemp.set(Calendar.DAY_OF_MONTH, day);
                // Create time dialog
                int hour = calendarTemp.get(Calendar.HOUR_OF_DAY);
                int minute = calendarTemp.get(Calendar.MINUTE);
                new TimePickerDialog(context, time, hour, minute, false).show();
            }
        };
        // Add dialog trigger to button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Default dialog to previously set time, or construction time if not set
                calendarTemp = Calendar.getInstance();
                calendarTemp.setTime(calendar.getTime());
                // Create date dialog
                int year = calendarTemp.get(Calendar.YEAR);
                int month = calendarTemp.get(Calendar.MONTH);
                int day = calendarTemp.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(context, date, year, month, day).show();
            }
        });
    }
    
    // Return the selected date
    public Calendar getCal() {
        return calendar;
    }

}
