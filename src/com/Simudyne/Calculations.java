package com.Simudyne;

import com.google.common.base.Preconditions;

/**
 * Created by selwynstephen on 15/04/16.
 */
public class Calculations {


    public static int incrementAge(int age, int incrementValue) {
        Preconditions.checkArgument(age>0, "Age must positive");
        return  age + incrementValue;
    }


    public static float calculateAffinity(int paymentAtPurchase, Float attributePrice, Float attributePromotions, int intertiaForSwitch) {
        return paymentAtPurchase/attributePrice + (2 * attributePromotions * intertiaForSwitch);
    }
}
