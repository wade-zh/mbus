using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Threading;
using System.Windows.Forms;

namespace SendMessageDesk
{
    public partial class Form1 : Form
    {
        /// <summary>
        /// 验证码网址
        /// </summary>
        const string url = "http://211.70.149.135:88/CheckCode.aspx";
        /// <summary>
        /// 识别验证码网址
        /// </summary>
        const string api = "http://139.199.211.96:8090/api/upload";

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            var image = Image.FromStream(WebRequest.Create(url).GetResponse().GetResponseStream());
            this.pictureBox1.Image = image;

            byte[] data = ImageHelper.ImageToBytes(image);

            var param = new Dictionary<object, object>
                        {
                            {"file", System.Text.Encoding.Default.GetString(data)}
                        };
            Thread t = new Thread(new ThreadStart(delegate
            {
                this.textBox1.BeginInvoke(new EventHandler(delegate
                {
                    this.textBox1.AppendText("调用接口上传图片...\r\n");
                }));

                string httpResult = HttpUtil.Post(api, null, data);
                this.textBox1.BeginInvoke(new EventHandler(delegate
                {
                    this.textBox1.AppendText(httpResult + "\r\n");
                    this.textBox1.Select(this.textBox1.TextLength, this.textBox1.TextLength);
                    this.textBox1.ScrollToCaret();
                }));
            }));
            t.IsBackground = true;
            t.Start();
        }
         
         
         
    }
}
