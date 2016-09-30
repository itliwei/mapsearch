package com.yimayhd.mapsearch.client.util;

import com.yimayhd.mapsearch.client.domain.top.HomeSubjectDO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by menhaihao on 15/7/17.
 *
 */
public class HomeSubjectComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        HomeSubjectDO homeSubjectDO1 = (HomeSubjectDO) o1;
        HomeSubjectDO homeSubjectDO2 = (HomeSubjectDO) o2;
        return homeSubjectDO2.getWeight() - homeSubjectDO1.getWeight();
    }

    public static void main(String[] args) {
        HomeSubjectDO h1 = new HomeSubjectDO();
        h1.setWeight(1);
        HomeSubjectDO h2 = new HomeSubjectDO();
        h2.setWeight(2);
        List<HomeSubjectDO> list = new ArrayList<HomeSubjectDO>();
        list.add(h1);
        list.add(h2);
        Collections.sort(list, new HomeSubjectComparator());
        for (HomeSubjectDO homeSubjectDO : list) {
            System.out.println(homeSubjectDO);
        }
    }

    public static void sortHomeSubjectDOList(List<HomeSubjectDO> list) {
        if (list == null) {
            return;
        }
        Collections.sort(list, new HomeSubjectComparator());
    }
}
