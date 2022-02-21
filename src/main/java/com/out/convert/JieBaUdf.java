package com.out.convert;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lizhiwei
 * @date 2021/10/20  16:15
 */
public class JieBaUdf extends UDF {

    public String evaluate(final String sentence) throws Exception {
        if (sentence == null) return null;

        JiebaSegmenter segmenter = new JiebaSegmenter();

        Path path = Paths.get(this.getClass().getResource("/add.txt").getPath().substring(1));
        WordDictionary.getInstance().loadUserDict(path);
        segmenter = new JiebaSegmenter();
        List<SegToken> tokens = segmenter.process(sentence, JiebaSegmenter.SegMode.SEARCH);
        StringBuilder sb = new StringBuilder();
        Set<String> stopWordsSet = new HashSet<>();
        Set<String> keyWordsSet = new HashSet<>();
        //loadStopWords(stopWordsSet, new File("src/main/java/com/out/convert/stopwords.txt"));
        //System.out.println(this.getClass().getResourceAsStream("/stop_words.txt"));
        loadStopWords(stopWordsSet, this.getClass().getResourceAsStream("/stop_words.txt"));
        for (SegToken token : tokens) {
            String word = token.word;
            if (!stopWordsSet.contains(word) && !word.equals(" ")) {
                keyWordsSet.add(word);
            }
        }

        for (String keyword : keyWordsSet) {
            sb.append(keyword).append("|");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        JiebaSegmenter segmenter;

        Path path = Paths.get(String.valueOf(new File("D:\\zw\\爬虫数据处理\\jieba.txt")));
        /*单词*/
//        System.out.println(segmenter.sentenceProcess("小明硕士毕业于中国科学院计算所，后在日本京都大学深造"));
//        System.out.println(segmenter.sentenceProcess("这是一个伸手不见五指的黑夜"));
        /*多词*/
//        String[] sentences =
//                new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
//                        "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
//        for (String sentence : sentences) {
//            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
//        }
        //加载自定义的词典进词库
        //WordDictionary.getInstance().loadUserDict(path);
        //重新分词
        segmenter = new JiebaSegmenter();
        //String sentence = "物流很快工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作!";
        String sentence = "0     物流很快工信处女干事每月经过下属科室都要亲口卖家描述的完全一致";
        System.out.println(segmenter.sentenceProcess(sentence));

        try {
            System.out.println(new JieBaUdf().evaluate(sentence));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStopWords(Set<String> set, InputStream in) {
        BufferedReader bufr;
        try {
            bufr = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = bufr.readLine()) != null) {
                set.add(line.trim());
            }
            try {
                bufr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStopWords(Set<String> set, File file) {
        BufferedReader bufr;
        try {
            bufr = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bufr.readLine()) != null) {
                set.add(line.trim());
            }
            try {
                bufr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
