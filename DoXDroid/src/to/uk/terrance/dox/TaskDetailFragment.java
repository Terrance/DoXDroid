package to.uk.terrance.dox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetailFragment extends Fragment {

    // Fragment argument representing the item ID that this fragment represents
    public static final String ARG_ITEM_ID = "item_id";
    // Task object for this fragment
    private Task mTask;

    // Fragment rows
    private TableRow rowPri;
    private TableRow rowDue;
    private TableRow rowRepeat;
    private TableRow rowTags;
    // Fragment text views
    private TextView labelPri;
    private TextView labelDue;
    private TextView labelRepeat;
    private TextView labelTags;
    private TextView labelDesc;

    // Mandatory empty constructor for the fragment manager to instantiate the fragment
    public TaskDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment arguments
            mTask = TaskContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_taskdetail, container, false);
        if (mTask == null) {
            Toast.makeText(getActivity(), "You cannot access this activity directly.", Toast.LENGTH_SHORT).show();
            getActivity().startActivity(new Intent(getActivity(), TaskListActivity.class));
            getActivity().finish();
            return null;
        }
        rowPri = (TableRow) rootView.findViewById(R.id.td_row_pri);
        labelPri = (TextView) rootView.findViewById(R.id.td_label_pri);
        rowDue = (TableRow) rootView.findViewById(R.id.td_row_due);
        labelDue = (TextView) rootView.findViewById(R.id.td_label_due);
        rowRepeat = (TableRow) rootView.findViewById(R.id.td_row_repeat);
        labelRepeat = (TextView) rootView.findViewById(R.id.td_label_repeat);
        rowTags = (TableRow) rootView.findViewById(R.id.td_row_tags);
        labelTags = (TextView) rootView.findViewById(R.id.td_label_tags);
        labelDesc = (TextView) rootView.findViewById(R.id.td_label_desc);
        labelPri.setText(Task.PRI_NAMES[mTask.getPri()] + " (" + mTask.getPri() + ")");
        labelPri.setTextColor(Task.PRI_COLOURS[mTask.getPri()]);
        if (mTask.getDue() == null) {
            rowDue.setVisibility(View.GONE);
        } else {
            labelDue.setText(mTask.getDue().toString());
            labelDue.setTextColor(DueDate.DATE_COLOURS[mTask.getDue().getRelative()]);
        }
        if (mTask.getRepeat() == null) {
            rowRepeat.setVisibility(View.GONE);
        } else {
            labelRepeat.setText(mTask.getRepeat().toString());
        }
        if (mTask.getTags().size() == 0) {
            rowTags.setVisibility(View.GONE);
        } else {
            labelTags.setText(mTask.getTags().toString());
        }
        if (mTask.getDesc() != null) {
            labelDesc.setText(mTask.getDesc().toString());
        }
        return rootView;
    }

}
