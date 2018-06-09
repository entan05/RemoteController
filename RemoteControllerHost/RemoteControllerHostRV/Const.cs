using System;

namespace RemoteControllerHostRV
{
    /// <summary>
    /// 定数クラス
    /// </summary>
    class Const
    {
        /// <summary>
        /// 設定ファイルのディレクトリ
        /// </summary>
        public static string SETTING_FILE_DIR { get; } = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData)
            + "\\e-works\\RemoteControllerHostRV";

        /// <summary>
        /// 設定ファイルのパス
        /// </summary>
        public static string SETTING_FILE_PATH { get; } = SETTING_FILE_DIR + "\\settings.xml";

        // XML 要素
        public const string XML_ROOT = "settings";
        public const string XML_ELEMENT_IP = "ip";
        public const string XML_ELEMENT_PORT = "port";
    }
}
