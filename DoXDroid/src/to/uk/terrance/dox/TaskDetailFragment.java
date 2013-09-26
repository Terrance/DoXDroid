package to.uk.terrance.dox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetailFragment extends Fragment {

	// Fragment argument representing the item ID that this fragment represents
	public static final String ARG_ITEM_ID = "item_id";
	// Task object for this fragment
	private Task mTask;

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
		labelPri = (TextView) rootView.findViewById(R.id.td_label_pri);
		labelDue = (TextView) rootView.findViewById(R.id.td_label_due);
		labelRepeat = (TextView) rootView.findViewById(R.id.td_label_repeat);
		labelTags = (TextView) rootView.findViewById(R.id.td_label_tags);
		labelDesc = (TextView) rootView.findViewById(R.id.td_label_desc);
		labelPri.setText(Task.PRI_NAMES[mTask.getPri()] + " (" + mTask.getPri() + ")");
		labelPri.setTextColor(Task.PRI_COLOURS[mTask.getPri()]);
		labelDue.setText(mTask.getDue().toString());
		labelDue.setTextColor(DueDate.DATE_COLOURS[mTask.getDue().getRelative()]);
		labelRepeat.setText(mTask.getRepeat().toString());
		labelTags.setText(mTask.getTags().toString());
		labelDesc.setText(mTask.getDesc().toString());
		return rootView;
	}

}
