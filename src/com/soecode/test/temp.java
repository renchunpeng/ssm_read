package com.soecode.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rcp on 2018/6/8.
 */
public class temp {
    public static void main(String[] args){
        List<Person> list2 = new ArrayList<>();
        List<Person> list = new ArrayList<>();
        Person p1 = new Person();
        Person p2 = new Person();
        p1.setName("任春鹏");
        p1.setAge("27");
        p1.setSex("1");
        list.add(p1);
        p2.setName("王倩");
        p2.setAge("26");
        p2.setSex("0");
        list2.add(p2);
        list.addAll(list2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());

        }
    }
}
