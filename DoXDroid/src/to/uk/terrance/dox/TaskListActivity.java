package to.uk.terrance.dox;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;

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
        super.onCreateOptionsMenu(menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tl_menu_add:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
