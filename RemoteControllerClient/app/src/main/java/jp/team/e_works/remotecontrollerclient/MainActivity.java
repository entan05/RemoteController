package jp.team.e_works.remotecontrollerclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private String mHostIp;
    private int mHostPort = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void sendMessage(String message) {
        TcpThread thread = new TcpThread();
        thread.setMessage(message);
        thread.start();
    }

    private class TcpThread extends Thread {
        private String mMessage = null;

        public void setMessage(String message) {
            mMessage = message;
        }

        @Override
        public void run() {
            if(TextUtils.isEmpty(mHostIp) || mHostPort < 0) {
                return;
            }
            if(TextUtils.isEmpty(mMessage)) {
                return;
            }
            try {
                // 通信用ソケット作成
                Socket socket = new Socket(mHostIp, mHostPort);

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                // テキストを送る
                bufferedWriter.write(mMessage);
                bufferedWriter.flush();

                // 後処理
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (Exception e) {
                // todo
            }
        }
    }
}
