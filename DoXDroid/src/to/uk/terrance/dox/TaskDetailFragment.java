package to.uk.terrance.dox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Task detail screen. This fragment is either
 * contained in a {@link TaskListActivity} in two-pane mode (on tablets) or a
 * {@link TaskDetailActivity} on handsets.
 */
public class TaskDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Task mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public TaskDetailFragment() {}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = TaskContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_taskdetail, container, false);
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			String[] pris = new String[]{"Low", "Medium", "High", "Critical"};
			((TextView) rootView.findViewById(R.id.td_label_pri)).setText(pris[mItem.getPri()] + " (" + mItem.getPri() + ")");
			((TextView) rootView.findViewById(R.id.td_label_due)).setText(mItem.getDue().toString());
			((TextView) rootView.findViewById(R.id.td_label_repeat)).setText(mItem.getRepeat().toString());
			((TextView) rootView.findViewById(R.id.td_label_tags)).setText(mItem.getTags().toString());
			((TextView) rootView.findViewById(R.id.td_label_desc)).setText(mItem.getDesc().toString());
		}
		return rootView;
	}
}
