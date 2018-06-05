using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RemoteControllerHostRV
{
    class RedisConst
    {
        /// <summary>
        /// キーイベントのチャンネル名
        /// </summary>
        public const string CHANNEL_KEY_EVENT = "e_works.remoteController.keyEvent";
        /// <summary>
        /// マウスイベントのチャンネル名
        /// </summary>
        public const string CHANNEL_MOUSE_EVENT = "e_works.remoteController.mouseEvent";

        public const int VALUE_MOUSE_LEFT_CLICK = 0x1000;
        public const int VALUE_MOUSE_LEFT_DOWN = 0x1100;
        public const int VALUE_MOUSE_LEFT_UP = 0x1200;
        public const int VALUE_MOUSE_RIGHT_CLICK = 0x2000;
        public const int VALUE_MOUSE_RIGHT_DOWN = 0x2100;
        public const int VALUE_MOUSE_RIGHT_UP = 0x2200;
        public const int VALUE_MOUSE_MIDDLE_CLICK = 0x4000;
        public const int VALUE_MOUSE_MIDDLE_DOWN = 0x4100;
        public const int VALUE_MOUSE_MIDDLE_UP = 0x4200;
        public const int VALUE_MOUSE_WHEEL_BACKWARD = 0x8100;
        public const int VALUE_MOUSE_WHEEL_AHEAD = 0x8200;
    }

    enum RedisKeyValues
    {
        VALUE_KEY_CTRL = 0x0100,
        VALUE_KEY_ALT = 0x0200,
        VALUE_KEY_SHIFT = 0x0400,

        VALUE_KEY_A = 0x0001,
        VALUE_KEY_B = 0x0002,
        VALUE_KEY_C = 0x0003,
        VALUE_KEY_D = 0x0004,
        VALUE_KEY_E = 0x0005,
        VALUE_KEY_F = 0x0006,
        VALUE_KEY_G = 0x0007,
        VALUE_KEY_H = 0x0008,
        VALUE_KEY_I = 0x0009,
        VALUE_KEY_J = 0x000A,
        VALUE_KEY_K = 0x000B,
        VALUE_KEY_L = 0x000C,
        VALUE_KEY_M = 0x000D,
        VALUE_KEY_N = 0x000E,
        VALUE_KEY_O = 0x000F,
        VALUE_KEY_P = 0x0010,
        VALUE_KEY_Q = 0x0011,
        VALUE_KEY_R = 0x0012,
        VALUE_KEY_S = 0x0013,
        VALUE_KEY_T = 0x0014,
        VALUE_KEY_U = 0x0015,
        VALUE_KEY_V = 0x0016,
        VALUE_KEY_W = 0x0017,
        VALUE_KEY_X = 0x0018,
        VALUE_KEY_Y = 0x0019,
        VALUE_KEY_Z = 0x001A,

        VALUE_KEY_0 = 0x001B,
        VALUE_KEY_1 = 0x001C,
        VALUE_KEY_2 = 0x001D,
        VALUE_KEY_3 = 0x001E,
        VALUE_KEY_4 = 0x001F,
        VALUE_KEY_5 = 0x0020,
        VALUE_KEY_6 = 0x0021,
        VALUE_KEY_7 = 0x0022,
        VALUE_KEY_8 = 0x0023,
        VALUE_KEY_9 = 0x0024,

        VALUE_KEY_F1 = 0x0025,
        VALUE_KEY_F2 = 0x0026,
        VALUE_KEY_F3 = 0x0027,
        VALUE_KEY_F4 = 0x0028,
        VALUE_KEY_F5 = 0x0029,
        VALUE_KEY_F6 = 0x002A,
        VALUE_KEY_F7 = 0x002B,
        VALUE_KEY_F8 = 0x002C,
        VALUE_KEY_F9 = 0x002D,
        VALUE_KEY_F10 = 0x002E,
        VALUE_KEY_F11 = 0x002F,
        VALUE_KEY_F12 = 0x0030,
        VALUE_KEY_F13 = 0x0031,
        VALUE_KEY_F14 = 0x0032,
        VALUE_KEY_F15 = 0x0033,
        VALUE_KEY_F16 = 0x0034,

        VALUE_KEY_BACK_SPACE = 0x0035,
        VALUE_KEY_TAB = 0x0036,
        VALUE_KEY_ENTER = 0x0037,
        VALUE_KEY_PAUSE = 0x0038,
        VALUE_KEY_ESC = 0x0039,
        VALUE_KEY_SPACE = 0x003A,
        VALUE_KEY_PAGE_UP = 0x003B,
        VALUE_KEY_PAGE_DOWN = 0x003C,
        VALUE_KEY_HOME = 0x003D,
        VALUE_KEY_END = 0x003E,
        VALUE_KEY_LEFTARROW = 0x003F,
        VALUE_KEY_UPARROW = 0x0040,
        VALUE_KEY_RIGHTARROW = 0x0041,
        VALUE_KEY_DOWNARROW = 0x0042,
        VALUE_KEY_INSERT = 0x0043,
        VALUE_KEY_DELETE = 0x0044,
        VALUE_KEY_WIN = 0x0045,
        VALUE_KEY_NUM_LOCK = 0x0046,
    }

    static class RedisKeyValuesExt
    {
        public static byte GetKeyCode(this RedisKeyValues redisKey)
        {
            switch (redisKey)
            {
                case RedisKeyValues.VALUE_KEY_CTRL:
                    return KeyCode.KEYCODE_CTRL;

                case RedisKeyValues.VALUE_KEY_ALT:
                    return KeyCode.KEYCODE_ALT;

                case RedisKeyValues.VALUE_KEY_SHIFT:
                    return KeyCode.KEYCODE_SHIFT;

                case RedisKeyValues.VALUE_KEY_A:
                    return KeyCode.KEYCODE_A;

                case RedisKeyValues.VALUE_KEY_B:
                    return KeyCode.KEYCODE_B;

                case RedisKeyValues.VALUE_KEY_C:
                    return KeyCode.KEYCODE_C;

                case RedisKeyValues.VALUE_KEY_D:
                    return KeyCode.KEYCODE_D;

                case RedisKeyValues.VALUE_KEY_E:
                    return KeyCode.KEYCODE_E;

                case RedisKeyValues.VALUE_KEY_F:
                    return KeyCode.KEYCODE_F;

                case RedisKeyValues.VALUE_KEY_G:
                    return KeyCode.KEYCODE_G;

                case RedisKeyValues.VALUE_KEY_H:
                    return KeyCode.KEYCODE_H;

                case RedisKeyValues.VALUE_KEY_I:
                    return KeyCode.KEYCODE_I;

                case RedisKeyValues.VALUE_KEY_J:
                    return KeyCode.KEYCODE_J;

                case RedisKeyValues.VALUE_KEY_K:
                    return KeyCode.KEYCODE_K;

                case RedisKeyValues.VALUE_KEY_L:
                    return KeyCode.KEYCODE_L;

                case RedisKeyValues.VALUE_KEY_M:
                    return KeyCode.KEYCODE_M;

                case RedisKeyValues.VALUE_KEY_N:
                    return KeyCode.KEYCODE_N;

                case RedisKeyValues.VALUE_KEY_O:
                    return KeyCode.KEYCODE_O;

                case RedisKeyValues.VALUE_KEY_P:
                    return KeyCode.KEYCODE_P;

                case RedisKeyValues.VALUE_KEY_Q:
                    return KeyCode.KEYCODE_Q;

                case RedisKeyValues.VALUE_KEY_R:
                    return KeyCode.KEYCODE_R;

                case RedisKeyValues.VALUE_KEY_S:
                    return KeyCode.KEYCODE_S;

                case RedisKeyValues.VALUE_KEY_T:
                    return KeyCode.KEYCODE_T;

                case RedisKeyValues.VALUE_KEY_U:
                    return KeyCode.KEYCODE_U;

                case RedisKeyValues.VALUE_KEY_V:
                    return KeyCode.KEYCODE_V;

                case RedisKeyValues.VALUE_KEY_W:
                    return KeyCode.KEYCODE_W;

                case RedisKeyValues.VALUE_KEY_X:
                    return KeyCode.KEYCODE_X;

                case RedisKeyValues.VALUE_KEY_Y:
                    return KeyCode.KEYCODE_Y;

                case RedisKeyValues.VALUE_KEY_Z:
                    return KeyCode.KEYCODE_Z;

                case RedisKeyValues.VALUE_KEY_0:
                    return KeyCode.KEYCODE_0;

                case RedisKeyValues.VALUE_KEY_1:
                    return KeyCode.KEYCODE_1;

                case RedisKeyValues.VALUE_KEY_2:
                    return KeyCode.KEYCODE_2;

                case RedisKeyValues.VALUE_KEY_3:
                    return KeyCode.KEYCODE_3;

                case RedisKeyValues.VALUE_KEY_4:
                    return KeyCode.KEYCODE_4;

                case RedisKeyValues.VALUE_KEY_5:
                    return KeyCode.KEYCODE_5;

                case RedisKeyValues.VALUE_KEY_6:
                    return KeyCode.KEYCODE_6;

                case RedisKeyValues.VALUE_KEY_7:
                    return KeyCode.KEYCODE_7;

                case RedisKeyValues.VALUE_KEY_8:
                    return KeyCode.KEYCODE_8;

                case RedisKeyValues.VALUE_KEY_9:
                    return KeyCode.KEYCODE_9;

                case RedisKeyValues.VALUE_KEY_F1:
                    return KeyCode.KEYCODE_F1;

                case RedisKeyValues.VALUE_KEY_F2:
                    return KeyCode.KEYCODE_F2;

                case RedisKeyValues.VALUE_KEY_F3:
                    return KeyCode.KEYCODE_F3;

                case RedisKeyValues.VALUE_KEY_F4:
                    return KeyCode.KEYCODE_F4;

                case RedisKeyValues.VALUE_KEY_F5:
                    return KeyCode.KEYCODE_F5;

                case RedisKeyValues.VALUE_KEY_F6:
                    return KeyCode.KEYCODE_F6;

                case RedisKeyValues.VALUE_KEY_F7:
                    return KeyCode.KEYCODE_F7;

                case RedisKeyValues.VALUE_KEY_F8:
                    return KeyCode.KEYCODE_F8;

                case RedisKeyValues.VALUE_KEY_F9:
                    return KeyCode.KEYCODE_F9;

                case RedisKeyValues.VALUE_KEY_F10:
                    return KeyCode.KEYCODE_F10;

                case RedisKeyValues.VALUE_KEY_F11:
                    return KeyCode.KEYCODE_F11;

                case RedisKeyValues.VALUE_KEY_F12:
                    return KeyCode.KEYCODE_F12;

                case RedisKeyValues.VALUE_KEY_F13:
                    return KeyCode.KEYCODE_F13;

                case RedisKeyValues.VALUE_KEY_F14:
                    return KeyCode.KEYCODE_F14;

                case RedisKeyValues.VALUE_KEY_F15:
                    return KeyCode.KEYCODE_F15;

                case RedisKeyValues.VALUE_KEY_F16:
                    return KeyCode.KEYCODE_F16;

                case RedisKeyValues.VALUE_KEY_BACK_SPACE:
                    return KeyCode.KEYCODE_BACK_SPACE;

                case RedisKeyValues.VALUE_KEY_TAB:
                    return KeyCode.KEYCODE_TAB;

                case RedisKeyValues.VALUE_KEY_ENTER:
                    return KeyCode.KEYCODE_ENTER;

                case RedisKeyValues.VALUE_KEY_PAUSE:
                    return KeyCode.KEYCODE_PAUSE;

                case RedisKeyValues.VALUE_KEY_ESC:
                    return KeyCode.KEYCODE_ESC;

                case RedisKeyValues.VALUE_KEY_SPACE:
                    return KeyCode.KEYCODE_SPACE;

                case RedisKeyValues.VALUE_KEY_PAGE_UP:
                    return KeyCode.KEYCODE_PG_UP;

                case RedisKeyValues.VALUE_KEY_PAGE_DOWN:
                    return KeyCode.KEYCODE_PG_DOWN;

                case RedisKeyValues.VALUE_KEY_HOME:
                    return KeyCode.KEYCODE_HOME;

                case RedisKeyValues.VALUE_KEY_END:
                    return KeyCode.KEYCODE_END;

                case RedisKeyValues.VALUE_KEY_LEFTARROW:
                    return KeyCode.KEYCODE_LEFT;

                case RedisKeyValues.VALUE_KEY_UPARROW:
                    return KeyCode.KEYCODE_UP;

                case RedisKeyValues.VALUE_KEY_RIGHTARROW:
                    return KeyCode.KEYCODE_RIGHT;

                case RedisKeyValues.VALUE_KEY_DOWNARROW:
                    return KeyCode.KEYCODE_DOWN;

                case RedisKeyValues.VALUE_KEY_INSERT:
                    return KeyCode.KEYCODE_INSERT;

                case RedisKeyValues.VALUE_KEY_DELETE:
                    return KeyCode.KEYCODE_DELETE;

                case RedisKeyValues.VALUE_KEY_WIN:
                    return KeyCode.KEYCODE_WIN;

                case RedisKeyValues.VALUE_KEY_NUM_LOCK:
                    return KeyCode.KEYCODE_NUMLOCK;

                default:
                    return 0;
            }
        }
    }
}
