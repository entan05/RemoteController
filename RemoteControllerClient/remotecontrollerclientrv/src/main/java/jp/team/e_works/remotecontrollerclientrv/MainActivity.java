package jp.team.e_works.remotecontrollerclientrv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import redis.clients.jedis.Jedis;

public class MainActivity extends AppCompatActivity implements ControllerFragment.FragmentCallback {
    private static final String PREFERENCE_NAME = "RedisData";
    private static final String KEY_IP = "key_ip";
    private static final String KEY_PORT = "key_port";

    private static String mIp = null;
    private static int mPort = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mIp = preferences.getString(KEY_IP, "");
        mPort = preferences.getInt(KEY_PORT, -1);

        findViewById(R.id.redis_state).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = new RedisDialog();
                dialog.show(getFragmentManager(), "RedisDialog");
            }
        });

        // 仮
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        ControllerFragment fragment = new ControllerFragment();
        transaction.add(R.id.fragment_area, fragment);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        if (!TextUtils.isEmpty(mIp) && mPort > 0) {
            SharedPreferences.Editor editor = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
            editor.putString(KEY_IP, mIp);
            editor.putInt(KEY_PORT, mPort);
            editor.apply();
        }
        super.onDestroy();
    }

    @Override
    public void onClick2Send(final String channel, final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(mIp) && mPort > 0) {
                    try {
                        Jedis jedis = new Jedis(mIp, mPort);
                        jedis.publish(channel, message);
                        jedis.quit();
                    } catch (Exception e) {
                        // todo
                    }
                }
            }
        }).start();
    }

    public static class RedisDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.dialog_layout, null);
            if (!TextUtils.isEmpty(mIp) && mPort > 0) {
                ((EditText) view.findViewById(R.id.input_ip)).setText(mIp);
                ((EditText) view.findViewById(R.id.input_port)).setText(Integer.toString(mPort));
            }

            builder.setView(view);
            builder.setMessage("Redis")
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String oldIp = mIp;
                            int oldPort = mPort;
                            mIp = ((EditText) view.findViewById(R.id.input_ip)).getText().toString();
                            mPort = Integer.parseInt(((EditText) view.findViewById(R.id.input_port)).getText().toString());
                            if (TextUtils.isEmpty(mIp) || mPort <= 0) {
                                mIp = oldIp;
                                mPort = oldPort;
                            }
                        }
                    });
            return builder.create();
        }
    }
}
