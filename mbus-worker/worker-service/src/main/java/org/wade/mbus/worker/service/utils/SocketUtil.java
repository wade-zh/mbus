package org.wade.mbus.worker.service.utils;

import org.wade.mbus.common.json.JsonUtil;
import org.wade.mbus.worker.model.TcpResp;
import org.wade.mbus.worker.repository.uitls.ISocketExternal;
import sun.misc.BASE64Encoder;

public class SocketUtil {
    public static boolean connect(String ip, int port){
        return ISocketExternal.Library.Connect(ip, port);
    }
    public static boolean disConnect(String ip, int port){
        return ISocketExternal.Library.DisConnect();
    }
    public static TcpResp send(String content){
        String res = ISocketExternal.Library.Send(content);
        if(res.contains("Send Data Fail 5023")){
            SocketUtil.connect("127.0.0.1", 7777);
            SocketUtil.send(content);
        }
        return JsonUtil.getModel(res, TcpResp.class);
    }
}
