package org.wade.mbus.worker.repository.uitls;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface ISocketExternal extends Library {
    ISocketExternal Library = (ISocketExternal) Native.loadLibrary("tcp.dll", ISocketExternal.class);
    boolean Connect(String ip, int port);
    String Send(String content);
    boolean DisConnect();
}
