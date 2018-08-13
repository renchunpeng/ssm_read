package com.soecode.test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rcp on 2018/6/8.
 */
public class temp {
    public static void main(String[] args) {
        double a = 9.825;
        NumberFormat nfFormat = NumberFormat.getInstance();
        nfFormat.setMaximumFractionDigits(2);
        System.out.println(nfFormat.format(a));
    }
}
