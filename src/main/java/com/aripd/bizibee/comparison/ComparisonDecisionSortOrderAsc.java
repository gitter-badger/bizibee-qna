package com.aripd.bizibee.comparison;

import com.aripd.bizibee.entity.DecisionEntity;
import java.util.Comparator;

public class ComparisonDecisionSortOrderAsc implements Comparator<DecisionEntity> {

    @Override
    public int compare(DecisionEntity o1, DecisionEntity o2) {
        Integer rollno1 = o1.getSortOrder();
        Integer rollno2 = o2.getSortOrder();
        return rollno1.compareTo(rollno2);
        //int rollno1 = o1.getSortOrder();
        //int rollno2 = o2.getSortOrder();
        //return rollno1 - rollno2;//For ascending order
        //return rollno2-rollno1;//For descending order
    }

}