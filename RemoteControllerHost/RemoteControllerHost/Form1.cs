using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace RemoteControllerHost
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void ConnectStartbutton_Click(object sender, EventArgs e)
        {
            if(!m_isListening)
            {
                Listening();
            }
        }

        // 元ネタ
        // https://dobon.net/vb/dotnet/internet/tcpclientserver.html
        private void Listening()
        {
            m_isListening = true;

            // ListenするIPアドレス
            //string host = "localhost";
            //IPAddress ipAddress = Dns.GetHostEntry(host).AddressList[0];
            string host = "127.0.0.1";
            IPAddress ipAddress = IPAddress.Parse(host);

            // Listenするポート番号
            int port = 2001;

            // TcpListenerオブジェクトを作成する
            TcpListener tcpListener = new TcpListener(ipAddress, port);

            // IP、ポートを画面表示
            IPlabel.Text = "IP : " + ((IPEndPoint)tcpListener.LocalEndpoint).Address;
            Portlabel.Text = "Port : " + ((IPEndPoint)tcpListener.LocalEndpoint).Port;

            // Listenを開始する
            tcpListener.Start();

            // 接続要求があれば受け付ける
            TcpClient tcpClient = tcpListener.AcceptTcpClient();

            // NetworkStreamを取得
            NetworkStream networkStream = tcpClient.GetStream();

            // 読み込み、書き込みのタイムアウトは10秒に設定
            networkStream.ReadTimeout = 10000;
            networkStream.WriteTimeout = 10000;

            // クライアントから送られてきたデータを受信する
            Encoding encoding = Encoding.UTF8;
            MemoryStream memoryStream = new MemoryStream();
            byte[] resBytes = new byte[256];
            int resSize = 0;
            do
            {
                // データの一部を受信する
                resSize = networkStream.Read(resBytes, 0, resBytes.Length);
                // Readが0を返したときはクライアントが切断したと判断
                if (resSize == 0)
                {
                    break;
                }
                // 受信したデータを蓄積する
                memoryStream.Write(resBytes, 0, resSize);

                // 読み取れるデータがあるか、最後が\nでない場合は受信を続ける
            } while (networkStream.DataAvailable || resBytes[resSize - 1] != '\n');
            // 受信データを文字列に変換
            string resMsg = encoding.GetString(memoryStream.GetBuffer(), 0, (int)memoryStream.Length);
            memoryStream.Close();

            // 閉じる
            networkStream.Close();
            tcpClient.Close();

            // リスナを閉じる
            tcpListener.Stop();

            IPlabel.Text = "IP : -";
            Portlabel.Text = "Port : -";
            m_isListening = false;
        }
    }
}
