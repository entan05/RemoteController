package jp.team.e_works.remotecontrollerclientrv.util;

public class RedisConst {
    public static final String REDIS_CHANNEL_KEYEVENT = "e_works.remoteController.keyEvent";
    public static final String REDIS_CHANNEL_MOUSEEVENT = "e_works.remoteController.mouseEvent";

    public static final int REDIS_EVENT_NONE = 0x0000;

    public static final int REDIS_KEYEVENT_CTRL = 0x0100;
    public static final int REDIS_KEYEVENT_ALT = 0x0200;
    public static final int REDIS_KEYEVENT_SHIFT = 0x0400;

    public static final int REDIS_KEYEVENT_A = 0x0001;
    public static final int REDIS_KEYEVENT_B = 0x0002;
    public static final int REDIS_KEYEVENT_C = 0x0003;
    public static final int REDIS_KEYEVENT_D = 0x0004;
    public static final int REDIS_KEYEVENT_E = 0x0005;
    public static final int REDIS_KEYEVENT_F = 0x0006;
    public static final int REDIS_KEYEVENT_G = 0x0007;
    public static final int REDIS_KEYEVENT_H = 0x0008;
    public static final int REDIS_KEYEVENT_I = 0x0009;
    public static final int REDIS_KEYEVENT_J = 0x000A;
    public static final int REDIS_KEYEVENT_K = 0x000B;
    public static final int REDIS_KEYEVENT_L = 0x000C;
    public static final int REDIS_KEYEVENT_M = 0x000D;
    public static final int REDIS_KEYEVENT_N = 0x000E;
    public static final int REDIS_KEYEVENT_O = 0x000F;
    public static final int REDIS_KEYEVENT_P = 0x0010;
    public static final int REDIS_KEYEVENT_Q = 0x0011;
    public static final int REDIS_KEYEVENT_R = 0x0012;
    public static final int REDIS_KEYEVENT_S = 0x0013;
    public static final int REDIS_KEYEVENT_T = 0x0014;
    public static final int REDIS_KEYEVENT_U = 0x0015;
    public static final int REDIS_KEYEVENT_V = 0x0016;
    public static final int REDIS_KEYEVENT_W = 0x0017;
    public static final int REDIS_KEYEVENT_X = 0x0018;
    public static final int REDIS_KEYEVENT_Y = 0x0019;
    public static final int REDIS_KEYEVENT_Z = 0x001A;

    public static final int REDIS_KEYEVENT_0 = 0x001B;
    public static final int REDIS_KEYEVENT_1 = 0x001C;
    public static final int REDIS_KEYEVENT_2 = 0x001D;
    public static final int REDIS_KEYEVENT_3 = 0x001E;
    public static final int REDIS_KEYEVENT_4 = 0x001F;
    public static final int REDIS_KEYEVENT_5 = 0x0020;
    public static final int REDIS_KEYEVENT_6 = 0x0021;
    public static final int REDIS_KEYEVENT_7 = 0x0022;
    public static final int REDIS_KEYEVENT_8 = 0x0023;
    public static final int REDIS_KEYEVENT_9 = 0x0024;

    public static final int REDIS_KEYEVENT_F1 = 0x0025;
    public static final int REDIS_KEYEVENT_F2 = 0x0026;
    public static final int REDIS_KEYEVENT_F3 = 0x0027;
    public static final int REDIS_KEYEVENT_F4 = 0x0028;
    public static final int REDIS_KEYEVENT_F5 = 0x0029;
    public static final int REDIS_KEYEVENT_F6 = 0x002A;
    public static final int REDIS_KEYEVENT_F7 = 0x002B;
    public static final int REDIS_KEYEVENT_F8 = 0x002C;
    public static final int REDIS_KEYEVENT_F9 = 0x002D;
    public static final int REDIS_KEYEVENT_F10 = 0x002E;
    public static final int REDIS_KEYEVENT_F11 = 0x002F;
    public static final int REDIS_KEYEVENT_F12 = 0x0030;
    public static final int REDIS_KEYEVENT_F13 = 0x0031;
    public static final int REDIS_KEYEVENT_F14 = 0x0032;
    public static final int REDIS_KEYEVENT_F15 = 0x0033;
    public static final int REDIS_KEYEVENT_F16 = 0x0034;

    public static final int REDIS_KEYEVENT_BACK_SPACE = 0x0035;
    public static final int REDIS_KEYEVENT_TAB = 0x0036;
    public static final int REDIS_KEYEVENT_ENTER = 0x0037;
    public static final int REDIS_KEYEVENT_PAUSE = 0x0038;
    public static final int REDIS_KEYEVENT_ESC = 0x0039;
    public static final int REDIS_KEYEVENT_SPACE = 0x003A;
    public static final int REDIS_KEYEVENT_PAGE_UP = 0x003B;
    public static final int REDIS_KEYEVENT_PAGE_DOWN = 0x003C;
    public static final int REDIS_KEYEVENT_HOME = 0x003D;
    public static final int REDIS_KEYEVENT_END = 0x003E;
    public static final int REDIS_KEYEVENT_LEFT_ARROW = 0x003F;
    public static final int REDIS_KEYEVENT_UP_ARROW = 0x0040;
    public static final int REDIS_KEYEVENT_RIGHT_ARROW = 0x0041;
    public static final int REDIS_KEYEVENT_DOWN_ARROW = 0x0042;
    public static final int REDIS_KEYEVENT_INSERT = 0x0043;
    public static final int REDIS_KEYEVENT_DELETE = 0x0044;
    public static final int REDIS_KEYEVENT_WIN = 0x0045;
    public static final int REDIS_KEYEVENT_NUMLOCK = 0x0046;

    public static final int REDIS_MOUSEEVENT_LEFT_CLICK = 0x1000;
    public static final int REDIS_MOUSEEVENT_LEFT_DOWN = 0x1100;
    public static final int REDIS_MOUSEEVENT_LEFT_UP = 0x1200;
    public static final int REDIS_MOUSEEVENT_RIGHT_CLICK = 0x2000;
    public static final int REDIS_MOUSEEVENT_RIGHT_DOWN = 0x2100;
    public static final int REDIS_MOUSEEVENT_RIGHT_UP = 0x2200;
    public static final int REDIS_MOUSEEVENT_MIDDLE_CLICK = 0x4000;
    public static final int REDIS_MOUSEEVENT_MIDDLE_DOWN = 0x4100;
    public static final int REDIS_MOUSEEVENT_MIDDLE_UP = 0x4200;
    public static final int REDIS_MOUSEEVENT_WHEEL_BACKWARD = 0x8100;
    public static final int REDIS_MOUSEEVENT_WHEEL_AHEAD = 0x8200;
}
