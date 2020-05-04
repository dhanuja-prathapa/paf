package com.project.healthcare.utils;

import java.util.ArrayList;

public class idGenerate {
    public static int findnewid(ArrayList<Integer> arrayList,int id){
        if(arrayList.contains(id) ){
            id++;
           return findnewid(arrayList,id);
        }else
        return id;
    }
    public static int generateHosID(ArrayList<Integer> arrayList){
        int id = arrayList.size();
           id = findnewid(arrayList,id);
        return id;
    }
}

