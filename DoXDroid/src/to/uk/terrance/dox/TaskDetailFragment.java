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
	// Content this fragment is presenting
	private Task mItem;

	// Mandatory empty constructor for the fragment manager to instantiate the fragment
	public TaskDetailFragment() {}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment arguments
			mItem = TaskContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_taskdetail, container, false);
		if (mItem == null) {
			Toast.makeText(getActivity(), "You cannot access this activity directly.", Toast.LENGTH_SHORT).show();
			getActivity().startActivity(new Intent(getActivity(), TaskListActivity.class));
			getActivity().finish();
			return null;
		}
		((TextView) rootView.findViewById(R.id.td_label_pri)).setText(Task.PRI_NAMES[mItem.getPri()] + " (" + mItem.getPri() + ")");
		((TextView) rootView.findViewById(R.id.td_label_due)).setText(mItem.getDue().toString());
		((TextView) rootView.findViewById(R.id.td_label_repeat)).setText(mItem.getRepeat().toString());
		((TextView) rootView.findViewById(R.id.td_label_tags)).setText(mItem.getTags().toString());
		((TextView) rootView.findViewById(R.id.td_label_desc)).setText(mItem.getDesc().toString());
		return rootView;
	}

}
