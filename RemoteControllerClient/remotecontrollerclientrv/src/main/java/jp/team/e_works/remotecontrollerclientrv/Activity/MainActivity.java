package jp.team.e_works.remotecontrollerclientrv.Activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import jp.team.e_works.remotecontrollerclientrv.Fragment.ControllerFragment;
import jp.team.e_works.remotecontrollerclientrv.Fragment.RedisSettingDialogFragment;
import jp.team.e_works.remotecontrollerclientrv.R;
import redis.clients.jedis.Jedis;

public class MainActivity extends AppCompatActivity
        implements ControllerFragment.FragmentCallback, RedisSettingDialogFragment.RedisSettingListener {
    private static final String PREFERENCE_NAME = "RedisData";
    private static final String KEY_IP = "key_ip";
    private static final String KEY_PORT = "key_port";

    private String mIp = null;
    private int mPort = -1;

    private TextView mRedisStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mIp = preferences.getString(KEY_IP, "");
        mPort = preferences.getInt(KEY_PORT, -1);

        mRedisStateView = (TextView) findViewById(R.id.redis_state);

        if (!TextUtils.isEmpty(mIp) && mPort > 0) {
            mRedisStateView.setText(String.format(getString(R.string.redis_state), mIp, "" + mPort));
        } else {
            mRedisStateView.setText(String.format(getString(R.string.redis_state),
                    getString(R.string.none_ip), getString(R.string.none_port)));
        }

        mRedisStateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedisSettingDialogFragment dialog = RedisSettingDialogFragment.createInstance(mIp, mPort);
                dialog.setRedisSettingListener(MainActivity.this);
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

    @Override
    public void onSetting(String ip, int port) {
        mIp = ip;
        mPort = port;

        if (!TextUtils.isEmpty(mIp) && mPort > 0) {
            SharedPreferences.Editor editor = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
            editor.putString(KEY_IP, mIp);
            editor.putInt(KEY_PORT, mPort);
            editor.apply();

            mRedisStateView.setText(String.format(getString(R.string.redis_state), mIp, "" + mPort));
        } else {
            mRedisStateView.setText(String.format(getString(R.string.redis_state),
                    getString(R.string.none_ip), getString(R.string.none_port)));
        }
    }
}
