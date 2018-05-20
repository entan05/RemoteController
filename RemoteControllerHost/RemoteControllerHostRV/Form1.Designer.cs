namespace RemoteControllerHostRV
{
    partial class Form1
    {
        /// <summary>
        /// 必要なデザイナー変数です。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 使用中のリソースをすべてクリーンアップします。
        /// </summary>
        /// <param name="disposing">マネージ リソースを破棄する場合は true を指定し、その他の場合は false を指定します。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows フォーム デザイナーで生成されたコード

        /// <summary>
        /// デザイナー サポートに必要なメソッドです。このメソッドの内容を
        /// コード エディターで変更しないでください。
        /// </summary>
        private void InitializeComponent()
        {
            this.IpLabel = new System.Windows.Forms.Label();
            this.IpBox = new System.Windows.Forms.TextBox();
            this.PortLabel = new System.Windows.Forms.Label();
            this.PortBox = new System.Windows.Forms.TextBox();
            this.ConnectBtn = new System.Windows.Forms.Button();
            this.ReceiveBox = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // IpLabel
            // 
            this.IpLabel.AutoSize = true;
            this.IpLabel.Location = new System.Drawing.Point(13, 13);
            this.IpLabel.Name = "IpLabel";
            this.IpLabel.Size = new System.Drawing.Size(54, 12);
            this.IpLabel.TabIndex = 0;
            this.IpLabel.Text = "Redis IP :";
            // 
            // IpBox
            // 
            this.IpBox.Location = new System.Drawing.Point(73, 10);
            this.IpBox.Name = "IpBox";
            this.IpBox.Size = new System.Drawing.Size(100, 19);
            this.IpBox.TabIndex = 1;
            // 
            // PortLabel
            // 
            this.PortLabel.AutoSize = true;
            this.PortLabel.Location = new System.Drawing.Point(179, 13);
            this.PortLabel.Name = "PortLabel";
            this.PortLabel.Size = new System.Drawing.Size(32, 12);
            this.PortLabel.TabIndex = 2;
            this.PortLabel.Text = "Port :";
            // 
            // PortBox
            // 
            this.PortBox.Location = new System.Drawing.Point(217, 10);
            this.PortBox.Name = "PortBox";
            this.PortBox.Size = new System.Drawing.Size(100, 19);
            this.PortBox.TabIndex = 3;
            // 
            // ConnectBtn
            // 
            this.ConnectBtn.Location = new System.Drawing.Point(323, 8);
            this.ConnectBtn.Name = "ConnectBtn";
            this.ConnectBtn.Size = new System.Drawing.Size(75, 23);
            this.ConnectBtn.TabIndex = 4;
            this.ConnectBtn.Text = "Connect";
            this.ConnectBtn.UseVisualStyleBackColor = true;
            this.ConnectBtn.Click += new System.EventHandler(this.ConnectBtn_Click);
            // 
            // ReceiveBox
            // 
            this.ReceiveBox.Location = new System.Drawing.Point(13, 50);
            this.ReceiveBox.Multiline = true;
            this.ReceiveBox.Name = "ReceiveBox";
            this.ReceiveBox.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.ReceiveBox.Size = new System.Drawing.Size(385, 388);
            this.ReceiveBox.TabIndex = 5;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.ReceiveBox);
            this.Controls.Add(this.ConnectBtn);
            this.Controls.Add(this.PortBox);
            this.Controls.Add(this.PortLabel);
            this.Controls.Add(this.IpBox);
            this.Controls.Add(this.IpLabel);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        delegate void UpdateReceiveBoxFunc(string channel, string message);

        private System.Windows.Forms.Label IpLabel;
        private System.Windows.Forms.TextBox IpBox;
        private System.Windows.Forms.Label PortLabel;
        private System.Windows.Forms.TextBox PortBox;
        private System.Windows.Forms.Button ConnectBtn;
        private System.Windows.Forms.TextBox ReceiveBox;

        private bool m_IsConnect = false;

        private StackExchange.Redis.ISubscriber m_Subscriber = null;
    }
}

