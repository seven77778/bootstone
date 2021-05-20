using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using HotelLockDll;
using System.Net;
using System.IO;
using System.Collections.Specialized;
using System.Threading;
using System.Runtime.InteropServices;

namespace httpService
{
    class Program
    {
       private static XXXkDll.YYYDll h = new XXXDll.YYYDll();
       private static HttpListener httpListener = new HttpListener();
       private static string INITIALIZEERRORSTR = "ser Lock init error";
       const int SW_HIDE = 0;
       const int SW_SHOW = 5;

        [DllImport("kernel32.dll")]
        static extern IntPtr GetConsoleWindow();

        [DllImport("user32.dll")]
        static extern bool ShowWindow(IntPtr hWnd, int nCmdShow); 
       


        static void Main(string[] args)
        {
            ShowWindow(GetConsoleWindow(), SW_HIDE);
            /*
            int ret = h.Initialize();
            Console.WriteLine("ret=" + ret);
            Console.WriteLine(read("316091"));
            Console.WriteLine(make("316091", "Li", "10301", "0100", "2019-03-26 22:34:00", "2019-03-27 15:34:00"));
            Console.ReadLine();
             * */
            StartServer();
        }

        static void StartServer()
        {
            httpListener.AuthenticationSchemes = AuthenticationSchemes.Anonymous;
            var prefixes = new List<string>() { "http://*:8082/ser/" };
            foreach (string prefix in prefixes)
            {
                httpListener.Prefixes.Add(prefix);
            }
            Console.WriteLine("Server start on *:8082/ser/");
            httpListener.Start();
            new Thread(new ThreadStart(delegate
            {
                while (true)
                {
                    HttpListenerContext context = httpListener.GetContext();
                    HttpListenerRequest request = context.Request;
                    NameValueCollection nv = request.QueryString;
                    string res = "";
                    string cmd = getValueFromNV(nv, "cmd");
                    if (null == cmd)
                    {
                        res = "http Service Start ,but unknown cmd , please invoke like this ,ReadCard : http://localhost:8082/ser?cmd=read&Stores_Number=316091 ,MakeCard : http://localhost:8082/ser?cmd=make&Stores_Number=316091&User_Name=Lish&Room_Num=10301&Control_Code=0100&Begin_Time=2019-03-26%2022:34:00&End_Time=2019-03-26%2022:34:00";
                    }
                    //http://localhost:8082/ser?cmd=read&Stores_Number=316091
                    else if (cmd.ToUpper().StartsWith("READ"))
                    {

                        if (0 == h.Initialize())
                        {
                            res = read(getValueFromNV(nv, "Stores_Number") ) ;
                        }
                        else { res = INITIALIZEERRORSTR; }
                        
                       
                    }
                    //http://localhost:8082/ser?cmd=make&Stores_Number=316091&User_Name=Lish&Room_Num=10301&Control_Code=0100&Begin_Time=2019-03-26%2022:34:00&End_Time=2019-03-26%2022:34:00
                    else if (cmd.ToUpper().StartsWith("MAKE"))
                    {
                        if (0 == h.Initialize())
                        {
                            string Stores_Number = getValueFromNV(nv, "Stores_Number");
                            string User_Name = getValueFromNV(nv, "User_Name");
                            string Room_Num = getValueFromNV(nv, "Room_Num");
                            string Control_Code = getValueFromNV(nv, "Control_Code");
                            string Begin_Time = getValueFromNV(nv, "Begin_Time");
                            string End_Time = getValueFromNV(nv, "End_Time");
                            res = make(Stores_Number, User_Name, Room_Num, Control_Code, Begin_Time, End_Time);
                        }
                        else { res = INITIALIZEERRORSTR; }
                    }
                    else
                    {
                        res = "false|unknown cmd";
                    }
                    HttpListenerResponse response = context.Response;
                    response.StatusCode = 200;
                    response.ContentType = "text/html";
                    WebHeaderCollection headers = new WebHeaderCollection();
                    headers.Set("hello", "");
                    response.Headers = headers;
                    using (StreamWriter writer = new StreamWriter(response.OutputStream))
                    {
                        writer.WriteLine(res);
                    }
                    context.Response.Close();
                }
            })).Start();
        }

        public static string getValueFromNV(NameValueCollection nv, string key)
        {
            for (int idx = 0; idx < nv.AllKeys.Length; idx++)
            {
                string keyString = nv.AllKeys[idx];
                if (keyString.Equals(key))
                {
                    string ret = nv.Get(keyString);
                    return ret;
                }
            }
            return null;
        }

        static string read(string hotelId)
        {
            return h.Read_Card(hotelId);
        }

        static string make(string Stores_Number, string User_Name, string Room_Num, string Control_Code, string Begin_Time, string End_Time)
        {
            return h.Write_Card(Stores_Number, User_Name, Room_Num, Control_Code, Begin_Time, End_Time);
        }

    }
}
