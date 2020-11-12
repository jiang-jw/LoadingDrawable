package app.dinus.com.loadingdrawable.render.utils;

import ohos.agp.animation.Animator;

public class Interpolator {
    public static float getInterpolation(float input, int curveType,float factor) {
        switch (curveType){
            case Animator.CurveType.ACCELERATE_DECELERATE:
                return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
            case Animator.CurveType.ACCELERATE:
                if (factor == 1.0f) {
                    return input * input;
                } else {
                    return (float)Math.pow(input, 2 * factor);
                }
            case Animator.CurveType.DECELERATE:
                float result;
                if (factor == 1.0f) {
                    result = (float)(1.0f - (1.0f - input) * (1.0f - input));
                } else {
                    result = (float)(1.0f - Math.pow((1.0f - input), 2 * factor));
                }
                return result;
            default:
                break;
        }
        return 1;
    }
}
