package com.out.convert;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
/**
 * @author lizhiwei
 * @date 2021/4/1  18:33
 */
public class SHA256Udf extends UDF{
    public Text evaluate (final Text t) throws Exception {
        if (t == null) return null;

        return new Text(encodeSHA256Hex(t.toString()));
    }

    public static String encodeSHA256Hex(String data) throws Exception {

        // 执行消息摘要
        return DigestUtils.sha256Hex(data);
    }
    public static void main(String [] args) throws Exception {
        System.out.println(new SHA256Udf().evaluate(new Text("15864650160")));
    }

}
