package com.sofiamarchinskaya.moretechmobile.utils;

import com.sofiamarchinskaya.moretechmobile.Constant;
import com.sofiamarchinskaya.moretechmobile.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GraphUtils {
    public static int [] generateDown(){
        Random random = new Random();
        int [] mass = new int[Constant.NUMBER_OF_DOTS];
        for (int i = 0; i < Constant.NUMBER_OF_DOTS; i++) {
            mass[i] = random.nextInt(100)+10;
        }
        for (int i = 0; i < Constant.NUMBER_OF_DOTS; i++) {
            for (int j = i; j < Constant.NUMBER_OF_DOTS; j++) {
                if (i!=j&&mass[i]<mass[j]){
                    int swap = mass[i];
                    mass[i] = mass[j];
                    mass[j] = swap;
                }
            }
        }

        for (int i = 2; i < Constant.NUMBER_OF_DOTS; i+=2) {
            mass[i] = (int)(mass[i-1] + mass[i-1] * random.nextInt(3)/10f);
        }
        return mass;
    }

    public static int [] generateUp(){
        Random random = new Random();
        int [] mass = new int[Constant.NUMBER_OF_DOTS];
        for (int i = 0; i < Constant.NUMBER_OF_DOTS; i++) {
            mass[i] = random.nextInt(100)+10;
        }
        for (int i = 0; i < Constant.NUMBER_OF_DOTS; i++) {
            for (int j = i; j < Constant.NUMBER_OF_DOTS; j++) {
                if (i!=j&&mass[i]>mass[j]){
                    int swap = mass[i];
                    mass[i] = mass[j];
                    mass[j] = swap;
                }
            }
        }

        for (int i = 2; i < Constant.NUMBER_OF_DOTS; i+=2) {
            mass[i] = (int)(mass[i-1] - mass[i-1] * random.nextInt(3)/10f);
        }
        return mass;
    }
}
