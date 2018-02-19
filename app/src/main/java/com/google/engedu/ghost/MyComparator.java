package com.google.engedu.ghost;

/**
 * Created by Himanshu Palve on 2/17/2018.
 */

public class MyComparator implements java.util.Comparator<String> {


    @Override
    public int compare(String s1, String s2) {
        int dist1 = Math.abs(s1.length() );
        int dist2 = Math.abs(s2.length() );

        return dist1 - dist2;
    }
}