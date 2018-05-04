package org.wade.mbus.site.common.base64;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64Util {
    public static byte[] decode(String data) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(data);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream("d:/temp/t.gif");
            out.write(b);
            out.flush();
            out.close();
            return b;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}  