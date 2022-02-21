package com.out.convert;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileRebuyOper {
    public static void main(String args[]) {
//        if(args.length!=2){
//            System.out.println("参数必须为2[inpath=C:/CS_20190308.txt、outpath=C:/CS_20190308_new.txt]");
//            return;
//        }
//        String inFile = args[0].trim();
//        String outFile = args[1].trim();

        String inFile = "C:\\Users\\xumin\\Desktop\\weekdata_new.csv";
        String outFile = "C:\\Users\\xumin\\Desktop\\hello.csv";

        initPorperties();
        try {
            copyFileByReader(inFile,outFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFileByReader(String src, String dest) throws IOException {
        FileReader reader = new FileReader(src);
        BufferedReader in = new BufferedReader(reader);
        File file = new File(dest);
        if (!file.exists()){
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(writer);
        String line;
        String newvalue;
        // 网友推荐更加简洁的写法
        while ((line = in.readLine()) != null) {
            // 一次读入一行数据
            String[] values = line.split(",");
            String secretkey = SystemConfig.getInstance().getSysProerites().getProperty("secretkey");
            String mw = "msisdn_normal";
            if(!"msisdn_normal".equals(values[1])){
                mw = DesEncode.decrypt(values[1], secretkey);//解密
            }
            newvalue = values[0]+","+mw+","+values[2]+","+values[3]+","+values[4]+","+values[5]+"\n";
            out.write(newvalue);
            out.flush();
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
            InputStream in = new FileInputStream("D:\\code\\idea_csw_work\\cswp\\osconvert\\src\\main\\resources\\systemConfig.properties");
//            InputStream in = new FileInputStream("systemConfig.properties");
//            InputStream in = new FileInputStream(MainProc.class.getResource("systemConfig.properties").getPath());
            pro.load(in);
            System.out.println("---systemConfig.properties load success!---");
            String name = pro.getProperty("auth");
            System.out.println("auth:" + name);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRebuyOper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileRebuyOper.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("-----contextInitialized finished---------");
    }



}

