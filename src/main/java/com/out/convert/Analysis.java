package com.out.convert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lizhiwei
 * @date 2021/4/26  14:06
 * <p>
 * 赚金客输出解析，控制输出项目
 */
public class Analysis {
    public static void main(String[] args) throws IOException, InterruptedException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String today = formatter.format(date);
        //today = "20210128";
        System.out.println("today=" + today);
        //String basePath = "D:\\zw\\联通\\赚金客";
        String basePath = args[0];
        String inputPath = basePath+File.separator+"res_"+today+".txt";
        String resourceInputPath = basePath+File.separator+"resource.txt";
        String outputPath = basePath+File.separator+ today;
        String countPath = basePath+File.separator+"out";

        File outputdir = new File(outputPath);
        File countdir = new File(countPath);

        File inputFile = new File(inputPath);
        Boolean appHostFlag = inputFile.exists();
        if (appHostFlag) {
            System.out.println("结果文件存在！进行分组输出 ...");
            if (!outputdir.exists()) {
                outputdir.mkdir();
            }

            if (!countdir.exists()) {
                countdir.mkdir();
            }
            if (outputdir.isDirectory()) {
                File[] files = outputdir.listFiles();
                for (File file : files) {
                    file.delete();
                }

            }


            File inFile = new File(inputPath);
            String filename = inFile.getName();
            BufferedReader reader = new BufferedReader(new FileReader(new File(inputPath)));
            BufferedReader resourceReader = new BufferedReader(new FileReader(new File(resourceInputPath)));
            Map<String, Set<String>> map = new HashMap<>();
            String line;//存放readLine方法的返回值

            Set<String> typeSet = new HashSet<>();
            System.out.println("目前输出的项目如下：");
            while ((line = resourceReader.readLine()) != null) {
                typeSet.add(line);
                System.out.println(line);
            }
            //readLine方法读到末尾的返回值为null，不是-1！
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                String[] data = line.split("\t");
                //phone id  province_x  city    pv log_day  type

                if (data.length == 7) {
                    String dataStr = data[0] + "\t" + data[1] + "\t"
                            + data[2] + "\t" + data[3] + "\t"
                            + data[4] + "\t" + data[5];
                    String type = data[6];

                    if (map.containsKey(type)) {
                        map.get(type).add(dataStr);
                    } else {
                        Set<String> set = new HashSet<>();
                        set.add(dataStr);
                        map.put(type, set);
                    }
                }

            }
            BufferedWriter countWriter = new BufferedWriter(new FileWriter(new File(countPath +File.separator+ "out_" + today + ".txt")));;
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                String type = entry.getKey();
                if (typeSet.contains(type)) {
                    Set<String> data = entry.getValue();

                    countWriter.write(type + ":" + data.size() + "\n");
                    countWriter.flush();
                    BufferedWriter outWriter = new BufferedWriter(new FileWriter(new File(outputPath + File.separator + today + "_" + type + "_" + data.size() + ".txt")));
                    for (String s : data) {
                        outWriter.write(s + "\n");
                        outWriter.flush();
                    }

                    outWriter.close();
                }
            }
            if (countWriter != null) {
                countWriter.close();
            }
            resourceReader.close();
            reader.close();

        }
    }

}
