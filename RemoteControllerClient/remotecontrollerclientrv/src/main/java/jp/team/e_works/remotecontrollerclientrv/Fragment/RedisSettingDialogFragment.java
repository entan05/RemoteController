package jp.team.e_works.remotecontrollerclientrv.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import jp.team.e_works.remotecontrollerclientrv.R;

public class RedisSettingDialogFragment extends DialogFragment {
    public interface RedisSettingListener {
        void onSetting(String ip, int port);
    }

    private static final String KEY_IP = "key_ip";
    private static final String KEY_PORT = "key_port";

    private RedisSettingListener mListener = null;

    @CheckResult
    public static RedisSettingDialogFragment createInstance(String oldIp, int oldPort) {
        RedisSettingDialogFragment fragment = new RedisSettingDialogFragment();

        Bundle args = new Bundle();
        args.putString(KEY_IP, oldIp);
        args.putInt(KEY_PORT, oldPort);
        fragment.setArguments(args);

        return fragment;
    }

    public void setRedisSettingListener(RedisSettingListener listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String ip = getArguments().getString(KEY_IP, null);
        final int port = getArguments().getInt(KEY_PORT, -1);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.redis_setting_dialog_layout, null);
        if (!TextUtils.isEmpty(ip) && port > 0) {
            ((EditText) view.findViewById(R.id.input_ip)).setText(ip);
            ((EditText) view.findViewById(R.id.input_port)).setText("" + port);
        }

        builder.setView(view);
        builder.setTitle(R.string.redis_setting_dialog_title)
                .setNegativeButton(R.string.redis_setting_dialog_close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String newIp = ((EditText) view.findViewById(R.id.input_ip)).getText().toString();
                        int newPort = Integer.parseInt(((EditText) view.findViewById(R.id.input_port)).getText().toString());

                        if (mListener != null) {
                            if (TextUtils.isEmpty(newIp) || newPort <= 0) {
                                mListener.onSetting(ip, port);
                            } else {
                                mListener.onSetting(newIp, newPort);
                            }
                        }
                    }
                });
        return builder.create();
    }
}
