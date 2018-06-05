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
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.useLocalhostCheckBox = new System.Windows.Forms.CheckBox();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // IpLabel
            // 
            this.IpLabel.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.IpLabel.AutoSize = true;
            this.IpLabel.Location = new System.Drawing.Point(45, 10);
            this.IpLabel.Name = "IpLabel";
            this.IpLabel.Size = new System.Drawing.Size(54, 12);
            this.IpLabel.TabIndex = 0;
            this.IpLabel.Text = "Redis IP :";
            // 
            // IpBox
            // 
            this.IpBox.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.IpBox.Location = new System.Drawing.Point(105, 6);
            this.IpBox.Name = "IpBox";
            this.IpBox.Size = new System.Drawing.Size(96, 19);
            this.IpBox.TabIndex = 1;
            // 
            // PortLabel
            // 
            this.PortLabel.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.PortLabel.AutoSize = true;
            this.PortLabel.Location = new System.Drawing.Point(271, 10);
            this.PortLabel.Name = "PortLabel";
            this.PortLabel.Size = new System.Drawing.Size(32, 12);
            this.PortLabel.TabIndex = 2;
            this.PortLabel.Text = "Port :";
            // 
            // PortBox
            // 
            this.PortBox.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.PortBox.Location = new System.Drawing.Point(309, 6);
            this.PortBox.Name = "PortBox";
            this.PortBox.Size = new System.Drawing.Size(96, 19);
            this.PortBox.TabIndex = 3;
            // 
            // ConnectBtn
            // 
            this.ConnectBtn.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.ConnectBtn.Location = new System.Drawing.Point(411, 3);
            this.ConnectBtn.Name = "ConnectBtn";
            this.ConnectBtn.Size = new System.Drawing.Size(97, 26);
            this.ConnectBtn.TabIndex = 4;
            this.ConnectBtn.Text = "Connect";
            this.ConnectBtn.UseVisualStyleBackColor = true;
            this.ConnectBtn.Click += new System.EventHandler(this.ConnectBtn_Click);
            // 
            // ReceiveBox
            // 
            this.tableLayoutPanel1.SetColumnSpan(this.ReceiveBox, 5);
            this.ReceiveBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.ReceiveBox.Location = new System.Drawing.Point(3, 67);
            this.ReceiveBox.Multiline = true;
            this.ReceiveBox.Name = "ReceiveBox";
            this.ReceiveBox.ReadOnly = true;
            this.ReceiveBox.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.ReceiveBox.Size = new System.Drawing.Size(505, 252);
            this.ReceiveBox.TabIndex = 5;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 5;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.Controls.Add(this.ReceiveBox, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.IpLabel, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.IpBox, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.PortBox, 3, 0);
            this.tableLayoutPanel1.Controls.Add(this.ConnectBtn, 4, 0);
            this.tableLayoutPanel1.Controls.Add(this.PortLabel, 2, 0);
            this.tableLayoutPanel1.Controls.Add(this.useLocalhostCheckBox, 1, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 3;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 10F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 10F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 80F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(511, 322);
            this.tableLayoutPanel1.TabIndex = 6;
            // 
            // useLocalhostCheckBox
            // 
            this.useLocalhostCheckBox.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.useLocalhostCheckBox.AutoSize = true;
            this.useLocalhostCheckBox.Location = new System.Drawing.Point(105, 35);
            this.useLocalhostCheckBox.Name = "useLocalhostCheckBox";
            this.useLocalhostCheckBox.Size = new System.Drawing.Size(96, 26);
            this.useLocalhostCheckBox.TabIndex = 6;
            this.useLocalhostCheckBox.Text = "use Localhost";
            this.useLocalhostCheckBox.UseVisualStyleBackColor = true;
            this.useLocalhostCheckBox.CheckStateChanged += new System.EventHandler(this.useLocalhostCheckBox_CheckStateChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(511, 322);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "Form1";
            this.Text = "RemoteControllerHostRV";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        delegate void UpdateReceiveBoxFunc(string channel, string message);
        delegate void ReceiveMessage2EventFunc(string channel, string message);

        private System.Windows.Forms.Label IpLabel;
        private System.Windows.Forms.TextBox IpBox;
        private System.Windows.Forms.Label PortLabel;
        private System.Windows.Forms.TextBox PortBox;
        private System.Windows.Forms.Button ConnectBtn;
        private System.Windows.Forms.TextBox ReceiveBox;

        private bool m_IsConnect = false;

        private StackExchange.Redis.ISubscriber m_Subscriber = null;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.CheckBox useLocalhostCheckBox;
    }
}

