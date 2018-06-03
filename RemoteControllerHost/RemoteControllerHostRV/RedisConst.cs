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

        public const int VALUE_KEY_CTRL = 0x0100;
        public const int VALUE_KEY_ALT = 0x0200;
        public const int VALUE_KEY_SHIFT = 0x0400;

        public const int VALUE_KEY_A = 0x0001;
        public const int VALUE_KEY_B = 0x0002;
        public const int VALUE_KEY_C = 0x0003;
        public const int VALUE_KEY_D = 0x0004;
        public const int VALUE_KEY_E = 0x0005;
        public const int VALUE_KEY_F = 0x0006;
        public const int VALUE_KEY_G = 0x0007;
        public const int VALUE_KEY_H = 0x0008;
        public const int VALUE_KEY_I = 0x0009;
        public const int VALUE_KEY_J = 0x000A;
        public const int VALUE_KEY_K = 0x000B;
        public const int VALUE_KEY_L = 0x000C;
        public const int VALUE_KEY_M = 0x000D;
        public const int VALUE_KEY_N = 0x000E;
        public const int VALUE_KEY_O = 0x000F;
        public const int VALUE_KEY_P = 0x0010;
        public const int VALUE_KEY_Q = 0x0011;
        public const int VALUE_KEY_R = 0x0012;
        public const int VALUE_KEY_S = 0x0013;
        public const int VALUE_KEY_T = 0x0014;
        public const int VALUE_KEY_U = 0x0015;
        public const int VALUE_KEY_V = 0x0016;
        public const int VALUE_KEY_W = 0x0017;
        public const int VALUE_KEY_X = 0x0018;
        public const int VALUE_KEY_Y = 0x0019;
        public const int VALUE_KEY_Z = 0x001A;

        public const int VALUE_KEY_0 = 0x001B;
        public const int VALUE_KEY_1 = 0x001C;
        public const int VALUE_KEY_2 = 0x001D;
        public const int VALUE_KEY_3 = 0x001E;
        public const int VALUE_KEY_4 = 0x001F;
        public const int VALUE_KEY_5 = 0x0020;
        public const int VALUE_KEY_6 = 0x0021;
        public const int VALUE_KEY_7 = 0x0022;
        public const int VALUE_KEY_8 = 0x0023;
        public const int VALUE_KEY_9 = 0x0024;

        public const int VALUE_KEY_F1 = 0x0025;
        public const int VALUE_KEY_F2 = 0x0026;
        public const int VALUE_KEY_F3 = 0x0027;
        public const int VALUE_KEY_F4 = 0x0028;
        public const int VALUE_KEY_F5 = 0x0029;
        public const int VALUE_KEY_F6 = 0x002A;
        public const int VALUE_KEY_F7 = 0x002B;
        public const int VALUE_KEY_F8 = 0x002C;
        public const int VALUE_KEY_F9 = 0x002D;
        public const int VALUE_KEY_F10 = 0x002E;
        public const int VALUE_KEY_F11 = 0x002F;
        public const int VALUE_KEY_F12 = 0x0030;
        public const int VALUE_KEY_F13 = 0x0031;
        public const int VALUE_KEY_F14 = 0x0032;
        public const int VALUE_KEY_F15 = 0x0033;
        public const int VALUE_KEY_F16 = 0x0034;

        public const int VALUE_KEY_BACK_SPACE = 0x0035;
        public const int VALUE_KEY_TAB = 0x0036;
        public const int VALUE_KEY_ENTER = 0x0037;
        public const int VALUE_KEY_PAUSE = 0x0038;
        public const int VALUE_KEY_ESC = 0x0039;
        public const int VALUE_KEY_SPACE = 0x003A;
        public const int VALUE_KEY_PAGE_UP = 0x003B;
        public const int VALUE_KEY_PAGE_DOWN = 0x003C;
        public const int VALUE_KEY_HOME = 0x003D;
        public const int VALUE_KEY_END = 0x003E;
        public const int VALUE_KEY_LEFTARROW = 0x003F;
        public const int VALUE_KEY_UPARROW = 0x0040;
        public const int VALUE_KEY_RIGHTARROW = 0x0041;
        public const int VALUE_KEY_DOWNARROW = 0x0042;
        public const int VALUE_KEY_INSERT = 0x0043;
        public const int VALUE_KEY_DELETE = 0x0044;
        public const int VALUE_KEY_WIN = 0x0045;
        public const int VALUE_KEY_NUM_LOCK = 0x0046;

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
}
