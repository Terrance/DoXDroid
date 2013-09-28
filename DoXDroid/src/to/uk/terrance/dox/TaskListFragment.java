package to.uk.terrance.dox;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TaskListFragment extends ListFragment {

    // The saved instance state Bundle key representing the activated item position; only used on tablets
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    // The fragment's current callback object, which is notified of list item clicks
    private Callbacks mCallbacks = sDummyCallbacks;
    // The current activated item position; only used on tablets
    private int mActivatedPosition = ListView.INVALID_POSITION;
    // The list adapter used to manage tasks
    private TaskArrayAdapter mAdapter;
    
    // Callback interface that all activities containing this fragment must implement
    public interface Callbacks {
        // Callback for when an item has been selected
        public void onItemSelected(String id);
    }

    // Dummy implementation of the Callbacks interface that does nothing
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {}
    };

    // Mandatory constructor for the fragment manager to instantiate the fragment
    public TaskListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new TaskArrayAdapter(getActivity(), TaskContent.ITEMS);
        setListAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Restore the previously serialized activated item position
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Activities containing this fragment must implement its callbacks
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks");
        }
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // Reset the active callbacks interface to the dummy implementation
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        // Notify the active callbacks interface that an item has been selected
        mCallbacks.onItemSelected(TaskContent.ITEMS.get(position).getId());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    // Turns on activate-on-click mode, where list items will be given the 'activated' state when touched
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically give items the 'activated' state when touched
        getListView().setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }

    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }
    
    public class TaskArrayAdapter extends ArrayAdapter<Task> {

        private final Context context;
        private final List<Task> tasks;

        // Fragment text views
        private TextView labelTitle;
        private TextView labelLine1;
        private TextView labelLine2;

        public TaskArrayAdapter(Context context, List<Task> tasks) {
            super(context, R.layout.row_tasklist, tasks);
            this.context = context;
            this.tasks = tasks;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row_tasklist, parent, false);
            labelTitle = (TextView) rowView.findViewById(R.id.tlr_label_title);
            labelLine1 = (TextView) rowView.findViewById(R.id.tlr_label_line1);
            labelLine2 = (TextView) rowView.findViewById(R.id.tlr_label_line2);
            Task task = tasks.get(position);
            labelTitle.setText(task.getTitle());
            labelLine1.setText(Task.PRI_NAMES[task.getPri()]);
            labelLine1.setTextColor(Task.PRI_COLOURS[task.getPri()]);
            if (task.getDue() != null) {
                labelLine2.setText(task.getDue().toString());
                labelLine2.setTextColor(DueDate.DATE_COLOURS[task.getDue().getRelative()]);
            }
            return rowView;
        }

    }

}
