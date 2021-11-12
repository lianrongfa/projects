package cn.lianrf.antlr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义词法分析
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/10/29 5:49 下午
 */
public class WordSplit {
    public static void main(String[] args) throws Exception {
//        String str = "ab+c*d-(a+b+c*abc('wer','qwe',1,1.7,8+9))";

        InputStream inputStream = WordSplit.class.getClassLoader().getResourceAsStream("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String s = reader.readLine();
        System.out.println(s);
        Word[] words = wordSplit(s, new String[]{});
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
        inputStream.close();

    }

    private static Word[] wordSplit(String str, String[] splitWord) throws Exception {
        if (str == null){
            return new Word[0];
        }
        char c;
        int line =1;
        List<Word> list = new ArrayList<Word>();
        int i= 0;
        int point = 0;
        while(i<str.length()){
            c = str.charAt(i);
            if (c=='"' || c=='\''){//字符串处理
                int index = str.indexOf(c,i + 1);
                //处理字符串中的”问题
                while(index >0 && str.charAt(index - 1) =='\\'){
                    index = str.indexOf(c,index + 1);
                }
                if (index < 0)
                    throw new Exception("字符串没有关闭");
                String tempDealStr = str.substring(i,index + 1);
                //处理 \\，\"的情况
                String tmpResult = "";
                int tmpPoint = tempDealStr.indexOf("\\");
                while(tmpPoint >=0 ){
                    tmpResult = tmpResult + tempDealStr.substring(0,tmpPoint);
                    if(tmpPoint == tempDealStr.length() -1){
                        throw new Exception("字符串中的" + "\\错误:" + tempDealStr);
                    }
                    tmpResult = tmpResult + tempDealStr.substring(tmpPoint + 1 ,tmpPoint + 2);
                    tempDealStr = tempDealStr.substring(tmpPoint + 2);
                    tmpPoint = tempDealStr.indexOf("\\");
                }
                tmpResult = tmpResult + tempDealStr;
                list.add(new Word(tmpResult,line,i));

                if (point < i ){
                    list.add(new Word(str.substring(point,i),line,i));
                }
                i = index + 1;
                point = i;
            }else if(c=='.' && point < i && isNumber(str.substring(point,i))){
                i = i + 1; //小数点的特殊处理
            }else if(c == ' ' ||c =='\r'|| c =='\n'||c=='\t'||c=='\u000C'){
                if (point < i ){
                    list.add(new Word(str.substring(point,i),line,i));
                }
                if(c =='\n'){
                    line = line + 1;
                }
                i = i + 1;
                point = i;
            }else{
                boolean isFind = false;

                for(String s:splitWord){
                    int length = s.length();
                    if(i + length <= str.length() && str.substring(i, i+length).equals(s)){
                        if (point < i ){
                            list.add(new Word(str.substring(point,i),line,i));
                        }
                        list.add(new Word(str.substring(i, i+length),line,i+length));
                        i = i + length;
                        point = i;
                        isFind = true;
                        break;
                    }
                }
                if(isFind == false){
                    i = i+1;
                }
            }
        }
        if (point < i) {
            list.add(new Word(str.substring(point, i), line, i));
        }

        Word result[] = new Word[list.size()];
        list.toArray(result);
        return result;

    }

    private static boolean isNumber(String substring) {
        return false;
    }
}
