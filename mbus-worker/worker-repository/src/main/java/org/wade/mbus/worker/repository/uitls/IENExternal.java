package org.wade.mbus.worker.repository.uitls;

import com.sun.jna.Library;
import com.sun.jna.Native;

/***
 * 英数类型验证码动态链接库
 */
public interface IENExternal extends Library {
    IENExternal Library = (IENExternal) Native.loadLibrary("en_external.dll", IENExternal.class);
    String GetImageText(String imgStr);
}
