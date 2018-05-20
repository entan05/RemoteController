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

        public const int VALUE_KEY_F4 = 0x0028;

        public const int VALUE_KEY_ENTER = 0x0037;
        public const int VALUE_KEY_LEFTARROW = 0x003F;
        public const int VALUE_KEY_UPARROW = 0x0040;
        public const int VALUE_KEY_RIGHTARROW = 0x0041;
        public const int VALUE_KEY_DOWNARROW = 0x0042;

        public const int VALUE_MOUSE_RIGHTCLICK = 0x2000;
        public const int VALUE_MOUSE_WHEELAHEAD = 0x8200;
    }
}
