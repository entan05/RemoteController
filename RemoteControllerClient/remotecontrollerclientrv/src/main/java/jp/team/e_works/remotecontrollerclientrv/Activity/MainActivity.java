package jp.team.e_works.remotecontrollerclientrv.Activity;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;

import jp.team.e_works.inifilelib.IniFileLoader;
import jp.team.e_works.remotecontrollerclientrv.Adapter.ProfileListAdapter;
import jp.team.e_works.remotecontrollerclientrv.Fragment.ControllerFragment;
import jp.team.e_works.remotecontrollerclientrv.Fragment.ProfileRegistDialogFragment;
import jp.team.e_works.remotecontrollerclientrv.Fragment.RedisSettingDialogFragment;
import jp.team.e_works.remotecontrollerclientrv.R;
import jp.team.e_works.remotecontrollerclientrv.object.ProfileListItem;
import jp.team.e_works.remotecontrollerclientrv.util.Const;
import jp.team.e_works.remotecontrollerclientrv.util.Log;
import redis.clients.jedis.Jedis;

public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCE_NAME = "RedisData";
    private static final String KEY_IP = "key_ip";
    private static final String KEY_PORT = "key_port";

    private static final int PERMISSION_REQUEST_CODE = 666;

    private String mIp = null;
    private int mPort = -1;

    private TextView mRedisStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.main_menu);

        setActionBar(toolbar);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mIp = preferences.getString(KEY_IP, "");
        mPort = preferences.getInt(KEY_PORT, -1);

        mRedisStateView = findViewById(R.id.redis_state);

        if (!TextUtils.isEmpty(mIp) && mPort > 0) {
            mRedisStateView.setText(String.format(getString(R.string.redis_state), mIp, Integer.toString(mPort)));
        } else {
            mRedisStateView.setText(String.format(getString(R.string.redis_state),
                    getString(R.string.none_ip), getString(R.string.none_port)));
        }

        mRedisStateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedisSettingDialogFragment dialog = RedisSettingDialogFragment.createInstance(mIp, mPort);
                dialog.setRedisSettingListener(mRedisSettingListener);
                dialog.show(getFragmentManager(), "RedisSettingDialog");
            }
        });

        requestAccessStoragePermission();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                ProfileRegistDialogFragment dialog = ProfileRegistDialogFragment.createInstance(null);
                dialog.setProfileRegistListener(mProfileRegistListener);
                dialog.show(getFragmentManager(), "ProfileResistDialog");
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Profile 登録画面結果を受け取り、Profile List を更新する
    private ProfileRegistDialogFragment.ProfileRegistListener mProfileRegistListener
            = new ProfileRegistDialogFragment.ProfileRegistListener() {
        @Override
        public void onResult(boolean result) {
            if (result) {
                updateMainList();
            }
        }
    };

    // Redis に publish を行う
    private ControllerFragment.ControllerListener mControllerListener = new ControllerFragment.ControllerListener() {
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
                            Log.e(e);
                        }
                    }
                }
            }).start();
        }
    };

    // Redis の IP, Port の設定結果を受け取る
    private RedisSettingDialogFragment.RedisSettingListener mRedisSettingListener
            = new RedisSettingDialogFragment.RedisSettingListener() {
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
    };

    // Storage へのパーミッション要求
    private void requestAccessStoragePermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            updateMainList();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateMainList();
            }
        }
    }

    // Profile List 更新
    private void updateMainList() {
        ArrayList<ProfileListItem> listItems;
        boolean isSkip = false;

        File iniDir = new File(Const.INI_DIR);
        if (!iniDir.exists()) {
            if (!iniDir.mkdirs()) {
                Toast.makeText(this, getString(R.string.mkdir_failed), Toast.LENGTH_LONG).show();
            }
            isSkip = true;
        }

        if (isSkip) {
            listItems = new ArrayList<>();
        } else {
            File[] iniFiles = iniDir.listFiles();
            listItems = new ArrayList<>(iniFiles.length);
            IniFileLoader loader = new IniFileLoader();
            for (File file : iniFiles) {
                if (loader.load(file.getPath())) {
                    String itemName = loader.getValue(null, Const.INI_KEY_ITEM_NAME);
                    listItems.add(new ProfileListItem(itemName != null ? itemName : "", file.getPath()));
                }
            }
        }

        ListView listView = findViewById(R.id.list_area);
        ProfileListAdapter adapter = new ProfileListAdapter(this, R.layout.list_item_layout, listItems);
        adapter.setOnProfileListUpdateRequestListener(mProfileListUpdateRequestListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mOnItemClickListener);
    }

    // Profile List Item 選択リスナ
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            ListView listView = (ListView) adapterView;
            ProfileListItem item = (ProfileListItem) listView.getItemAtPosition(position);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            ControllerFragment fragment = ControllerFragment.createInstance(item.getFilePath());
            fragment.setControllerListener(mControllerListener);
            transaction.add(R.id.fragment_area, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

    private ProfileListAdapter.OnProfileListUpdateRequestListener mProfileListUpdateRequestListener
            = new ProfileListAdapter.OnProfileListUpdateRequestListener() {
        @Override
        public void onProfileDeleteRequest(String filePath) {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
            if (!file.delete()) {
                Toast.makeText(MainActivity.this, getString(R.string.profileDelete_failed), Toast.LENGTH_LONG).show();
                return;
            }
            updateMainList();
        }

        @Override
        public void onProfileEditRequest(String filePath) {
            ProfileRegistDialogFragment dialog = ProfileRegistDialogFragment.createInstance(filePath);
            dialog.setProfileRegistListener(mProfileRegistListener);
            dialog.show(getFragmentManager(), "ProfileResistDialog");
        }
    };
}
