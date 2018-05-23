package jp.team.e_works.remotecontrollerclientrv;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.team.e_works.remotecontrollerclientrv.util.RedisConst;

public class ControllerFragment extends Fragment implements View.OnClickListener {

    public interface FragmentCallback {
        void onClick2Send(String channel, String message);
    }

    private FragmentCallback mListener = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentCallback) {
            mListener = (FragmentCallback) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.controller_layout, container, false);

        view.findViewById(R.id.btn_enter).setOnClickListener(this);
        view.findViewById(R.id.btn_rightClick).setOnClickListener(this);
        view.findViewById(R.id.btn_leftArrow).setOnClickListener(this);
        view.findViewById(R.id.btn_upArrow).setOnClickListener(this);
        view.findViewById(R.id.btn_downArrow).setOnClickListener(this);
        view.findViewById(R.id.btn_rightArrow).setOnClickListener(this);
        view.findViewById(R.id.btn_wheelBackward).setOnClickListener(this);
        view.findViewById(R.id.btn_wheelAhead).setOnClickListener(this);
        view.findViewById(R.id.btn_button01).setOnClickListener(this);
        view.findViewById(R.id.btn_button02).setOnClickListener(this);
        view.findViewById(R.id.btn_button03).setOnClickListener(this);
        view.findViewById(R.id.btn_button04).setOnClickListener(this);
        view.findViewById(R.id.btn_button05).setOnClickListener(this);
        view.findViewById(R.id.btn_button06).setOnClickListener(this);
        view.findViewById(R.id.btn_button07).setOnClickListener(this);
        view.findViewById(R.id.btn_button08).setOnClickListener(this);
        view.findViewById(R.id.btn_button09).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        String channel = "";
        String messgae = "";
        switch (view.getId()) {
            case R.id.btn_enter:
                channel = RedisConst.REDIS_CHANNEL_KEYEVENT;
                messgae = Integer.toString(RedisConst.REDIS_KEYEVENT_ENTER);
                break;

            case R.id.btn_rightClick:
                break;

            case R.id.btn_leftArrow:
                break;

            case R.id.btn_upArrow:
                break;

            case R.id.btn_downArrow:
                break;

            case R.id.btn_rightArrow:
                break;

            case R.id.btn_wheelBackward:
                break;

            case R.id.btn_wheelAhead:
                break;

            case R.id.btn_button01:
                break;

            case R.id.btn_button02:
                break;

            case R.id.btn_button03:
                break;

            case R.id.btn_button04:
                break;

            case R.id.btn_button05:
                break;

            case R.id.btn_button06:
                break;

            case R.id.btn_button07:
                break;

            case R.id.btn_button08:
                break;

            case R.id.btn_button09:
                break;

            default:
                return;
        }
        if (mListener != null && (!TextUtils.isEmpty(channel) && !TextUtils.isEmpty(messgae))) {
            mListener.onClick2Send(channel, messgae);
        }
    }
}
