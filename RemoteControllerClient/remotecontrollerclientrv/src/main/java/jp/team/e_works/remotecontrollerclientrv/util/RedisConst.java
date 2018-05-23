package jp.team.e_works.remotecontrollerclientrv.util;

public class RedisConst {
    public static final String REDIS_CHANNEL_KEYEVENT = "e_works.remoteController.keyEvent";
    public static final String REDIS_CHANNEL_MOUSEEVENT = "e_works.remoteController.mouseEvent";

    public static final int REDIS_KEYEVENT_CTRL = 0x0100;
    public static final int REDIS_KEYEVENT_ALT = 0x0200;
    public static final int REDIS_KEYEVENT_SHIFT = 0x0400;

    public static final int REDIS_KEYEVENT_ENTER = 0x0037;
}
