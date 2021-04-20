package edu.cooper.ece366.DBconnection;

import com.google.common.base.Splitter;

import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
        String str = "1, 2, 3, 4";
        String s =  Arrays.toString(str.split("[, ]+"));
        System.out.println(s);

        List<String> resultList = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str);
        String pt = resultList.toString();
        System.out.println("hi: '" + pt + "'");
    }
}
