package com.out.convert;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileEffectOper {

    public static void main(String args[]) {
        if(args.length!=2){
            System.out.println("参数必须为2[输入文件全路径、输出文件全路径]");
            return;
        }
        String inFile = args[0].trim();
        String outFile = args[1].trim();
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
        String secretkey = SystemConfig.getInstance().getSysProerites().getProperty("secretkey");
        // 网友推荐更加简洁的写法
        while ((line = in.readLine()) != null) {
            // 一次读入一行数据
            String[] values = line.split(",");
            newvalue="";
            for(int k=0;k<values.length;k++){
                if(k==0){
                    newvalue+=DesEncode.decrypt(values[0], secretkey)+",";
                }else{
                    newvalue+=values[k]+",";
                }
            }
            out.write(newvalue.substring(0,newvalue.length()-1)+"\n");
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
//            InputStream in = new FileInputStream("D:\\idea_csw_work\\cswp\\osconvert\\src\\main\\resources\\systemConfig.properties");
            InputStream in = new FileInputStream("systemConfig.properties");
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
