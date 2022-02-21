package com.out.convert;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOperMain {

    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("参数必须为3[输入文件全路径、输出文件全路径、解密列]");
            return;
        }
        String inFile = args[0].trim();
        String outFile = args[1].trim();
        int colnum = Integer.valueOf(args[2].trim());
//        String inFile="C:\\Users\\xumin\\Desktop\\weekdata_new.csv";
//        String outFile="C:\\Users\\xumin\\Desktop\\hello.csv";
//        int colnum=2;
        initPorperties();
        try {
            copyFileByReader(inFile, outFile, colnum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件解密
     *
     * @param src    原文件
     * @param dest   目标文件
     * @param colnum 列号
     * @throws IOException
     */
    public static void copyFileByReader(String src, String dest, int colnum) throws IOException {
        FileReader reader = new FileReader(src);
        BufferedReader in = new BufferedReader(reader);
        File file = new File(dest);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(writer);
        String secretkey = SystemConfig.getInstance().getSysProerites().getProperty("secretkey");
        String line;
        String newvalue;
        while ((line = in.readLine()) != null) {
            String[] values = line.trim().split(",");
            if (values.length>1) {
                if (!values[1].equals("msisdn_normal")) {
                    newvalue = "";
                    for (int k = 0; k < values.length; k++) {
                        if (k == colnum - 1) {
                            newvalue += DesEncode.decrypt(values[k], secretkey) + ",";
                        } else {
                            newvalue += values[k] + ",";
                        }
                    }
                    out.write(newvalue.substring(0, newvalue.length() - 1) + "\n");
                    out.flush();
                }
            }else {
                newvalue = "";
                for (int k = 0; k < values.length; k++) {
                    if (k == colnum - 1) {
                        newvalue += DesEncode.decrypt(values[k], secretkey) + ",";
                    } else {
                        newvalue += values[k] + ",";
                    }
                }
                out.write(newvalue.substring(0, newvalue.length() - 1) + "\n");
                out.flush();
            }
        }
        in.close();
        reader.close();
        out.close();
        writer.close();
    }

    public static void initPorperties() {
        SystemConfig scf = SystemConfig.getInstance();
        scf.setSysProerites(new Properties());
        Properties pro = scf.getSysProerites();
        try {
//            InputStream in = new FileInputStream("D:\\code\\idea_csw_work\\cswp\\osconvert\\src\\main\\resources\\systemConfig.properties");
            InputStream in = new FileInputStream("systemConfig.properties");
//            InputStream in =  FileOperMain.class.getResourceAsStream("/systemConfig.properties");//resources下的
            pro.load(in);
            System.out.println("---systemConfig.properties load success!---");
            String name = pro.getProperty("auth");
            System.out.println("auth:" + name);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileOperMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("-----contextInitialized finished---------");
    }
}
