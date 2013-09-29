package to.uk.terrance.dox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class RepeatView {

    // Date picker fields
    private Repeat mRepeat;

    // Main constructor
    public RepeatView(final Context context, final TextView buttonRepeat) {
        // Initialize to no repeat
        mRepeat = null;
        // Add dialog trigger to set button
        buttonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate view first, so interaction is possible later
                final View dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_repeat, null);
                // Assign form fields to variables
                final Spinner spinPreset = (Spinner) dialogView.findViewById(R.id.rp_spin_preset);
                final TextView editDays = (TextView) dialogView.findViewById(R.id.rp_edit_days);
                final Spinner spinFrom = (Spinner) dialogView.findViewById(R.id.rp_spin_from);
                if (mRepeat != null) {
                    switch (mRepeat.getDays()) {
                        case 1:
                            spinPreset.setSelection(1);
                            break;
                        case 7:
                            spinPreset.setSelection(2);
                            break;
                        case 14:
                            spinPreset.setSelection(3);
                            break;
                        default:
                            spinPreset.setSelection(4);
                            break;
                    }
                    editDays.setText(String.valueOf(mRepeat.getDays()));
                    spinFrom.setSelection(mRepeat.isFromDue() ? 1 : 0);
                }
                // Update fields on preset spinner selection
                spinPreset.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String value = null;
                        switch (position) {
                            case 0:
                                value = "";
                                break;
                            case 1:
                                value = "1";
                                break;
                            case 2:
                                value = "7";
                                break;
                            case 3:
                                value = "14";
                                break;
                        }
                        if (value != null) {
                            editDays.setText(value);
                        }
                        editDays.setEnabled(position == 4);
                        spinFrom.setEnabled(position > 0);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        editDays.setEnabled(false);
                        editDays.setText("");
                        spinFrom.setEnabled(false);
                    }
                });
                // Build the dialog
                new AlertDialog.Builder(context)
                    .setTitle("Set Repeat")
                    .setView(dialogView)
                    .setNegativeButton("Cancel", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setNeutralButton("Clear", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mRepeat = null;
                            buttonRepeat.setText("No repeat");
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("Set", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int days = 0;
                            switch (spinPreset.getSelectedItemPosition()) {
                                case 0:
                                    days = 0;
                                    break;
                                case 1:
                                    days = 1;
                                    break;
                                case 2:
                                    days = 7;
                                    break;
                                case 3:
                                    days = 14;
                                    break;
                                case 4:
                                    try {
                                        days = Integer.valueOf(editDays.getText().toString());
                                        if (days < 0) {
                                            days = 0;
                                        }
                                    } catch (NumberFormatException e) {
                                        days = 0;
                                    }
                                    break;
                            }
                            if (days > 0) {
                                mRepeat = new Repeat(days, spinFrom.getSelectedItemPosition() == 1);
                                buttonRepeat.setText(mRepeat.toString());
                            } else {
                                mRepeat = null;
                                buttonRepeat.setText("No repeat");
                            }
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
            }
        });
    }

    // Return the selected repeat
    public Repeat getRepeat() {
        return mRepeat;
    }

}
