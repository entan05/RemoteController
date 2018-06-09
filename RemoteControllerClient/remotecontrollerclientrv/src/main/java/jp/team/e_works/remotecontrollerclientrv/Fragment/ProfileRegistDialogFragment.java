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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import jp.team.e_works.inifilelib.IniFileLoader;
import jp.team.e_works.inifilelib.IniFileWriter;
import jp.team.e_works.inifilelib.IniItem;
import jp.team.e_works.remotecontrollerclientrv.R;
import jp.team.e_works.remotecontrollerclientrv.object.ControlButton;
import jp.team.e_works.remotecontrollerclientrv.object.SelectButtonSpinnerItem;
import jp.team.e_works.remotecontrollerclientrv.util.Const;
import jp.team.e_works.remotecontrollerclientrv.util.RedisConst;

public class ProfileRegistDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String KEY_INIFILE = "key_iniFile";

    public interface ProfileRegistListener {
        void onResult(boolean result);
    }

    private boolean mIsNewProfile = true;
    private String mOldIniFilePath = null;

    private ControlButton[] mButtons = new ControlButton[9];

    private ProfileRegistListener mListener = null;

    private EditText mProfileName;
    private View mDivider;
    private TextView mButtonAreaText;
    private EditText mButtonName;
    private Spinner mButtonType;
    private CheckBox mCtrlCheck;
    private CheckBox mAltCheck;
    private CheckBox mShiftCheck;

    private int mSelectedButtonIndex = -1;

    @CheckResult
    public static ProfileRegistDialogFragment createInstance(String iniFilePath) {
        ProfileRegistDialogFragment fragment = new ProfileRegistDialogFragment();

        Bundle args = new Bundle();
        args.putString(KEY_INIFILE, iniFilePath);
        fragment.setArguments(args);

        return fragment;
    }

    // Profile 登録結果受け取りリスナ登録
    public void setProfileRegistListener(ProfileRegistListener listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mOldIniFilePath = getArguments().getString(KEY_INIFILE, null);
        String profileName = "";

        if (TextUtils.isEmpty(mOldIniFilePath)) {
            for (int i = 0; i < mButtons.length; i++) {
                mButtons[i] = new ControlButton("", i, RedisConst.REDIS_EVENT_NONE);
            }
        } else {
            IniFileLoader loader = new IniFileLoader();
            loader.load(mOldIniFilePath);
            for (int i = 0; i < mButtons.length; i++) {
                String buttonName = loader.getValue(null,
                        String.format(Locale.US, Const.INI_KEY_BUTTON_X_NAME, i));
                int buttonCommand = Integer.parseInt(loader.getValue(null,
                        String.format(Locale.US, Const.INI_KEY_BUTTON_X_COMMAND, i)));

                if (buttonCommand != RedisConst.REDIS_EVENT_NONE) {
                    mButtons[i] = new ControlButton(buttonName, i, buttonCommand);
                } else {
                    mButtons[i] = new ControlButton("", i, RedisConst.REDIS_EVENT_NONE);
                }
            }
            profileName = loader.getValue(null, Const.INI_KEY_ITEM_NAME);
            mIsNewProfile = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.profile_regist_dialog_layout, null);

        view.findViewById(R.id.btn_button01).setOnClickListener(this);
        view.findViewById(R.id.btn_button02).setOnClickListener(this);
        view.findViewById(R.id.btn_button03).setOnClickListener(this);
        view.findViewById(R.id.btn_button04).setOnClickListener(this);
        view.findViewById(R.id.btn_button05).setOnClickListener(this);
        view.findViewById(R.id.btn_button06).setOnClickListener(this);
        view.findViewById(R.id.btn_button07).setOnClickListener(this);
        view.findViewById(R.id.btn_button08).setOnClickListener(this);
        view.findViewById(R.id.btn_button09).setOnClickListener(this);

        mProfileName = view.findViewById(R.id.profile_name);
        mDivider = view.findViewById(R.id.divider);
        mButtonAreaText = view.findViewById(R.id.button_area);
        mButtonName = view.findViewById(R.id.regist_button_name);
        mButtonType = view.findViewById(R.id.regist_button_type);
        mCtrlCheck = view.findViewById(R.id.regist_check_ctrl);
        mAltCheck = view.findViewById(R.id.regist_check_alt);
        mShiftCheck = view.findViewById(R.id.regist_check_shift);

        if (!TextUtils.isEmpty(profileName)) {
            mProfileName.setText(profileName);
        }

        ArrayAdapter<SelectButtonSpinnerItem> spinnerAdapter
                = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, SelectButtonSpinnerItem.values());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mButtonType.setAdapter(spinnerAdapter);

        builder.setView(view);
        builder.setTitle(R.string.regist_dialog_title)
                .setPositiveButton(R.string.regist_dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mListener != null && saveProfile()) {
                            mListener.onResult(true);
                        }
                    }
                })
                .setNegativeButton(R.string.regist_dialog_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mListener != null) {
                            mListener.onResult(false);
                        }
                    }
                });

        return builder.create();
    }

    // Profile の File 出力
    private boolean saveProfile() {
        IniFileWriter writer = new IniFileWriter();
        writer.add(new IniItem(null, Const.INI_KEY_ITEM_NAME, mProfileName.getText().toString()));
        for (int i = 0; i < mButtons.length; i++) {
            if (mSelectedButtonIndex != i) {
                // todo ボタン名を設定していない場合の処理
                writer.add(new IniItem(null, String.format(Locale.US, Const.INI_KEY_BUTTON_X_NAME, i),
                        mButtons[i].getText()));
                writer.add(new IniItem(null, String.format(Locale.US, Const.INI_KEY_BUTTON_X_COMMAND, i),
                        Integer.toString(mButtons[i].getCommand())));
            } else {
                writer.add(new IniItem(null, String.format(Locale.US, Const.INI_KEY_BUTTON_X_NAME, i),
                        mButtonName.getText().toString()));
                writer.add(new IniItem(null, String.format(Locale.US, Const.INI_KEY_BUTTON_X_COMMAND, i),
                        Integer.toString(getCommandFromInput())));
            }
        }
        return writer.write(mIsNewProfile ? Const.CreateIniFilePath() : mOldIniFilePath);
    }

    @Override
    public void onClick(View view) {
        if (mSelectedButtonIndex == -1) {
            mDivider.setVisibility(View.VISIBLE);
            mButtonAreaText.setVisibility(View.VISIBLE);
            mButtonName.setVisibility(View.VISIBLE);
            mButtonType.setVisibility(View.VISIBLE);
            mCtrlCheck.setVisibility(View.VISIBLE);
            mAltCheck.setVisibility(View.VISIBLE);
            mShiftCheck.setVisibility(View.VISIBLE);
        } else if (0 <= mSelectedButtonIndex && mSelectedButtonIndex <= 8) {
            mButtons[mSelectedButtonIndex].setText(mButtonName.getText().toString());
            mButtons[mSelectedButtonIndex].setCommand(getCommandFromInput());
        } else {
            return;
        }

        String text;
        switch (view.getId()) {
            case R.id.btn_button01:
                text = String.format(getString(R.string.regist_buttonArea), "01");
                mSelectedButtonIndex = 0;
                break;

            case R.id.btn_button02:
                text = String.format(getString(R.string.regist_buttonArea), "02");
                mSelectedButtonIndex = 1;
                break;

            case R.id.btn_button03:
                text = String.format(getString(R.string.regist_buttonArea), "03");
                mSelectedButtonIndex = 2;
                break;

            case R.id.btn_button04:
                text = String.format(getString(R.string.regist_buttonArea), "04");
                mSelectedButtonIndex = 3;
                break;

            case R.id.btn_button05:
                text = String.format(getString(R.string.regist_buttonArea), "05");
                mSelectedButtonIndex = 4;
                break;

            case R.id.btn_button06:
                text = String.format(getString(R.string.regist_buttonArea), "06");
                mSelectedButtonIndex = 5;
                break;

            case R.id.btn_button07:
                text = String.format(getString(R.string.regist_buttonArea), "07");
                mSelectedButtonIndex = 6;
                break;

            case R.id.btn_button08:
                text = String.format(getString(R.string.regist_buttonArea), "08");
                mSelectedButtonIndex = 7;
                break;

            case R.id.btn_button09:
                text = String.format(getString(R.string.regist_buttonArea), "09");
                mSelectedButtonIndex = 8;
                break;

            default:
                return;
        }
        mButtonAreaText.setText(text);
        mButtonName.setText(mButtons[mSelectedButtonIndex].getText());
        int command = mButtons[mSelectedButtonIndex].getCommand();
        if ((command & RedisConst.REDIS_KEYEVENT_CTRL) != 0) {
            mCtrlCheck.setChecked(true);
            command = command & (~RedisConst.REDIS_KEYEVENT_CTRL);
        } else {
            mCtrlCheck.setChecked(false);
        }
        if ((command & RedisConst.REDIS_KEYEVENT_ALT) != 0) {
            mAltCheck.setChecked(true);
            command = command & (~RedisConst.REDIS_KEYEVENT_ALT);
        } else {
            mAltCheck.setChecked(false);
        }
        if ((command & RedisConst.REDIS_KEYEVENT_SHIFT) != 0) {
            mShiftCheck.setChecked(true);
            command = command & (~RedisConst.REDIS_KEYEVENT_SHIFT);
        } else {
            mShiftCheck.setChecked(false);
        }
        // spinner の状態設定
        SelectButtonSpinnerItem item = SelectButtonSpinnerItem.getItem(command);
        if (item != null) {
            mButtonType.setSelection(item.ordinal());
        } else {
            mButtonType.setSelection(0);
        }
    }

    // 現在の入力内容からコマンド数値を取得する
    private int getCommandFromInput() {
        int command = RedisConst.REDIS_EVENT_NONE;
        if (mCtrlCheck.isChecked()) {
            command |= RedisConst.REDIS_KEYEVENT_CTRL;
        }
        if (mAltCheck.isChecked()) {
            command |= RedisConst.REDIS_KEYEVENT_ALT;
        }
        if (mShiftCheck.isChecked()) {
            command |= RedisConst.REDIS_KEYEVENT_SHIFT;
        }
        // spinner からキーをとる
        SelectButtonSpinnerItem selectedButton = (SelectButtonSpinnerItem) mButtonType.getSelectedItem();
        command |= selectedButton.getRedisCommand();
        return command;
    }
}
