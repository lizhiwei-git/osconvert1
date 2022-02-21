//package com.out.convert;
//
//import org.apache.commons.lang3.StringUtils;
//
//import java.io.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class DaysConvert {
//
//    public static String getRetValue(String month,int daynum){
//        List<Integer> list = new ArrayList<Integer>();
//        StringBuilder sb = new StringBuilder();
//        List<String> result = new ArrayList<>();
//        int m = daynum;
//        while (m / 2 != 0) {
//            list.add(m % 2);
//            m = m / 2;
//        }
//        list.add(m % 2);
//        for (int i = list.size() - 1; i >= 0; i--) {
//            sb.append(list.get(i));
//        }
//
//        for(int i= 0;i<list.size();i++){
//            if (list.get(i)==1){
//                int tp = 31-i;
//                if(String.valueOf(tp).length()==1){//长度为1
//                    result.add(month.concat("-0").concat(String.valueOf(tp)));
//                }else{
//                    result.add(month.concat("-").concat(String.valueOf(tp)));
//                }
//
//            }
//        }
//        return StringUtils.join(result.toArray(), "|");
//    }
//
//    public static String getRetValue(String curmonth,String lastmonth,int curday,int daynum){
//        List<Integer> list = new ArrayList<Integer>();
//        StringBuilder sb = new StringBuilder();
//        List<String> result = new ArrayList<>();
//        int m = daynum;
//        while (m / 2 != 0) {
//            list.add(m % 2);
//            m = m / 2;
//        }
//        list.add(m % 2);
//        for (int i = list.size() - 1; i >= 0; i--) {
//            sb.append(list.get(i));
//        }
//
//        for(int i= 0;i<list.size();i++){
//            if (list.get(i)==1){
//                int tp = 31-i;
//                if(String.valueOf(tp).length()==1){//长度为1
//                    if(tp<=curday){//当月
//                        result.add(curmonth.concat("-0").concat(String.valueOf(tp)));
//                    }else{//上月
//                        result.add(lastmonth.concat("-0").concat(String.valueOf(tp)));
//                    }
//                }else{
//                    if(tp<=curday){//当月
//                        result.add(curmonth.concat("-").concat(String.valueOf(tp)));
//                    }else{//上月
//                        result.add(lastmonth.concat("-").concat(String.valueOf(tp)));
//                    }
//                }
//            }
//        }
//        return StringUtils.join(result.toArray(), "|");
//    }
//
//    public static String getTimeDay(Date tdate,int index){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(tdate);
//        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//        calendar.add(Calendar.DAY_OF_MONTH,index);
//        String date = fmt.format(calendar.getTime());
//        return date;
//    }
//
//    public static void main(String[] args) {
//
//
//
////        String s = "2020-05-22";
////        System.out.println( s.substring(0,8));
////		Map map = getValue("msisdn","282cda87a736d23a48cc56286e938a06");
////		System.out.println(map.get("yh"));
////        String t = getRetValue("2020-05-22",86016);
////        System.out.println(t);
//
//        if(args.length<2){
//            System.out.println("参数必须大于2[输入文件全路径、输出文件全路径、日期]");
//            return;
//        }
//
//        //========================================================================================
//        //1、当前日期[周日]减4为结束日期，结束日期减6为开始日期。
//        //2、取当前日期的月份、开始日期、结束日期的月份。如果存在二个月份，1号至当前日期的日为本月，否则为上月；一个月份，直接为本月。
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//
//        try {
//            if(args.length==3){
////                date = sdf.parse("2020-08-09");
//                date = sdf.parse(args[2].trim());
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        String curday = getTimeDay(date,0);
//        String begday = getTimeDay(date,-4);
//        String endday = getTimeDay(date,-10);
//        ArrayList<String> list = new ArrayList<>();
//        list.add(curday.substring(0,7));
//        list.add(begday.substring(0,7));
//        list.add(endday.substring(0,7));
//        //.sorted(Comparator.reverseOrder())
//        List<String> newlist = list.stream().distinct().sorted().collect(Collectors.toList());
//
//        String curmonth = "";
//        String lastmonth = "";
//        int day = 0;
//        if(newlist.size()==1){
//            curmonth=newlist.get(0);//当月
//        }else{
//            lastmonth=newlist.get(0);//上月
//            curmonth=newlist.get(1);//当月
//            day = Integer.parseInt(curday.substring(8));
//        }
//        //========================================================================================
//
////        String inFile = "C:\\Users\\xumin\\Desktop\\test_begin.csv";
////        String outFile = "C:\\Users\\xumin\\Desktop\\test_begin111.csv";
//
//        String inFile = args[0].trim();
//        String outFile = args[1].trim();
//        int colnum=3;
//        File rfile = new File(inFile);
//        File wfile = new File(outFile);
//        BufferedReader bufferedReader = null;
//        BufferedWriter bufferedWriter = null;
//        try {
//            bufferedReader = new BufferedReader(new FileReader(rfile));
//            bufferedWriter = new BufferedWriter(new FileWriter(wfile));
//            String tempString = null;
//            // 一次读入一行，直到读入null为文件结束
//            while ((tempString = bufferedReader.readLine()) != null) {
//                String[] arr = tempString.split(",");
////                System.out.println(tempString+"============"+arr[colnum]);
//                if("".equals(lastmonth)){//前月
//                    bufferedWriter.write(tempString.replace(",".concat(arr[colnum]).concat(","),",".concat(getRetValue(curmonth,Integer.parseInt(arr[colnum]))).concat(",")));
//                }else{
//                    //前月、上月
//                    bufferedWriter.write(tempString.replace(",".concat(arr[colnum]).concat(","),",".concat(getRetValue(curmonth,lastmonth,day,Integer.parseInt(arr[colnum]))).concat(",")));
//
//                }
//                bufferedWriter.newLine();
//            }
//            bufferedReader.close();
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException e1) {
//                }
//            }
//            if (bufferedWriter != null) {
//                try {
//                    bufferedWriter.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//    }
//}
