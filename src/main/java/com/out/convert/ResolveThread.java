package com.out.convert;

//import com.chuangdata.framework.encrypt.resource.AESEncrypt;
//import com.chuangdata.framework.encrypt.resource.AppActionEncrypter;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author lizhiwei
 * @date 2019/6/5  10:10
 */
public class ResolveThread implements Runnable {
    private BufferedWriter writer;
    private String line;
//    private static final AESEncrypt ENCRYPT = AESEncrypt.getInstance();
    public ResolveThread(BufferedWriter writer, String line) {
        this.writer = writer;
        this.line = line;
    }

    @Override
    public void run() {
        try {
            //writer.write(encrypt+System.getProperty("line.separator"));
            writer.write(line+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
