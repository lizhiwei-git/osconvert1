//package com.out.convert;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class Test {
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
//        //1、当前日期[周日]减4为结束日期，结束日期减6为开始日期。
//        //2、取当前日期的月份、开始日期、结束日期的月份。如果存在二个月份，1号至当前日期的日为本月，否则为上月；一个月份，直接为本月。
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = sdf.parse("2005-12-15");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        String curday = getTimeDay(new Date(),0);
//        String begday = getTimeDay(new Date(),-4);
//        String endday = getTimeDay(new Date(),-20);
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
//            curmonth=newlist.get(0);//当月
//            lastmonth=newlist.get(1);//上月
//            day = Integer.parseInt(curday.substring(8));
//        }
//
//        System.out.println(curday.substring(8));
//        for(String s:newlist){
//            System.out.println(s);
//        }
//
//
//
////        System.out.println(curday);
////        System.out.println(begday);
////        System.out.println(endday);
//
//    }
//}
