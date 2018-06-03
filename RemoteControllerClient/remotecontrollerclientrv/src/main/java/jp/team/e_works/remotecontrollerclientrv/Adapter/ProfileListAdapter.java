package jp.team.e_works.remotecontrollerclientrv.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import jp.team.e_works.remotecontrollerclientrv.Fragment.AlertDialogFragment;
import jp.team.e_works.remotecontrollerclientrv.R;
import jp.team.e_works.remotecontrollerclientrv.object.ProfileListItem;

public class ProfileListAdapter extends ArrayAdapter<ProfileListItem> {
    public interface OnProfileListUpdateRequestListener {
        void onProfileDeleteRequest(String filePath);
    }

    private Activity mActivity;
    private int mResource;
    private List<ProfileListItem> mItems;
    private LayoutInflater mInflater;

    private OnProfileListUpdateRequestListener mListener;

    public ProfileListAdapter(@NonNull Context context, int resource, List<ProfileListItem> items) {
        super(context, resource, items);

        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnProfileListUpdateRequestListener(OnProfileListUpdateRequestListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }

        final ProfileListItem item = mItems.get(position);

        ((TextView) view.findViewById(R.id.item_name)).setText(item.getItemTitle());

        view.findViewById(R.id.item_option).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mActivity == null) {
                    return;
                }
                AlertDialogFragment dialog = AlertDialogFragment.createInstance(
                        mActivity.getString(R.string.delete_alert_title),
                        mActivity.getString(R.string.delete_alert_message),
                        mActivity.getString(R.string.delete_alert_positive),
                        mActivity.getString(R.string.delete_alert_negative));
                dialog.setClickListener(new AlertDialogFragment.OnAlertDialogButtonClickListener() {
                    @Override
                    public void onPositiveClick(DialogInterface dialogInterface, int i) {
                        if (mListener != null) {
                            mListener.onProfileDeleteRequest(item.getFilePath());
                        }
                    }

                    @Override
                    public void onNegativeClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show(mActivity.getFragmentManager(), "AlertDialog");
            }
        });

        return view;
    }
}
