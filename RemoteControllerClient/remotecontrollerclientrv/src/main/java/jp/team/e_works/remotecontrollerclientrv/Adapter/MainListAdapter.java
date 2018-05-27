package jp.team.e_works.remotecontrollerclientrv.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import jp.team.e_works.remotecontrollerclientrv.R;
import jp.team.e_works.remotecontrollerclientrv.obj.MainListItem;

public class MainListAdapter extends ArrayAdapter<MainListItem> {
    private int mResource;
    private List<MainListItem> mItems;
    private LayoutInflater mInflater;

    public MainListAdapter(@NonNull Context context, int resource, List<MainListItem> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        MainListItem item = mItems.get(position);

        ((TextView) view.findViewById(R.id.item_name)).setText(item.getItemTitle());

        view.findViewById(R.id.item_option).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });

        return view;
    }
}
