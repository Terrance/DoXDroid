package to.uk.terrance.dox;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TaskListActivity extends SherlockFragmentActivity implements TaskListFragment.Callbacks {

    // Whether or not the activity is in two-pane mode, i.e. running on a tablet device
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);
        if (findViewById(R.id.task_detail_container) != null) {
            // Two-pane mode when the detail container is present, only in the large-screen layouts
            mTwoPane = true;
            // In two-pane mode, list items should be given the 'activated' state when touched
            ((TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.task_list)).setActivateOnItemClick(true);
        }
        // TODO: If exposing deep links into your app, handle intents here
    }

    // Callback method from Callbacks indicating that the item with the given ID was selected
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // Show the detail view by adding or replacing the detail fragment using a fragment transaction
            Bundle arguments = new Bundle();
            arguments.putString(TaskDetailFragment.ARG_ITEM_ID, id);
            TaskDetailFragment fragment = new TaskDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.task_detail_container, fragment).commit();
        } else {
            // Start the detail activity for the selected item ID
            Intent detailIntent = new Intent(this, TaskDetailActivity.class);
            detailIntent.putExtra(TaskDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.menu_tasklist, menu);
        if (mTwoPane) {
            ((TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.task_list)).setSelection(0);
            getSupportMenuInflater().inflate(R.menu.menu_taskdetail, menu);
        }
        super.onCreateOptionsMenu(menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tl_menu_add:
                // Inflate view first, so interaction is possible later
                final View dialogView = getLayoutInflater().inflate(R.layout.dialog_addtask, null);
                // Assign form fields to variables
                final TextView editTitle = (TextView) dialogView.findViewById(R.id.at_edit_title);
                final Spinner spinPri = (Spinner) dialogView.findViewById(R.id.at_spin_pri);
                final TextView editDesc = (TextView) dialogView.findViewById(R.id.at_edit_desc);
                final TextView editDue = (TextView) dialogView.findViewById(R.id.at_edit_due);
                ImageButton buttonDueSet = (ImageButton) dialogView.findViewById(R.id.at_button_due_set);
                ImageButton buttonDueClear = (ImageButton) dialogView.findViewById(R.id.at_button_due_clear);
                final Button buttonRepeat = (Button) dialogView.findViewById(R.id.at_button_repeat);
                final TextView editTags = (TextView) dialogView.findViewById(R.id.at_edit_tags);
                // Show date picker when clicking on date buttons
                final DateTimeView dateTimeDue = new DateTimeView(this, editDue, buttonDueSet, buttonDueClear);
                // Show repeat picker when clicking on repeat button
                final RepeatView repeatPick = new RepeatView(this, buttonRepeat);
                // Build the dialog
                new AlertDialog.Builder(this)
                    .setTitle("Add Task")
                    .setView(dialogView)
                    .setNegativeButton("Cancel", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Add", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Task task = new Task();
                            task.setTitle(editTitle.getText().toString());
                            task.setPri(spinPri.getSelectedItemPosition());
                            if (editDesc.getText().length() > 0) {
                                task.setDesc(editDesc.getText().toString());
                            }
                            if (dateTimeDue.getCal() != null) {
                                task.setDue(new DueDate(dateTimeDue.getCal(), dateTimeDue.hasTime()));
                            }
                            if (repeatPick.getRepeat() != null) {
                                task.setRepeat(repeatPick.getRepeat());
                            }
                            if (editTags.getText().length() > 0) {
                                task.parseTags(editTags.getText().toString());
                            }
                            task.setId(Task.newID());
                            TaskContent.addItem(task);
                            dialog.dismiss();
                            ((TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.task_list)).refreshList();
                            Toast.makeText(TaskListActivity.this, "Added task \"" + task.getTitle() + "\".", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .create()
                    .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
