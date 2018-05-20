namespace RemoteControllerHost
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
            this.IPlabel = new System.Windows.Forms.Label();
            this.Portlabel = new System.Windows.Forms.Label();
            this.ConnectStartbutton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // IPlabel
            // 
            this.IPlabel.AutoSize = true;
            this.IPlabel.Location = new System.Drawing.Point(169, 77);
            this.IPlabel.Name = "IPlabel";
            this.IPlabel.Size = new System.Drawing.Size(31, 12);
            this.IPlabel.TabIndex = 0;
            this.IPlabel.Text = "IP : -";
            // 
            // Portlabel
            // 
            this.Portlabel.AutoSize = true;
            this.Portlabel.Location = new System.Drawing.Point(171, 109);
            this.Portlabel.Name = "Portlabel";
            this.Portlabel.Size = new System.Drawing.Size(42, 12);
            this.Portlabel.TabIndex = 1;
            this.Portlabel.Text = "Port : -";
            // 
            // ConnectStartbutton
            // 
            this.ConnectStartbutton.Location = new System.Drawing.Point(660, 95);
            this.ConnectStartbutton.Name = "ConnectStartbutton";
            this.ConnectStartbutton.Size = new System.Drawing.Size(75, 23);
            this.ConnectStartbutton.TabIndex = 2;
            this.ConnectStartbutton.Text = "start";
            this.ConnectStartbutton.UseVisualStyleBackColor = true;
            this.ConnectStartbutton.Click += new System.EventHandler(this.ConnectStartbutton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.ConnectStartbutton);
            this.Controls.Add(this.Portlabel);
            this.Controls.Add(this.IPlabel);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private bool m_isListening = false;

        private System.Windows.Forms.Label IPlabel;
        private System.Windows.Forms.Label Portlabel;
        private System.Windows.Forms.Button ConnectStartbutton;
    }
}

