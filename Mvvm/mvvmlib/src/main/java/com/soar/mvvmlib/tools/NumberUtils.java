package com.soar.mvvmlib.tools;

import java.text.DecimalFormat;

/**
 * Created by gaofei on 2016/9/23.
 */
public class NumberUtils {


    /**
     * 四舍五入取整
     * @param num
     * @return
     */
    public static String getRoundingNum(float num){
        DecimalFormat fnum   =   new DecimalFormat("##0");
        return fnum.format(num);
    }

}
