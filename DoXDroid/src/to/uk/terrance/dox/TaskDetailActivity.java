package to.uk.terrance.dox;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;

public class TaskDetailActivity extends SherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetail);
        // Show the Up button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Only create the fragment on first load, otherwise already rebuilt
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity using a fragment transaction
            Bundle arguments = new Bundle();
            arguments.putString(TaskDetailFragment.ARG_ITEM_ID, getIntent().getStringExtra(TaskDetailFragment.ARG_ITEM_ID));
            TaskDetailFragment fragment = new TaskDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.task_detail_container, fragment).commit();
        }
        setTitle(TaskContent.ITEM_MAP.get(getIntent().getStringExtra(TaskDetailFragment.ARG_ITEM_ID)).getTitle());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.menu_taskdetail, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, TaskListActivity.class));
                return true;
            case R.id.td_menu_done:
                return true;
            case R.id.td_menu_edit:
                return true;
            case R.id.td_menu_delete:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
