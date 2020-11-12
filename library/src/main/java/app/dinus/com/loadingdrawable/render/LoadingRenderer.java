package app.dinus.com.loadingdrawable.render;

import app.dinus.com.loadingdrawable.render.utils.DensityUtil;
import app.dinus.com.loadingdrawable.render.base.Drawable;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.render.Canvas;
import ohos.agp.render.ColorMatrix;
import ohos.agp.utils.Rect;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public abstract class LoadingRenderer {
    static final HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0, "MY_TAG");

    private static final long ANIMATION_DURATION = 1333;
    private static final float DEFAULT_SIZE = 56.0f;
    private int count = 0;
    private final AnimatorValue.ValueUpdateListener mAnimatorUpdateListener
            = new AnimatorValue.ValueUpdateListener() {
        @Override
        public void onUpdate(AnimatorValue animatorValue, float v) {
            HiLog.info(label,">>>>>>>>>AnimatorValue======v(%{public}f.)"+v,v);
            count++;
            HiLog.info(label,">>>>>>>>>AnimatorValue======count(%{public}d.)",count);
            computeRender(v);
            invalidateSelf();
        }
    };
    /**
     * Whenever {@link LoadingDrawable} boundary changes mBounds will be updated.
     * More details you can see {@link LoadingDrawable#onBoundsChange(Rect)}
     */
    protected final Rect mBounds = new Rect();
    private Drawable.Callback mCallback;
    private AnimatorValue mRenderAnimator;

    protected long mDuration;
    protected float mWidth;
    protected float mHeight;

    public LoadingRenderer(Context context) {
        initParams(context);
        setupAnimators();
    }

    @Deprecated
    protected void draw(Canvas canvas, Rect bounds) {

    }
    public void draw(Canvas canvas) {
        draw(canvas, mBounds);
    }

    protected abstract void computeRender(float renderProgress);
    protected abstract void setAlpha(int alpha);
    protected abstract void setColorFilter(ColorMatrix matrix);
    protected abstract void reset();

    protected void addRenderListener(Animator.StateChangedListener animatorListener) {
        mRenderAnimator.setStateChangedListener(animatorListener);;
    }

    void start() {
        HiLog.info(label,">>>>LoadingRenderer>>>>start>>>1");
        reset();
        mRenderAnimator.setValueUpdateListener(mAnimatorUpdateListener);
        mRenderAnimator.setLoopedCount(2);
        mRenderAnimator.setDuration(mDuration);
        mRenderAnimator.start();
        HiLog.info(label,">>>>LoadingRenderer>>>>start>>>2");
    }

    void stop() {
        // if I just call mRenderAnimator.end(),
        // it will always call the method onAnimationUpdate(ValueAnimator animation)
        // why ? if you know why please send email to me (dinus_developer@163.com)
        mRenderAnimator.setValueUpdateListener(null);
        mRenderAnimator.setLoopedCount(0);
        mRenderAnimator.setDuration(0);
        mRenderAnimator.stop();;
    }
    boolean isRunning() {
        return mRenderAnimator.isRunning();
    }

    void setCallback(Drawable.Callback callback) {
        this.mCallback = callback;
    }

    void setBounds(Rect bounds) {
        mBounds.set(bounds.left,bounds.top,bounds.right,bounds.bottom);
    }

    private void initParams(Context context) {
        mWidth = DensityUtil.dip2px(context, DEFAULT_SIZE);
        mHeight = DensityUtil.dip2px(context, DEFAULT_SIZE);
        mDuration = ANIMATION_DURATION;
    }
    private void setupAnimators() {
        mRenderAnimator = new AnimatorValue();
        mRenderAnimator.setLoopedCount(2);
        mRenderAnimator.setDuration(mDuration);
        mRenderAnimator.setValueUpdateListener(mAnimatorUpdateListener);
    }

    private void invalidateSelf() {
        mCallback.invalidateDrawable(null);
    }
}
