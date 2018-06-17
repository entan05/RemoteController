package jp.team.e_works.remotecontrollerclientrv.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Locale;

import jp.team.e_works.inifilelib.IniFileLoader;
import jp.team.e_works.remotecontrollerclientrv.R;
import jp.team.e_works.remotecontrollerclientrv.object.ControlButton;
import jp.team.e_works.remotecontrollerclientrv.object.SelectButtonSpinnerItem;
import jp.team.e_works.remotecontrollerclientrv.util.Const;
import jp.team.e_works.remotecontrollerclientrv.util.RedisConst;

public class ControllerFragment extends Fragment implements View.OnClickListener {
    // ボタンクリックを受け取るリスナ
    public interface ControllerListener {
        void onClick2Send(String channel, String message);
    }

    // iniファイルパスを取得するためのキー
    private static final String KEY_FILE_PATH = "key_iniFilePath";

    // ボタン設定を格納する配列
    private ControlButton[] mButtonDetails = new ControlButton[9];
    // ユーザー設定ボタンID
    private int[] mButtonIds = {
            R.id.btn_button01,
            R.id.btn_button02,
            R.id.btn_button03,
            R.id.btn_button04,
            R.id.btn_button05,
            R.id.btn_button06,
            R.id.btn_button07,
            R.id.btn_button08,
            R.id.btn_button09
    };

    // 変更不可のボタンID
    private int[] mMainControllerButtonIds = {
            R.id.btn_enter,
            R.id.btn_rightClick,
            R.id.btn_leftArrow,
            R.id.btn_upArrow,
            R.id.btn_downArrow,
            R.id.btn_rightArrow,
            R.id.btn_wheelBackward,
            R.id.btn_wheelAhead,
    };

    // イベントを渡すリスナ
    private ControllerListener mListener = null;

    @CheckResult
    public static ControllerFragment createInstance(String iniFilePath) {
        ControllerFragment fragment = new ControllerFragment();

        Bundle args = new Bundle();
        args.putString(KEY_FILE_PATH, iniFilePath);
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * リスナ登録
     *
     * @param listener 登録するリスナ
     */
    public void setControllerListener(ControllerListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.controller_layout, container, false);

        // 設定ファイルを取得する
        String filePath = getArguments().getString(KEY_FILE_PATH, null);
        if (filePath != null) {
            // 設定ファイル解析処理
            IniFileLoader loader = new IniFileLoader();
            if (loader.load(filePath)) {
                for (int i = 0; i < mButtonDetails.length; i++) {
                    String buttonText = loader.getValue(null,
                            String.format(Locale.US, Const.INI_KEY_BUTTON_X_NAME, i));
                    int command = Integer.parseInt(loader.getValue(null,
                            String.format(Locale.US, Const.INI_KEY_BUTTON_X_COMMAND, i)));

                    mButtonDetails[i] = new ControlButton(buttonText, i, command);
                }
            }
        }

        int displayWidth = view.getContext().getResources().getDisplayMetrics().widthPixels;

        for (int id : mMainControllerButtonIds) {
            Button button = view.findViewById(id);
            button.setOnClickListener(this);
            switch (id) {
                case R.id.btn_leftArrow:
                case R.id.btn_upArrow:
                case R.id.btn_downArrow:
                case R.id.btn_rightArrow:
                    button.setWidth(displayWidth / 3);
                    break;

                default:
                    button.setWidth((displayWidth / 3) * 2);
            }
        }

        // ユーザー設定ボタンの設定
        for (int i = 0; i < mButtonIds.length; i++) {
            Button button = view.findViewById(mButtonIds[i]);
            if (mButtonDetails[i] != null) {
                if (!TextUtils.isEmpty(mButtonDetails[i].getText())) {
                    button.setText(mButtonDetails[i].getText());
                } else {
                    int command = mButtonDetails[i].getCommand();
                    StringBuilder buttonTextSb = new StringBuilder();
                    if ((command & RedisConst.REDIS_KEYEVENT_CTRL) != 0) {
                        buttonTextSb.append("Ctrl");
                        command = command & (~RedisConst.REDIS_KEYEVENT_CTRL);
                    }
                    if ((command & RedisConst.REDIS_KEYEVENT_ALT) != 0) {
                        if (buttonTextSb.length() != 0) {
                            buttonTextSb.append(" + ");
                        }
                        buttonTextSb.append("Alt");
                        command = command & (~RedisConst.REDIS_KEYEVENT_ALT);
                    }
                    if ((command & RedisConst.REDIS_KEYEVENT_SHIFT) != 0) {
                        if (buttonTextSb.length() != 0) {
                            buttonTextSb.append(" + ");
                        }
                        buttonTextSb.append("Shift");
                        command = command & (~RedisConst.REDIS_KEYEVENT_SHIFT);
                    }
                    SelectButtonSpinnerItem item = SelectButtonSpinnerItem.getItem(command);
                    if (item != null) {
                        if (buttonTextSb.length() != 0 && item.getRedisCommand() != RedisConst.REDIS_EVENT_NONE) {
                            buttonTextSb.append(" + ");
                        }
                        buttonTextSb.append(item.toString());
                    }
                    if (buttonTextSb.length() != 0) {
                        button.setText(buttonTextSb);
                    }
                }
                // コマンドが登録されていないボタンは非表示にする
                if (RedisConst.REDIS_EVENT_NONE == mButtonDetails[i].getCommand()) {
                    button.setVisibility(View.INVISIBLE);
                } else {
                    button.setOnClickListener(this);
                }
            } else {
                button.setVisibility(View.INVISIBLE);
            }
        }

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        String channel = null;
        String messgae = null;
        switch (view.getId()) {
            case R.id.btn_enter:
                channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                messgae = Integer.toString(RedisConst.REDIS_KEYEVENT_ENTER);
                break;

            case R.id.btn_rightClick:
                channel = RedisConst.REDIS_CHANNEL_MOUSEEVENT;
                messgae = Integer.toString(RedisConst.REDIS_MOUSEEVENT_RIGHT_CLICK);
                break;

            case R.id.btn_leftArrow:
                channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                messgae = Integer.toString(RedisConst.REDIS_KEYEVENT_LEFT_ARROW);
                break;

            case R.id.btn_upArrow:
                channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                messgae = Integer.toString(RedisConst.REDIS_KEYEVENT_UP_ARROW);
                break;

            case R.id.btn_downArrow:
                channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                messgae = Integer.toString(RedisConst.REDIS_KEYEVENT_DOWN_ARROW);
                break;

            case R.id.btn_rightArrow:
                channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                messgae = Integer.toString(RedisConst.REDIS_KEYEVENT_RIGHT_ARROW);
                break;

            case R.id.btn_wheelBackward:
                channel = RedisConst.REDIS_CHANNEL_MOUSEEVENT;
                messgae = Integer.toString(RedisConst.REDIS_MOUSEEVENT_WHEEL_BACKWARD);
                break;

            case R.id.btn_wheelAhead:
                channel = RedisConst.REDIS_CHANNEL_MOUSEEVENT;
                messgae = Integer.toString(RedisConst.REDIS_MOUSEEVENT_WHEEL_AHEAD);
                break;

            case R.id.btn_button01:
                if (mButtonDetails[0] != null) {
                    channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                    messgae = Integer.toString(mButtonDetails[0].getCommand());
                }
                break;

            case R.id.btn_button02:
                if (mButtonDetails[1] != null) {
                    channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                    messgae = Integer.toString(mButtonDetails[1].getCommand());
                }
                break;

            case R.id.btn_button03:
                if (mButtonDetails[2] != null) {
                    channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                    messgae = Integer.toString(mButtonDetails[2].getCommand());
                }
                break;

            case R.id.btn_button04:
                if (mButtonDetails[3] != null) {
                    channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                    messgae = Integer.toString(mButtonDetails[3].getCommand());
                }
                break;

            case R.id.btn_button05:
                if (mButtonDetails[4] != null) {
                    channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                    messgae = Integer.toString(mButtonDetails[4].getCommand());
                }
                break;

            case R.id.btn_button06:
                if (mButtonDetails[5] != null) {
                    channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                    messgae = Integer.toString(mButtonDetails[5].getCommand());
                }
                break;

            case R.id.btn_button07:
                if (mButtonDetails[6] != null) {
                    channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                    messgae = Integer.toString(mButtonDetails[6].getCommand());
                }
                break;

            case R.id.btn_button08:
                if (mButtonDetails[7] != null) {
                    channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                    messgae = Integer.toString(mButtonDetails[7].getCommand());
                }
                break;

            case R.id.btn_button09:
                if (mButtonDetails[8] != null) {
                    channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                    messgae = Integer.toString(mButtonDetails[8].getCommand());
                }
                break;

            default:
                return;
        }
        if (mListener != null && (!TextUtils.isEmpty(channel) && !TextUtils.isEmpty(messgae))) {
            mListener.onClick2Send(channel, messgae);
        }
    }
}
