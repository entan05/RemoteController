using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
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

        private void ConnectBtn_Click(object sender, EventArgs e)
        {
            if (m_IsConnect)
            {
                // disconnect処理
                if (null != m_Subscriber)
                {
                    m_Subscriber.UnsubscribeAll();
                }
                IpBox.Enabled = true;
                PortBox.Enabled = true;
                ConnectBtn.Text = "Connect";
                m_IsConnect = false;
            }
            else
            {
                // connect処理
                if (string.IsNullOrEmpty(IpBox.Text) || string.IsNullOrEmpty(PortBox.Text))
                {
                    return;
                }
                ConnectionMultiplexer redis = ConnectionMultiplexer.Connect(IpBox.Text + ":" + PortBox.Text);
                m_Subscriber = redis.GetSubscriber();
                m_Subscriber.Subscribe("*", (channel, message) =>
                {
                    Invoke(new UpdateReceiveBoxFunc(UpdateReceiveBox), channel.ToString(), message.ToString());
                });

                IpBox.Enabled = false;
                PortBox.Enabled = false;
                ConnectBtn.Text = "Disconnect";
                m_IsConnect = true;
            }
        }

        private void UpdateReceiveBox(string channel, string message)
        {
            ReceiveBox.Text += "[" + channel + "]" + message + "\r\n";
            ReceiveBox.Update();
            ReceiveBox.Select(ReceiveBox.Text.Length, 0);
            ReceiveBox.ScrollToCaret();

            if(RedisConst.CHANNEL_KEY_EVENT == channel)
            {
                CheckKeyEventCode(int.Parse(message));
            }
            else if(RedisConst.CHANNEL_MOUSE_EVENT == channel)
            {
                SendMouseEvent(int.Parse(message));
            }
        }

        private void CheckKeyEventCode(int keyEventCode)
        {
            ArrayList keys = new ArrayList();
            
            if((keyEventCode & RedisConst.VALUE_KEY_CTRL) != 0)
            {
                keys.Add(KeyCode.KEYCODE_CTRL);
                keyEventCode = keyEventCode & (~RedisConst.VALUE_KEY_CTRL);
            }
            
            if((keyEventCode & RedisConst.VALUE_KEY_ALT) != 0)
            {
                keys.Add(KeyCode.KEYCODE_ALT);
                keyEventCode = keyEventCode & (~RedisConst.VALUE_KEY_ALT);
            }
            
            switch (keyEventCode)
            {
                case RedisConst.VALUE_KEY_ENTER:
                    keys.Add(KeyCode.KEYCODE_ENTER);
                    break;

                case RedisConst.VALUE_KEY_F4:
                    keys.Add(KeyCode.KEYCODE_F4);
                    break;

                case RedisConst.VALUE_KEY_LEFTARROW:
                    keys.Add(KeyCode.KEYCODE_LEFT);
                    break;

                case RedisConst.VALUE_KEY_UPARROW:
                    keys.Add(KeyCode.KEYCODE_UP);
                    break;

                case RedisConst.VALUE_KEY_RIGHTARROW:
                    keys.Add(KeyCode.KEYCODE_RIGHT);
                    break;

                case RedisConst.VALUE_KEY_DOWNARROW:
                    keys.Add(KeyCode.KEYCODE_DOWN);
                    break;

                default:
                    return;
            }
            SendKeyEvents(keys);
        }

        private void SendKeyEvents(ArrayList keys)
        {
            for(int i = 0; i < keys.Count; i++)
            {
                keybd_event((byte)keys[i], 0, 0, (UIntPtr)0);
                Thread.Sleep(50);
            }
            keys.Reverse();
            for(int i = 0; i < keys.Count; i++)
            {
                keybd_event((byte)keys[i], 0, 2, (UIntPtr)0);
            }
        }

        // 単一のキーイベントを発行する
        private void SendSingleKeyEvent(byte keycode)
        {
            keybd_event(keycode, 0, 0, (UIntPtr)0);
            Thread.Sleep(50);
            keybd_event(keycode, 0, 2, (UIntPtr)0);
        }

        private void SendDoubleKeyEvent(byte keycode1, byte keycode2)
        {
            keybd_event(keycode1, 0, 0, (UIntPtr)0);
            Thread.Sleep(50);
            keybd_event(keycode2, 0, 0, (UIntPtr)0);
            Thread.Sleep(50);

            keybd_event(keycode2, 0, 2, (UIntPtr)0);
            keybd_event(keycode1, 0, 2, (UIntPtr)0);
        }

        private void SendMouseEvent(int eventCode)
        {
            switch(eventCode)
            {
                case RedisConst.VALUE_MOUSE_RIGHTCLICK:
                    mouse_event(MouseEventCode.MOUSE_EVENT_RIGHT_DOWN, 0, 0, 0, 0);
                    mouse_event(MouseEventCode.MOUSE_EVENT_RIGHT_UP, 0, 0, 0, 0);
                    break;

                case RedisConst.VALUE_MOUSE_WHEELAHEAD:
                    mouse_event(MouseEventCode.MOUSE_EVENT_WHEEL, 0, 0, MouseEventCode.MOUSE_DATA_WHEEL_AHEAD, 0);
                    break;

                default:
                    break;
            }
        }
    }
}
