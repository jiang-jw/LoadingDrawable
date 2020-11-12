package app.dinus.com.loadingdrawable.render;

import app.dinus.com.loadingdrawable.render.base.Animatable;
import app.dinus.com.loadingdrawable.render.base.Drawable;
import ohos.agp.components.Component;
import ohos.agp.components.element.Element;
import ohos.agp.render.Canvas;
import ohos.agp.render.ColorMatrix;
import ohos.agp.utils.Rect;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.lang.ref.WeakReference;

public class LoadingDrawable extends Drawable implements Animatable {
    static final HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0, "MY_TAG");
    private final Component mLoadingView;
    private final LoadingRenderer mLoadingRender;
    private final Callback mCallback = new Callback() {
        @Override
        public void invalidateDrawable(Drawable d) {
            invalidateSelf();
            HiLog.info(label,"=================1");
            mLoadingView.addDrawTask(new Component.DrawTask() {
                @Override
                public void onDraw(Component component, Canvas canvas) {
                    setBounds(0,0,component.getWidth(),component.getHeight());
                    draw(canvas);
                }
            });
            HiLog.info(label,"=================2");
        }

        @Override
        public void scheduleDrawable(Drawable d, Runnable what, long when) {
            scheduleSelf(what, when);
        }

        @Override
        public void unscheduleDrawable(Drawable d, Runnable what) {
            unscheduleSelf(what);
        }
    };

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mLoadingRender.setBounds(new Rect(left, top, right, bottom));
    }

    public LoadingDrawable(LoadingRenderer loadingRender, Component loadingView) {
        this.mLoadingView = loadingView;
        this.mLoadingRender = loadingRender;
        this.mLoadingRender.setCallback(mCallback);
    }

    @Override
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mLoadingRender.setBounds(rect);
    }

    @Override
    public void draw(Canvas canvas) {
        if (!getBounds().isEmpty()) {
            this.mLoadingRender.draw(canvas);
        }
    }
    @Override
    public void setAlpha(int alpha) {
        this.mLoadingRender.setAlpha(alpha);
    }
    @Override
    public void setColorFilter(ColorMatrix matrix) {
        this.mLoadingRender.setColorFilter(matrix);;
    }
    @Override
    public int getOpacity() {
        return Element.ALPHA_DEFAULT;
    }
    @Override
    public void start() {
        HiLog.info(label,">>>>LoadingDrawable>>>>start>>>1");

        this.mLoadingRender.start();
        HiLog.info(label,">>>>LoadingDrawable>>>>start>>>2");
    }

    @Override
    public void stop() {
        this.mLoadingRender.stop();
    }

    @Override
    public boolean isRunning() {
        return this.mLoadingRender.isRunning();
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) this.mLoadingRender.mHeight;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) this.mLoadingRender.mWidth;
    }
}
