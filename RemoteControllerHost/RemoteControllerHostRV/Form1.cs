using System;
using System.Collections;
using System.Runtime.InteropServices;
using System.Threading;
using System.Windows.Forms;

using StackExchange.Redis;

namespace RemoteControllerHostRV
{
    public partial class Form1 : Form
    {
        [DllImport("user32.dll")]
        public static extern uint keybd_event(byte bVk, byte bScan, uint dwFlags, UIntPtr dwExtraInfo);
        [DllImport("user32.dll")]
        public static extern void mouse_event(int dwFlags, int dx, int dy, int cButtons, int dwExtraInfo);

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            // disconnect処理
            if (null != m_Subscriber)
            {
                m_Subscriber.UnsubscribeAll();
                m_Subscriber = null;
            }
        }

        private void useLocalhostCheckBox_CheckStateChanged(object sender, EventArgs e)
        {
            if (useLocalhostCheckBox.Checked)
            {
                IpBox.Enabled = false;
            }
            else
            {
                IpBox.Enabled = true;
            }
        }

        private void ConnectBtn_Click(object sender, EventArgs e)
        {
            if (m_IsConnect)
            {
                // disconnect処理
                if (null != m_Subscriber)
                {
                    m_Subscriber.UnsubscribeAll();
                    m_Subscriber = null;
                }
                IpBox.Enabled = true;
                PortBox.Enabled = true;
                ConnectBtn.Text = "Connect";
                m_IsConnect = false;
            }
            else
            {
                string ip = useLocalhostCheckBox.Checked ? "localhost" : IpBox.Text;
                // connect処理
                if (string.IsNullOrEmpty(ip) || string.IsNullOrEmpty(PortBox.Text))
                {
                    return;
                }

                try
                {
                    ConnectionMultiplexer redis = ConnectionMultiplexer.Connect(ip + ":" + PortBox.Text);
                    m_Subscriber = redis.GetSubscriber();
                    m_Subscriber.Subscribe("*", (channel, message) =>
                    {
                        Invoke(new UpdateReceiveBoxFunc(UpdateReceiveBox), channel.ToString(), message.ToString());
                        Invoke(new ReceiveMessage2EventFunc(ReceiveMessage2Event), channel.ToString(), message.ToString());
                    });

                    IpBox.Enabled = false;
                    PortBox.Enabled = false;
                    ConnectBtn.Text = "Disconnect";
                    m_IsConnect = true;
                }
                catch (Exception)
                {

                }
            }
        }

        /// <summary>
        /// 表示イベントログを更新する
        /// </summary>
        private void UpdateReceiveBox(string channel, string message)
        {
            ReceiveBox.Text += "[" + channel + "]" + message + "\r\n";
            ReceiveBox.Update();
            ReceiveBox.Select(ReceiveBox.Text.Length, 0);
            ReceiveBox.ScrollToCaret();
        }

        /// <summary>
        /// 受け取ったメッセージからイベントを発行する
        /// </summary>
        /// <param name="channel">メッセージを受け取ったchannel</param>
        /// <param name="message">メッセージ</param>
        private void ReceiveMessage2Event(string channel, string message)
        {
            if (RedisConst.CHANNEL_KEY_EVENT == channel)
            {
                CheckKeyEventCode(int.Parse(message));
            }
            else if (RedisConst.CHANNEL_MOUSE_EVENT == channel)
            {
                SendMouseEvent(int.Parse(message));
            }
        }

        /// <summary>
        /// キーイベントコードを解析し、イベントを発行する
        /// </summary>
        /// <param name="keyEventCode">Redisから受け取ったキーイベントコード</param>
        private void CheckKeyEventCode(int keyEventCode)
        {
            ArrayList keys = new ArrayList();

            if ((keyEventCode & (int)RedisKeyValues.VALUE_KEY_CTRL) != 0)
            {
                keys.Add(KeyCode.KEYCODE_CTRL);
                keyEventCode = keyEventCode & (~(int)RedisKeyValues.VALUE_KEY_CTRL);
            }

            if ((keyEventCode & (int)RedisKeyValues.VALUE_KEY_ALT) != 0)
            {
                keys.Add(KeyCode.KEYCODE_ALT);
                keyEventCode = keyEventCode & (~(int)RedisKeyValues.VALUE_KEY_ALT);
            }

            if ((keyEventCode & (int)RedisKeyValues.VALUE_KEY_SHIFT) != 0)
            {
                keys.Add(KeyCode.KEYCODE_SHIFT);
                keyEventCode = keyEventCode & (~(int)RedisKeyValues.VALUE_KEY_SHIFT);
            }

            keys.Add(((RedisKeyValues)keyEventCode).GetKeyCode());

            SendKeyEvents(keys);
        }

        /// <summary>
        /// 複数のキーイベントを発行する
        /// </summary>
        /// <param name="keys">発行するキーイベントのリスト</param>
        private void SendKeyEvents(ArrayList keys)
        {
            for (int i = 0; i < keys.Count; i++)
            {
                keybd_event((byte)keys[i], 0, 0, (UIntPtr)0);
                Thread.Sleep(50);
            }
            keys.Reverse();
            for (int i = 0; i < keys.Count; i++)
            {
                keybd_event((byte)keys[i], 0, 2, (UIntPtr)0);
            }
        }

        /// <summary>
        /// マウスイベントを送信する
        /// </summary>
        /// <param name="eventCode">Redisで送信されたイベントコード</param>
        private void SendMouseEvent(int eventCode)
        {
            switch (eventCode)
            {
                case RedisConst.VALUE_MOUSE_LEFT_CLICK:
                    mouse_event(MouseEventCode.MOUSE_EVENT_LEFT_DOWN, 0, 0, 0, 0);
                    mouse_event(MouseEventCode.MOUSE_EVENT_LEFT_UP, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_LEFT_DOWN:
                    mouse_event(MouseEventCode.MOUSE_EVENT_LEFT_DOWN, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_LEFT_UP:
                    mouse_event(MouseEventCode.MOUSE_EVENT_LEFT_UP, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_RIGHT_CLICK:
                    mouse_event(MouseEventCode.MOUSE_EVENT_RIGHT_DOWN, 0, 0, 0, 0);
                    mouse_event(MouseEventCode.MOUSE_EVENT_RIGHT_UP, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_RIGHT_DOWN:
                    mouse_event(MouseEventCode.MOUSE_EVENT_RIGHT_DOWN, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_RIGHT_UP:
                    mouse_event(MouseEventCode.MOUSE_EVENT_RIGHT_UP, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_MIDDLE_CLICK:
                    mouse_event(MouseEventCode.MOUSE_EVENT_MIDDLE_DOWN, 0, 0, 0, 0);
                    mouse_event(MouseEventCode.MOUSE_EVENT_MIDDLE_UP, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_MIDDLE_DOWN:
                    mouse_event(MouseEventCode.MOUSE_EVENT_MIDDLE_DOWN, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_MIDDLE_UP:
                    mouse_event(MouseEventCode.MOUSE_EVENT_MIDDLE_UP, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_WHEEL_BACKWARD:
                    mouse_event(MouseEventCode.MOUSE_EVENT_WHEEL, 0, 0, MouseEventCode.MOUSE_DATA_WHEEL_BACKWARD, 0);
                    break;

                case RedisConst.VALUE_MOUSE_WHEEL_AHEAD:
                    mouse_event(MouseEventCode.MOUSE_EVENT_WHEEL, 0, 0, MouseEventCode.MOUSE_DATA_WHEEL_AHEAD, 0);
                    break;

                default:
                    break;
            }
        }
    }
}
