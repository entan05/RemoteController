namespace RemoteControllerHostRV
{
    class MouseEventCode
    {
        /// <summary>
        /// 左ボタン ダウン
        /// </summary>
        public const int MOUSE_EVENT_LEFT_DOWN = 0x002;
        /// <summary>
        /// 左ボタン アップ
        /// </summary>
        public const int MOUSE_EVENT_LEFT_UP = 0x004;

        /// <summary>
        /// 右ボタン ダウン
        /// </summary>
        public const int MOUSE_EVENT_RIGHT_DOWN = 0x008;
        /// <summary>
        /// 右ボタン アップ
        /// </summary>
        public const int MOUSE_EVENT_RIGHT_UP = 0x010;

        /// <summary>
        /// 中央ボタン ダウン
        /// </summary>
        public const int MOUSE_EVENT_MIDDLE_DOWN = 0x020;
        /// <summary>
        /// 中央ボタン アップ
        /// </summary>
        public const int MOUSE_EVENT_MIDDLE_UP = 0x040;

        /// <summary>
        /// ホイール回転
        /// </summary>
        public const int MOUSE_EVENT_WHEEL = 0x800;

        /// <summary>
        /// <para>ホイール回転方向 後方（ユーザー側）</para>
        /// <para>第3引数で使用する</para>
        /// </summary>
        public const int MOUSE_DATA_WHEEL_BACKWARD = -100;
        /// <summary>
        /// <para>ホイール回転方向 前方（ユーザーから離れる方向）</para>
        /// <para>第3引数で使用する</para>
        /// </summary>
        public const int MOUSE_DATA_WHEEL_AHEAD = 100;
    }
}
