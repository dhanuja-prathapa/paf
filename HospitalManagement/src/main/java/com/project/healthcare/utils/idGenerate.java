package com.project.healthcare.utils;

import java.util.ArrayList;

public class idGenerate {
    public static int generateHosID(ArrayList<Integer> arrayList){
        int id = arrayList.size();
        id++;
        if (arrayList.contains(id)){
            id++;
        }
        return id;
    }

}
