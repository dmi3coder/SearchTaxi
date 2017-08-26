package io.github.dmi3coder.searchtaxi;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by dim3coder on 8/26/17.
 */
public class Utils {

  private Utils(){}

  public static float convertDpToPixel(float dp, Context context){
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    return dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
  }


}
