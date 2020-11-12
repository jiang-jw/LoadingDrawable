package app.dinus.com.loadingdrawable.render.shapechange;

//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.ColorFilter;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.graphics.PathMeasure;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.util.DisplayMetrics;
//import android.view.animation.AccelerateDecelerateInterpolator;
//import android.view.animation.AccelerateInterpolator;
//import android.view.animation.DecelerateInterpolator;
//import android.view.animation.Interpolator;

import app.dinus.com.loadingdrawable.render.utils.DensityUtil;
import app.dinus.com.loadingdrawable.render.utils.Interpolator;
import app.dinus.com.loadingdrawable.render.LoadingRenderer;
import app.dinus.com.loadingdrawable.render.utils.PathMeasure;
import app.dinus.com.loadingdrawable.render.utils.RectF;
import ohos.agp.animation.Animator;
import ohos.agp.render.Canvas;
import ohos.agp.render.ColorMatrix;
import ohos.agp.render.Paint;
import ohos.agp.render.Path;
import ohos.agp.utils.Color;
import ohos.agp.utils.Point;
import ohos.agp.utils.Rect;
import ohos.agp.utils.RectFloat;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class CoolWaitLoadingRenderer extends LoadingRenderer {
    static final HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0, "MY_TAG");

//    private final Interpolator ACCELERATE_INTERPOLATOR08 = new AccelerateInterpolator(0.8f);
//    private final Interpolator ACCELERATE_INTERPOLATOR10 = new AccelerateInterpolator(1.0f);
//    private final Interpolator ACCELERATE_INTERPOLATOR15 = new AccelerateInterpolator(1.5f);
    //TODO:Animator.CurveType ACCELERATE
//
//    private final Interpolator DECELERATE_INTERPOLATOR03 = new DecelerateInterpolator(0.3f);
//    private final Interpolator DECELERATE_INTERPOLATOR05 = new DecelerateInterpolator(0.5f);
//    private final Interpolator DECELERATE_INTERPOLATOR08 = new DecelerateInterpolator(0.8f);
//    private final Interpolator DECELERATE_INTERPOLATOR10 = new DecelerateInterpolator(1.0f);
    //TODO:Animator.CurveType DECELERATE
////
//    private static final Interpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    //TODO:Animator.CurveType ACCELERATE_DECELERATE

    private final float DEFAULT_WIDTH = 200.0f;
    private final float DEFAULT_HEIGHT = 150.0f;
    private final float DEFAULT_STROKE_WIDTH = 8.0f;
    private final float WAIT_CIRCLE_RADIUS = 50.0f;

    private static final float WAIT_TRIM_DURATION_OFFSET = 0.5f;
    private static final float END_TRIM_DURATION_OFFSET = 1.0f;

    private final long ANIMATION_DURATION = 2222;

    private final Paint mPaint = new Paint();

    private final Path mWaitPath = new Path();
    private boolean mWaitPathIsEmpty = true;
    private final Path mTest = new Path();
    private final Path mCurrentTopWaitPath = new Path();
    private final Path mCurrentMiddleWaitPath = new Path();
    private final Path mCurrentBottomWaitPath = new Path();
    private final PathMeasure mWaitPathMeasure = new PathMeasure();

    private final RectF mCurrentBounds = new RectF();

    private float mStrokeWidth;
    private float mWaitCircleRadius;
    private float mOriginEndDistance;
    private float mOriginStartDistance;
    private float mWaitPathLength;

    private Color mTopColor;
    private Color mMiddleColor;
    private Color mBottomColor;

    private CoolWaitLoadingRenderer(Context context) {
        super(context);
        init(context);
        setupPaint();
//        TimeInterpolator a;
    }

    private void init(Context context) {
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>init>>>1");
        mWidth = DensityUtil.dip2px(context, DEFAULT_WIDTH);
        mHeight = DensityUtil.dip2px(context, DEFAULT_HEIGHT);
        mStrokeWidth = DensityUtil.dip2px(context, DEFAULT_STROKE_WIDTH);
        mWaitCircleRadius = DensityUtil.dip2px(context, WAIT_CIRCLE_RADIUS);

        mTopColor = Color.WHITE;
        //mMiddleColor = Color.parseColor("#FFF3C742");
        mMiddleColor = new Color(0xFFF3C742);
        //mBottomColor = Color.parseColor("#FF89CC59");
        mBottomColor = new Color(0xFF89CC59);

        mDuration = ANIMATION_DURATION;
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>init>>>2");
    }

    private void setupPaint() {
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>setupPaint>>>1");
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mStrokeWidth);
        //mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStyle(Paint.Style.STROKE_STYLE);
        //mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND_JOIN);
        //mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeCap(Paint.StrokeCap.ROUND_CAP);
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>setupPaint>>>2");
    }

    @Override
    protected void draw(Canvas canvas, Rect bounds) {
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>draw>>>bounds left(%{public}d) top(%{public}d) right(%{public}d) bottom(%{public}d)",bounds.left,bounds.top,bounds.right,bounds.bottom);
        //int saveCount = canvas.save();
        //RectF arcBounds = mCurrentBounds;
        //arcBounds.set(bounds);
        mCurrentBounds.set(bounds);
        HiLog.info(label,"draw>>>>>>>"+mBottomColor);

        mPaint.setColor(mBottomColor);
        mPaint.setColor(Color.RED);
        //canvas.drawPath(mCurrentBottomWaitPath, mPaint);
        canvas.drawPath(mWaitPath, mPaint);

        mPaint.setColor(mMiddleColor);
        mPaint.setColor(Color.YELLOW);
        //canvas.drawPath(mCurrentMiddleWaitPath, mPaint);
        canvas.drawPath(mWaitPath, mPaint);

        mPaint.setColor(mTopColor);
        mPaint.setColor(Color.BLACK);
        //canvas.drawPath(mCurrentTopWaitPath, mPaint);
        canvas.drawPath(mWaitPath, mPaint);
        //
        canvas.drawPath(mTest,mPaint);
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>draw>>>2");
    }

    private Path createWaitPath(RectF bounds) {

        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>createWaitPath>>>bounds left(%{public}d) top(%{public}d) right(%{public}d) bottom(%{public}d)",bounds.left,bounds.top,bounds.right,bounds.bottom);

        Path path = new Path();
        //create circle
        path.moveTo(bounds.centerX() + mWaitCircleRadius, bounds.centerY());

        //create w
//        path.cubicTo(bounds.centerX() + mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius * 0.5f,
//                bounds.centerX() + mWaitCircleRadius * 0.3f, bounds.centerY() - mWaitCircleRadius,
//                bounds.centerX() - mWaitCircleRadius * 0.35f, bounds.centerY() + mWaitCircleRadius * 0.5f);
        path.cubicTo(new Point(bounds.centerX() + mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius * 0.5f),
                new Point(bounds.centerX() + mWaitCircleRadius * 0.3f, bounds.centerY() - mWaitCircleRadius),
                new Point(bounds.centerX() - mWaitCircleRadius * 0.35f, bounds.centerY() + mWaitCircleRadius * 0.5f));
//        path.quadTo(bounds.centerX() + mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius,
//                bounds.centerX() + mWaitCircleRadius * 0.05f, bounds.centerY() + mWaitCircleRadius * 0.5f);
        path.quadTo(new Point(bounds.centerX() + mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius),
                new Point(bounds.centerX() + mWaitCircleRadius * 0.05f, bounds.centerY() + mWaitCircleRadius * 0.5f));
        path.lineTo(bounds.centerX() + mWaitCircleRadius * 0.75f, bounds.centerY() - mWaitCircleRadius * 0.2f);

//        path.cubicTo(bounds.centerX(), bounds.centerY() + mWaitCircleRadius * 1f,
//                bounds.centerX() + mWaitCircleRadius, bounds.centerY() + mWaitCircleRadius * 0.4f,
//                bounds.centerX() + mWaitCircleRadius, bounds.centerY());
        path.cubicTo(new Point(bounds.centerX(), bounds.centerY() + mWaitCircleRadius * 1f),
                new Point(bounds.centerX() + mWaitCircleRadius, bounds.centerY() + mWaitCircleRadius * 0.4f),
                new Point(bounds.centerX() + mWaitCircleRadius, bounds.centerY()));
        //create arc
//        path.arcTo(new RectF(bounds.centerX() - mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius,
//                bounds.centerX() + mWaitCircleRadius, bounds.centerY() + mWaitCircleRadius), 0, -359);
        path.arcTo(new RectFloat(bounds.centerX() - mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius,
                bounds.centerX() + mWaitCircleRadius, bounds.centerY() + mWaitCircleRadius), 0, -359);
//        path.arcTo(new RectF(bounds.centerX() - mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius,
//                bounds.centerX() + mWaitCircleRadius, bounds.centerY() + mWaitCircleRadius), 1, -359);
        path.arcTo(new RectFloat(bounds.centerX() - mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius,
                bounds.centerX() + mWaitCircleRadius, bounds.centerY() + mWaitCircleRadius), 1, -359);
//        path.arcTo(new RectF(bounds.centerX() - mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius,
//                bounds.centerX() + mWaitCircleRadius, bounds.centerY() + mWaitCircleRadius), 2, -2);
        path.arcTo(new RectFloat(bounds.centerX() - mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius,
                bounds.centerX() + mWaitCircleRadius, bounds.centerY() + mWaitCircleRadius), 2, -2);
        //create w
//        path.cubicTo(bounds.centerX() + mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius * 0.5f,
//                bounds.centerX() + mWaitCircleRadius * 0.3f, bounds.centerY() - mWaitCircleRadius,
//                bounds.centerX() - mWaitCircleRadius * 0.35f, bounds.centerY() + mWaitCircleRadius * 0.5f);
        path.cubicTo(new Point(bounds.centerX() + mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius * 0.5f),
                new Point(bounds.centerX() + mWaitCircleRadius * 0.3f, bounds.centerY() - mWaitCircleRadius),
                new Point(bounds.centerX() - mWaitCircleRadius * 0.35f, bounds.centerY() + mWaitCircleRadius * 0.5f));
//        path.quadTo(bounds.centerX() + mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius,
//                bounds.centerX() + mWaitCircleRadius * 0.05f, bounds.centerY() + mWaitCircleRadius * 0.5f);
        path.quadTo(new Point(bounds.centerX() + mWaitCircleRadius, bounds.centerY() - mWaitCircleRadius),
                new Point(bounds.centerX() + mWaitCircleRadius * 0.05f, bounds.centerY() + mWaitCircleRadius * 0.5f));
        path.lineTo(bounds.centerX() + mWaitCircleRadius * 0.75f, bounds.centerY() - mWaitCircleRadius * 0.2f);

        path.cubicTo(new Point(bounds.centerX(), bounds.centerY() + mWaitCircleRadius * 1f),
                new Point(bounds.centerX() + mWaitCircleRadius, bounds.centerY() + mWaitCircleRadius * 0.4f),
                        new Point(bounds.centerX() + mWaitCircleRadius, bounds.centerY()));
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>createWaitPath>>>2");
        return path;
    }

    @Override
    protected void computeRender(float renderProgress) {
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>computeRender>>>1>>>renderProgress(%{public})f)",renderProgress);
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>computeRender>>>1>>>bounds left(%{public}f) top(%{public}f) right(%{public}f) bottom(%{public}f)",mCurrentBounds.left,mCurrentBounds.top,mCurrentBounds.right,mCurrentBounds.bottom);
        if (mCurrentBounds.isEmpty()) {
            HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>computeRender>>>1FFFFFFFF");
            return;
        }

        if (mWaitPathIsEmpty) {
            mWaitPath.set(createWaitPath(mCurrentBounds));
            mWaitPathMeasure.setPath(mWaitPath, false);
            mWaitPathLength = mWaitPathMeasure.getLength();

            mOriginEndDistance = mWaitPathLength * 0.255f;
            mOriginStartDistance = mWaitPathLength * 0.045f;
            mWaitPathIsEmpty = false;
        }

        mCurrentTopWaitPath.reset();
        mCurrentMiddleWaitPath.reset();
        mCurrentBottomWaitPath.reset();

        //draw the first half : top
        if (renderProgress <= WAIT_TRIM_DURATION_OFFSET) {
            //float topTrimProgress = ACCELERATE_DECELERATE_INTERPOLATOR.getInterpolation(renderProgress / WAIT_TRIM_DURATION_OFFSET);
            float topTrimProgress = Interpolator.getInterpolation(renderProgress / WAIT_TRIM_DURATION_OFFSET, Animator.CurveType.ACCELERATE_DECELERATE,0);
            float topEndDistance = mOriginEndDistance + mWaitPathLength * 0.3f * topTrimProgress;
            float topStartDistance = mOriginStartDistance + mWaitPathLength * 0.48f * topTrimProgress;
            mWaitPathMeasure.getSegment(topStartDistance, topEndDistance, mCurrentTopWaitPath, true);
        }

        //draw the first half : middle
        if (renderProgress > 0.02f * WAIT_TRIM_DURATION_OFFSET && renderProgress <= WAIT_TRIM_DURATION_OFFSET * 0.75f) {
//            float middleStartTrimProgress = ACCELERATE_INTERPOLATOR10.getInterpolation((renderProgress - 0.02f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.73f));
//            float middleEndTrimProgress = DECELERATE_INTERPOLATOR08.getInterpolation((renderProgress - 0.02f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.73f));
            float middleStartTrimProgress = Interpolator.getInterpolation((renderProgress - 0.02f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.73f), Animator.CurveType.ACCELERATE,1.0f);
            float middleEndTrimProgress = Interpolator.getInterpolation((renderProgress - 0.02f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.73f), Animator.CurveType.DECELERATE,0.8f);

            float middleEndDistance = mOriginStartDistance + mWaitPathLength * 0.42f * middleEndTrimProgress;
            float middleStartDistance = mOriginStartDistance + mWaitPathLength * 0.42f * middleStartTrimProgress;
            mWaitPathMeasure.getSegment(middleStartDistance, middleEndDistance, mCurrentMiddleWaitPath, true);
        }

        //draw the first half : bottom
        if (renderProgress > 0.04f * WAIT_TRIM_DURATION_OFFSET && renderProgress <= WAIT_TRIM_DURATION_OFFSET * 0.75f) {
//            float bottomStartTrimProgress = ACCELERATE_INTERPOLATOR15.getInterpolation((renderProgress - 0.04f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.71f));
//            float bottomEndTrimProgress = DECELERATE_INTERPOLATOR05.getInterpolation((renderProgress - 0.04f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.71f));
            float bottomStartTrimProgress = Interpolator.getInterpolation((renderProgress - 0.04f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.71f), Animator.CurveType.ACCELERATE,1.5f);
            float bottomEndTrimProgress = Interpolator.getInterpolation((renderProgress - 0.04f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.71f), Animator.CurveType.DECELERATE,0.5f);

            float bottomEndDistance = mOriginStartDistance + mWaitPathLength * 0.42f * bottomEndTrimProgress;
            float bottomStartDistance = mOriginStartDistance + mWaitPathLength * 0.42f * bottomStartTrimProgress;
            mWaitPathMeasure.getSegment(bottomStartDistance, bottomEndDistance, mCurrentBottomWaitPath, true);
        }

        //draw the last half : top
        if (renderProgress <= END_TRIM_DURATION_OFFSET && renderProgress > WAIT_TRIM_DURATION_OFFSET) {
//            float trimProgress = ACCELERATE_DECELERATE_INTERPOLATOR.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET) / (END_TRIM_DURATION_OFFSET - WAIT_TRIM_DURATION_OFFSET));
            float trimProgress = Interpolator.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET) / (END_TRIM_DURATION_OFFSET - WAIT_TRIM_DURATION_OFFSET),Animator.CurveType.ACCELERATE_DECELERATE,0f);
            float topEndDistance = mOriginEndDistance + mWaitPathLength * 0.3f + mWaitPathLength * 0.45f * trimProgress;
            float topStartDistance = mOriginStartDistance + mWaitPathLength * 0.48f + mWaitPathLength * 0.27f * trimProgress;
            mWaitPathMeasure.getSegment(topStartDistance, topEndDistance, mCurrentTopWaitPath, true);
        }

        //draw the last half : middle
        if (renderProgress > WAIT_TRIM_DURATION_OFFSET + 0.02f * WAIT_TRIM_DURATION_OFFSET && renderProgress <= WAIT_TRIM_DURATION_OFFSET + WAIT_TRIM_DURATION_OFFSET * 0.62f) {
//            float middleStartTrimProgress = ACCELERATE_INTERPOLATOR08.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.02f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.60f));
//            float middleEndTrimProgress = DECELERATE_INTERPOLATOR03.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.02f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.60f));
            float middleStartTrimProgress = Interpolator.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.02f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.60f),Animator.CurveType.ACCELERATE,0.8f);
            float middleEndTrimProgress = Interpolator.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.02f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.60f),Animator.CurveType.DECELERATE,0.3f);

            float middleEndDistance = mOriginStartDistance + mWaitPathLength * 0.48f + mWaitPathLength * 0.20f * middleEndTrimProgress;
            float middleStartDistance = mOriginStartDistance + mWaitPathLength * 0.48f + mWaitPathLength * 0.10f * middleStartTrimProgress;
            mWaitPathMeasure.getSegment(middleStartDistance, middleEndDistance, mCurrentMiddleWaitPath, true);
        }

        if (renderProgress > WAIT_TRIM_DURATION_OFFSET + 0.62f * WAIT_TRIM_DURATION_OFFSET && renderProgress <= END_TRIM_DURATION_OFFSET) {
//            float middleStartTrimProgress = DECELERATE_INTERPOLATOR10.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.62f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.38f));
//            float middleEndTrimProgress = DECELERATE_INTERPOLATOR03.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.62f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.38f));
            float middleStartTrimProgress = Interpolator.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.62f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.38f),Animator.CurveType.DECELERATE,1.0f);
            float middleEndTrimProgress = Interpolator.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.62f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.38f),Animator.CurveType.DECELERATE,0.3f);

            float middleEndDistance = mOriginStartDistance + mWaitPathLength * 0.68f + mWaitPathLength * 0.325f * middleEndTrimProgress;
            float middleStartDistance = mOriginStartDistance + mWaitPathLength * 0.58f + mWaitPathLength * 0.17f * middleStartTrimProgress;
            mWaitPathMeasure.getSegment(middleStartDistance, middleEndDistance, mCurrentMiddleWaitPath, true);
        }

        //draw the last half : bottom
        if (renderProgress > WAIT_TRIM_DURATION_OFFSET + 0.10f * WAIT_TRIM_DURATION_OFFSET && renderProgress <= WAIT_TRIM_DURATION_OFFSET + WAIT_TRIM_DURATION_OFFSET * 0.70f) {
//            float bottomStartTrimProgress = ACCELERATE_INTERPOLATOR15.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.10f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.60f));
//            float bottomEndTrimProgress = DECELERATE_INTERPOLATOR03.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.10f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.60f));
            float bottomStartTrimProgress = Interpolator.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.10f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.60f),Animator.CurveType.ACCELERATE,1.5f);
            float bottomEndTrimProgress = Interpolator.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.10f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.60f),Animator.CurveType.DECELERATE,0.3f);

            float bottomEndDistance = mOriginStartDistance + mWaitPathLength * 0.48f + mWaitPathLength * 0.20f * bottomEndTrimProgress;
            float bottomStartDistance = mOriginStartDistance + mWaitPathLength * 0.48f + mWaitPathLength * 0.10f * bottomStartTrimProgress;
            mWaitPathMeasure.getSegment(bottomStartDistance, bottomEndDistance, mCurrentBottomWaitPath, true);
        }

        if (renderProgress > WAIT_TRIM_DURATION_OFFSET + 0.70f * WAIT_TRIM_DURATION_OFFSET && renderProgress <= END_TRIM_DURATION_OFFSET) {
//            float bottomStartTrimProgress = DECELERATE_INTERPOLATOR05.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.70f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.30f));
//            float bottomEndTrimProgress = DECELERATE_INTERPOLATOR03.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.70f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.30f));
            float bottomStartTrimProgress = Interpolator.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.70f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.30f),Animator.CurveType.DECELERATE,0.5f);
            float bottomEndTrimProgress = Interpolator.getInterpolation((renderProgress - WAIT_TRIM_DURATION_OFFSET - 0.70f * WAIT_TRIM_DURATION_OFFSET) / (WAIT_TRIM_DURATION_OFFSET * 0.30f),Animator.CurveType.DECELERATE,0.3f);

            float bottomEndDistance = mOriginStartDistance + mWaitPathLength * 0.68f + mWaitPathLength * 0.325f * bottomEndTrimProgress;
            float bottomStartDistance = mOriginStartDistance + mWaitPathLength * 0.58f + mWaitPathLength * 0.17f * bottomStartTrimProgress;
            mWaitPathMeasure.getSegment(bottomStartDistance, bottomEndDistance, mCurrentBottomWaitPath, true);
        }
        mTest.reset();
        mTest.lineTo(500*renderProgress,500*renderProgress);
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>computeRender>>>2");
    }

    @Override
    protected void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

//    @Override
//    protected void setColorFilter(ColorFilter cf) {
//        mPaint.setColorFilter(cf);
//
//    }
    @Override
    protected void setColorFilter(ColorMatrix matrix) {
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>setColorFilter>>>1");
        mPaint.setColorMatrix(matrix);
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>setColorFilter>>>2");
    }

    @Override
    protected void reset() {
        HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>setColorFilter>>>1");
    }

    public static class Builder {
        private Context mContext;

        public Builder(Context mContext) {
            HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>Builder==>>>1");
            this.mContext = mContext;
            HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>Builder===>>>2");
        }

        public CoolWaitLoadingRenderer build() {
            HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>CoolWaitLoadingRenderer==>>>1");
            CoolWaitLoadingRenderer loadingRenderer = new CoolWaitLoadingRenderer(mContext);
            HiLog.info(label,">>>>CoolWaitLoadingRenderer>>>>CoolWaitLoadingRenderer==>>>2");
            return loadingRenderer;
        }
    }

}

