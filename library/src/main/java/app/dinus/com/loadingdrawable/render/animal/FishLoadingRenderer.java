package app.dinus.com.loadingdrawable.render.animal;

import app.dinus.com.loadingdrawable.render.LoadingRenderer;
import app.dinus.com.loadingdrawable.render.utils.DensityUtil;
import app.dinus.com.loadingdrawable.render.utils.PathMeasure;
import app.dinus.com.loadingdrawable.render.utils.RectF;
import ohos.agp.render.Canvas;
import ohos.agp.render.ColorMatrix;
import ohos.agp.render.Paint;
import ohos.agp.render.Path;
import ohos.agp.utils.*;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class FishLoadingRenderer extends LoadingRenderer {
    //private Interpolator FISH_INTERPOLATOR = new FishInterpolator();
    HiLogLabel label = new HiLogLabel(HiLog.LOG_APP,0,"MY_TAG");
    private static final float DEFAULT_PATH_FULL_LINE_SIZE = 7.0f;
    private static final float DEFAULT_PATH_DOTTED_LINE_SIZE = DEFAULT_PATH_FULL_LINE_SIZE / 2.0f;
    private static final float DEFAULT_RIVER_HEIGHT = DEFAULT_PATH_FULL_LINE_SIZE * 8.5f;
    private static final float DEFAULT_RIVER_WIDTH = DEFAULT_PATH_FULL_LINE_SIZE * 5.5f;

    private static final float DEFAULT_FISH_EYE_SIZE = DEFAULT_PATH_FULL_LINE_SIZE * 0.5f;
    private static final float DEFAULT_FISH_WIDTH = DEFAULT_PATH_FULL_LINE_SIZE * 3.0f;
    private static final float DEFAULT_FISH_HEIGHT = DEFAULT_PATH_FULL_LINE_SIZE * 4.5f;

    private static final float DEFAULT_WIDTH = 200.0f;
    private static final float DEFAULT_HEIGHT = 150.0f;
    private static final float DEFAULT_RIVER_BANK_WIDTH = DEFAULT_PATH_FULL_LINE_SIZE;

    private static final long ANIMATION_DURATION = 800;
    private static final float DOTTED_LINE_WIDTH_COUNT = (8.5f + 5.5f - 2.0f) * 2.0f * 2.0f;
    private static final float DOTTED_LINE_WIDTH_RATE = 1.0f / DOTTED_LINE_WIDTH_COUNT;

    private final float[] FISH_MOVE_POINTS = new float[]{
            DOTTED_LINE_WIDTH_RATE * 3.0f, DOTTED_LINE_WIDTH_RATE * 6.0f,
            DOTTED_LINE_WIDTH_RATE * 15f, DOTTED_LINE_WIDTH_RATE * 18f,
            DOTTED_LINE_WIDTH_RATE * 27.0f, DOTTED_LINE_WIDTH_RATE * 30.0f,
            DOTTED_LINE_WIDTH_RATE * 39f, DOTTED_LINE_WIDTH_RATE * 42f,
    };

    private final float FISH_MOVE_POINTS_RATE = 1.0f / FISH_MOVE_POINTS.length;

    //private static final int DEFAULT_COLOR = Color.parseColor("#fffefed6");
    private static final Color DEFAULT_COLOR = new Color(0xfffefed6);

    private final Paint mPaint = new Paint();
    private final RectF mTempBounds = new RectF();

    private final float[] mFishHeadPos = new float[2];

    private Path mRiverPath;
    private PathMeasure mRiverMeasure;

    private float mFishRotateDegrees;

    private float mRiverBankWidth;
    private float mRiverWidth;
    private float mRiverHeight;
    private float mFishWidth;
    private float mFishHeight;
    private float mFishEyeSize;
    private float mPathFullLineSize;
    private float mPathDottedLineSize;

    private Color mColor;
    public FishLoadingRenderer(Context context) {
        super(context);
        init(context);
        setupPaint();
    }
    private void init(Context context) {
        mWidth = DensityUtil.dip2px(context, DEFAULT_WIDTH);
        mHeight = DensityUtil.dip2px(context, DEFAULT_HEIGHT);
        mRiverBankWidth = DensityUtil.dip2px(context, DEFAULT_RIVER_BANK_WIDTH);

        mPathFullLineSize = DensityUtil.dip2px(context, DEFAULT_PATH_FULL_LINE_SIZE);
        mPathDottedLineSize = DensityUtil.dip2px(context, DEFAULT_PATH_DOTTED_LINE_SIZE);
        mFishWidth = DensityUtil.dip2px(context, DEFAULT_FISH_WIDTH);
        mFishHeight = DensityUtil.dip2px(context, DEFAULT_FISH_HEIGHT);
        mFishEyeSize = DensityUtil.dip2px(context, DEFAULT_FISH_EYE_SIZE);
        mRiverWidth = DensityUtil.dip2px(context, DEFAULT_RIVER_WIDTH);
        mRiverHeight = DensityUtil.dip2px(context, DEFAULT_RIVER_HEIGHT);

        mColor = DEFAULT_COLOR;

        mDuration = ANIMATION_DURATION;
    }
    private void setupPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mRiverBankWidth);
        mPaint.setStyle(Paint.Style.STROKE_STYLE);
        mPaint.setStrokeJoin(Paint.Join.MITER_JOIN);
       // mPaint.setPathEffect(new DashPathEffect(new float[]{mPathFullLineSize, mPathDottedLineSize}, mPathDottedLineSize));
        mPaint.setDashPathEffectIntervals(new float[]{mPathFullLineSize, mPathDottedLineSize});
        mPaint.setDashPathEffectPhase(mPathDottedLineSize);
    }

    @Override
    protected void draw(Canvas canvas, Rect bounds) {
        //int saveCount = canvas.save();
        RectF arcBounds = mTempBounds;
        arcBounds.set(bounds);

        mPaint.setColor(mColor);

        //calculate fish clip bounds
        //clip the width of the fish need to increase mPathDottedLineSize * 1.2f
        RectF fishRectF = new RectF(mFishHeadPos[0] - mFishWidth / 2.0f - mPathDottedLineSize * 1.2f, mFishHeadPos[1] - mFishHeight / 2.0f,
                mFishHeadPos[0] + mFishWidth / 2.0f + mPathDottedLineSize * 1.2f, mFishHeadPos[1] + mFishHeight / 2.0f);
//        Matrix matrix = new Matrix();
//        matrix.postRotate(mFishRotateDegrees, fishRectF.centerX(), fishRectF.centerY());
//        matrix.mapRect(fishRectF);

        //draw river
        //int riverSaveCount = canvas.save();
        mPaint.setStyle(Paint.Style.STROKE_STYLE);
        //canvas.clipRect(fishRectF, Region.Op.DIFFERENCE);
        canvas.drawPath(createRiverPath(arcBounds), mPaint);
        //canvas.restoreToCount(riverSaveCount);
        //draw fish
        //int fishSaveCount = canvas.save();
        mPaint.setStyle(Paint.Style.FILL_STYLE);
        //canvas.rotate(mFishRotateDegrees, mFishHeadPos[0], mFishHeadPos[1]);
        //canvas.clipPath(createFishEyePath(mFishHeadPos[0], mFishHeadPos[1] - mFishHeight * 0.06f), Region.Op.DIFFERENCE);
        Path curFishPath = createFishPath(mFishHeadPos[0], mFishHeadPos[1]);
        HiLog.info(label,"========mFishRotateDegrees>>>>>"+mFishRotateDegrees);
        Matrix matrix = new Matrix(new float[]{1,0,0,0,1,0,0,0,1});
        matrix.translate(mFishHeadPos[0],mFishHeadPos[1]);
        matrix.rotate(mFishRotateDegrees);
        matrix.translate(-mFishHeadPos[0],-mFishHeadPos[1]);
        //Matrix matrix = new Matrix(new float[]{(float) Math.cos(mFishRotateDegrees), (float) -Math.sin(mFishRotateDegrees),0,(float) Math.sin(mFishRotateDegrees),(float) Math.cos(mFishRotateDegrees),0,0,0,1});
        //matrix.rotate(mFishRotateDegrees);
        curFishPath.transform(matrix);
        canvas.drawPath(curFishPath, mPaint);
        //canvas.restoreToCount(fishSaveCount);

        //canvas.restoreToCount(saveCount);
    }
    private float calculateRotateDegrees(float fishProgress) {
        if (fishProgress < FISH_MOVE_POINTS_RATE * 2) {
            return 90;
        }

        if (fishProgress < FISH_MOVE_POINTS_RATE * 4) {
            return 180;
        }

        if (fishProgress < FISH_MOVE_POINTS_RATE * 6) {
            return 270;
        }

        return 0.0f;
    }
    @Override
    protected void computeRender(float renderProgress) {
        if (mRiverPath == null) {
            return;
        }

        if (mRiverMeasure == null) {
            mRiverMeasure = new PathMeasure(mRiverPath, false);
        }

        float fishProgress = getFISHInterpolation(renderProgress);

        //mRiverMeasure.getPosTan(mRiverMeasure.getLength() * fishProgress, mFishHeadPos, null);
        float curLength = (mRiverWidth+mRiverHeight) * 2 * fishProgress;
        if(curLength < mRiverWidth){
            mFishHeadPos[0] = curLength;
            mFishHeadPos[1] = 0;
        }else if(curLength < (mRiverWidth+mRiverHeight)){
            mFishHeadPos[0] = mRiverWidth;
            mFishHeadPos[1] = curLength - mRiverWidth;
        }else if(curLength < (mRiverWidth*2+mRiverHeight)){
            mFishHeadPos[0] = mRiverWidth*2+mRiverHeight - curLength;
            mFishHeadPos[1] = mRiverHeight;
        }else if(curLength < (mRiverWidth*2+mRiverHeight*2)){
            mFishHeadPos[0] = 0;
            mFishHeadPos[1] = (mRiverWidth+mRiverHeight) * 2 - curLength;
        }
        mFishHeadPos[0] = mFishHeadPos[0] + mTempBounds.centerX() - mRiverWidth/2;
        mFishHeadPos[1] =  mFishHeadPos[1] + mTempBounds.centerY() - mRiverHeight/2;
        mFishRotateDegrees = calculateRotateDegrees(fishProgress);
    }
    public float getFISHInterpolation(float input) {
        int index = ((int) (input / FISH_MOVE_POINTS_RATE));
        if (index >= FISH_MOVE_POINTS.length) {
            index = FISH_MOVE_POINTS.length - 1;
        }

        return FISH_MOVE_POINTS[index];
    }
    @Override
    protected void setAlpha(int alpha) {

    }

    @Override
    protected void setColorFilter(ColorMatrix matrix) {

    }

    @Override
    protected void reset() {

    }
    private Path createFishEyePath(float fishEyeCenterX, float fishEyeCenterY) {
        Path path = new Path();
        path.addCircle(new Point(fishEyeCenterX, fishEyeCenterY), mFishEyeSize, Path.Direction.CLOCK_WISE);

        return path;
    }
    private Path createFishPath(float fishCenterX, float fishCenterY) {
        Path path = new Path();

        float fishHeadX = fishCenterX;
        float fishHeadY = fishCenterY - mFishHeight / 2.0f;

        //the head of the fish
        path.moveTo(fishHeadX, fishHeadY);
        //the left body of the fish
        path.quadTo(new Point(fishHeadX - mFishWidth * 0.333f, fishHeadY + mFishHeight * 0.222f),
                new Point(fishHeadX - mFishWidth * 0.333f, fishHeadY + mFishHeight * 0.444f));
        path.lineTo(fishHeadX - mFishWidth * 0.333f, fishHeadY + mFishHeight * 0.666f);
        path.lineTo(fishHeadX - mFishWidth * 0.5f, fishHeadY + mFishHeight * 0.8f);
        path.lineTo(fishHeadX - mFishWidth * 0.5f, fishHeadY + mFishHeight);

        //the tail of the fish
        path.lineTo(fishHeadX, fishHeadY + mFishHeight * 0.9f);

        //the right body of the fish
        path.lineTo(fishHeadX + mFishWidth * 0.5f, fishHeadY + mFishHeight);
        path.lineTo(fishHeadX + mFishWidth * 0.5f, fishHeadY + mFishHeight * 0.8f);
        path.lineTo(fishHeadX + mFishWidth * 0.333f, fishHeadY + mFishHeight * 0.666f);
        path.lineTo(fishHeadX + mFishWidth * 0.333f, fishHeadY + mFishHeight * 0.444f);
        path.quadTo(new Point(fishHeadX + mFishWidth * 0.333f, fishHeadY + mFishHeight * 0.222f),
                new Point(fishHeadX, fishHeadY));

        path.close();

        return path;
    }
    private Path createRiverPath(RectF arcBounds) {
        if (mRiverPath != null) {
            return mRiverPath;
        }

        mRiverPath = new Path();

        RectF rectF = new RectF(arcBounds.centerX() - mRiverWidth / 2.0f, arcBounds.centerY() - mRiverHeight / 2.0f,
                arcBounds.centerX() + mRiverWidth / 2.0f, arcBounds.centerY() + mRiverHeight / 2.0f);

        rectF.inset(mRiverBankWidth / 2.0f, mRiverBankWidth / 2.0f);

        mRiverPath.addRect(rectF.getRectFloat(), Path.Direction.CLOCK_WISE);

        return mRiverPath;
    }
    public static class Builder {
        private Context mContext;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public FishLoadingRenderer build() {
            FishLoadingRenderer loadingRenderer = new FishLoadingRenderer(mContext);
            return loadingRenderer;
        }
    }
}
