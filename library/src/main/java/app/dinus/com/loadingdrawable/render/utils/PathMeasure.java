package app.dinus.com.loadingdrawable.render.utils;

import ohos.agp.render.Path;
import ohos.agp.utils.Matrix;

public class PathMeasure {
    Path myPath;

    public PathMeasure(Path mRiverPath, boolean b) {
        myPath = mRiverPath;
    }

    public PathMeasure() {

    }

    public void getSegment(float topStartDistance, float topEndDistance, Path mCurrentTopWaitPath, boolean b) {
        mCurrentTopWaitPath = myPath;
    }

    public void setPath(Path mWaitPath, boolean b) {
        myPath = mWaitPath;
    }

    public float getLength() {
        return 0.0f;
    }
}