package jp.team.e_works.remotecontrollerclientrv.object;

import android.support.annotation.NonNull;

import static jp.team.e_works.remotecontrollerclientrv.util.RedisConst.REDIS_KEYEVENT_CTRL;
import static jp.team.e_works.remotecontrollerclientrv.util.RedisConst.REDIS_KEYEVENT_ALT;
import static jp.team.e_works.remotecontrollerclientrv.util.RedisConst.REDIS_KEYEVENT_SHIFT;

public class ControlButton {
    private String mText;
    private int mPosition;
    private int mCommand;

    public ControlButton(@NonNull String text, int position, int command) {
        mText = text;
        mPosition = position;
        mCommand = command;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setCommand(int command) {
        mCommand = command;
    }

    public void addCommand(int command) {
        if (command != REDIS_KEYEVENT_CTRL && command != REDIS_KEYEVENT_ALT && command != REDIS_KEYEVENT_SHIFT) {
            return;
        }
        mCommand |= command;
    }

    public int getCommand() {
        return mCommand;
    }
}
