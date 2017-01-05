package com.aripd.bizibee.comparison;

import com.aripd.bizibee.entity.GuideEntity;
import java.util.Comparator;

public class ComparisonGuideSortOrderAsc implements Comparator<GuideEntity> {

    @Override
    public int compare(GuideEntity o1, GuideEntity o2) {
        Integer rollno1 = o1.getSortOrder();
        Integer rollno2 = o2.getSortOrder();
        return rollno1.compareTo(rollno2);
        //int rollno1 = o1.getSortOrder();
        //int rollno2 = o2.getSortOrder();
        //return rollno1 - rollno2;//For ascending order
        //return rollno2-rollno1;//For descending order
    }

}