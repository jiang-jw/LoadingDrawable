package app.dinus.com.loadingdrawable.render.utils;

import ohos.agp.window.service.DisplayManager;
import ohos.app.Context;

public class DensityUtil {

    public static float dip2px(Context context, float dpValue) {
        float scale =  (DisplayManager.getInstance().getDefaultDisplay(context)).get().getAttributes().scalDensity;
        //float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }  
}