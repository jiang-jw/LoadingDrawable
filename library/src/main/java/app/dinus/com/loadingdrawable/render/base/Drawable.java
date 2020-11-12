package app.dinus.com.loadingdrawable.render.base;

import ohos.agp.components.ComponentState;
import ohos.agp.render.Canvas;
import ohos.agp.render.ColorMatrix;
import ohos.agp.utils.Rect;

import java.lang.ref.WeakReference;
import java.util.Arrays;

public abstract class Drawable {
    private static final Rect ZERO_BOUNDS_RECT = new Rect();
    private Rect mBounds = ZERO_BOUNDS_RECT;  // lazily becomes a new Rect()
    private WeakReference<Callback> mCallback = null;
    private int[] mStateSet = new int[]{ComponentState.COMPONENT_STATE_EMPTY,ComponentState.COMPONENT_STATE_CHECKED,ComponentState.COMPONENT_STATE_DISABLED,ComponentState.COMPONENT_STATE_FOCUSED,ComponentState.COMPONENT_STATE_HOVERED,ComponentState.COMPONENT_STATE_PRESSED,ComponentState.COMPONENT_STATE_SELECTED};
    private int mLevel = 0;
    public Canvas mcanvas;
    public void setCanvas(Canvas canvas){
        mcanvas = canvas;
    }
    /**
     * Specify a bounding rectangle for the Drawable. This is where the drawable
     * will draw when its draw() method is called.
     */
    //TODO:CALL
    public void setBounds(int left, int top, int right, int bottom) {
        Rect oldBounds = mBounds;

        if (oldBounds == ZERO_BOUNDS_RECT) {
            oldBounds = mBounds = new Rect();
        }

        if (oldBounds.left != left || oldBounds.top != top ||
                oldBounds.right != right || oldBounds.bottom != bottom) {
            if (!oldBounds.isEmpty()) {
                // first invalidate the previous bounds
                invalidateSelf();
            }
            mBounds.set(left, top, right, bottom);
            onBoundsChange(mBounds);
        }
    }
    /**
     * Return the intrinsic width of the underlying drawable object.  Returns
     * -1 if it has no intrinsic width, such as with a solid color.
     */
    //TODO:CALL
    public int getIntrinsicWidth() {
        return -1;
    }

    /**
     * Return the intrinsic height of the underlying drawable object. Returns
     * -1 if it has no intrinsic height, such as with a solid color.
     */
    //TODO:CALL
    public int getIntrinsicHeight() {
        return -1;
    }

    /**
     * Specify a set of states for the drawable. These are use-case specific,
     * so see the relevant documentation.
     * [{@link ComponentState}
     *
     * <p>If the new state you are supplying causes the appearance of the
     * Drawable to change, then it is responsible for calling
     * {@link #invalidateSelf} in order to have itself redrawn, <em>and</em>
     * true will be returned from this function.
     *
     * <p>Note: The Drawable holds a reference on to <var>stateSet</var>
     * until a new state array is given to it, so you must not modify this
     * array during that time.</p>
     *
     * @param stateSet The new set of states to be displayed.
     *
     * @return Returns true if this change in state has caused the appearance
     * of the Drawable to change (hence requiring an invalidate), otherwise
     * returns false.
     */
    //TODO:CALL
    public boolean setState(final int[] stateSet) {
        if (!Arrays.equals(mStateSet, stateSet)) {
            mStateSet = stateSet;
            return onStateChange(stateSet);
        }
        return false;
    }
    /**
     * Specify the level for the drawable.  This allows a drawable to vary its
     * imagery based on a continuous controller, for example to show progress
     * or volume level.
     *
     * <p>If the new level you are supplying causes the appearance of the
     * Drawable to change, then it is responsible for calling
     * {@link #invalidateSelf} in order to have itself redrawn, <em>and</em>
     * true will be returned from this function.
     *
     * @param level The new level, from 0 (minimum) to 10000 (maximum).
     *
     * @return Returns true if this change in level has caused the appearance
     * of the Drawable to change (hence requiring an invalidate), otherwise
     * returns false.
     */
    public final boolean setLevel(int level) {
        if (mLevel != level) {
            mLevel = level;
            return onLevelChange(level);
        }
        return false;
    }
    /**
     * Override this in your subclass to change appearance if you recognize the
     * specified state.
     *
     * @return Returns true if the state change has caused the appearance of
     * the Drawable to change (that is, it needs to be drawn), else false
     * if it looks the same and there is no need to redraw it since its
     * last state.
     */
    protected boolean onStateChange(int[] state) { return false; }
    /** Override this in your subclass to change appearance if you vary based
     *  on level.
     * @return Returns true if the level change has caused the appearance of
     * the Drawable to change (that is, it needs to be drawn), else false
     * if it looks the same and there is no need to redraw it since its
     * last level.
     */
    protected boolean onLevelChange(int level) { return false; }
    /**
     * Override this in your subclass to change appearance if you vary based on
     * the bounds.
     */
    protected void onBoundsChange(Rect bounds) {}
    /**
     * Use the current {@link Callback} implementation to have this Drawable
     * redrawn.  Does nothing if there is no Callback attached to the
     * Drawable.
     *
     * @see Callback#invalidateDrawable
     * @see #getCallback()
     * @see #setCallback(Drawable.Callback)
     */
    public void invalidateSelf() {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }
    /**
     * Use the current {@link Callback} implementation to have this Drawable
     * scheduled.  Does nothing if there is no Callback attached to the
     * Drawable.
     *
     * @param what The action being scheduled.
     * @param when The time (in milliseconds) to run.
     *
     * @see Callback#scheduleDrawable
     */
    public void scheduleSelf(Runnable what, long when) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }
    /**
     * Use the current {@link Callback} implementation to have this Drawable
     * unscheduled.  Does nothing if there is no Callback attached to the
     * Drawable.
     *
     * @param what The runnable that you no longer want called.
     *
     * @see Callback#unscheduleDrawable
     */
    public void unscheduleSelf(Runnable what) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }
    /**
     * Bind a {@link Callback} object to this Drawable.  Required for clients
     * that want to support animated drawables.
     *
     * @param cb The client's Callback implementation.
     *
     * @see #getCallback()
     */
    public final void setCallback(Callback cb) {
        mCallback = new WeakReference<Callback>(cb);
    }
    /**
     * Return the current {@link Callback} implementation attached to this
     * Drawable.
     *
     * @return A {@link Callback} instance or null if no callback was set.
     *
     * @see #setCallback(Drawable.Callback)
     */
    public Callback getCallback() {
        if (mCallback != null) {
            return mCallback.get();
        }
        return null;
    }
    /**
     * Implement this interface if you want to create an animated drawable that
     * extends {@link Drawable Drawable}.
     * Upon retrieving a drawable, use
     * {@link Drawable#setCallback(Drawable.Callback)}
     * to supply your implementation of the interface to the drawable; it uses
     * this interface to schedule and execute animation changes.
     */
    public static interface Callback {
        /**
         * Called when the drawable needs to be redrawn.  A view at this point
         * should invalidate itself (or at least the part of itself where the
         * drawable appears).
         *
         * @param who The drawable that is requesting the update.
         */
        public void invalidateDrawable(Drawable who);

        /**
         * A Drawable can call this to schedule the next frame of its
         * animation.
         *
         * @param who The drawable being scheduled.
         * @param what The action to execute.
         * @param when The time (in milliseconds) to run.
         */
        public void scheduleDrawable(Drawable who, Runnable what, long when);

        /**
         * A Drawable can call this to unschedule an action previously
         * scheduled with {@link #scheduleDrawable}.
         *
         * @param who The drawable being unscheduled.
         * @param what The action being unscheduled.
         */
        public void unscheduleDrawable(Drawable who, Runnable what);
    }
    /**
     * Draw in its bounds (set via setBounds) respecting optional effects such
     * as alpha (set via setAlpha) and color filter (set via setColorFilter).
     *
     * @param canvas The canvas to draw into
     */
    public abstract void draw(Canvas canvas);
    /**
     * Return the drawable's bounds Rect. Note: for efficiency, the returned
     * object may be the same object stored in the drawable (though this is not
     * guaranteed), so if a persistent copy of the bounds is needed, call
     * copyBounds(rect) instead.
     * You should also not change the object returned by this method as it may
     * be the same object stored in the drawable.
     *
     * @return The bounds of the drawable (which may change later, so caller
     *         beware). DO NOT ALTER the returned object as it may change the
     *         stored bounds of this drawable.
     *
     * @see #copyBounds()
     * @see #copyBounds(Rect)
     */
    public final Rect getBounds() {
        if (mBounds == ZERO_BOUNDS_RECT) {
            mBounds = new Rect();
        }
        return mBounds;
    }
    /**
     * Return a copy of the drawable's bounds in a new Rect. This returns the
     * same values as getBounds(), but the returned object is guaranteed to not
     * be changed later by the drawable (i.e. it retains no reference to this
     * rect). If the caller already has a Rect allocated, call copyBounds(rect).
     *
     * @return A copy of the drawable's bounds
     */
    public final Rect copyBounds() {
        return new Rect(mBounds.left,mBounds.top,mBounds.right,mBounds.bottom);
    }
    /**
     * Return a copy of the drawable's bounds in the specified Rect (allocated
     * by the caller). The bounds specify where this will draw when its draw()
     * method is called.
     *
     * @param bounds Rect to receive the drawable's bounds (allocated by the
     *               caller).
     */
    public final void copyBounds(Rect bounds) {
        bounds.set(mBounds.left,mBounds.top,mBounds.right,mBounds.bottom);
    }
    /**
     * Specify an alpha value for the drawable. 0 means fully transparent, and
     * 255 means fully opaque.
     */
    public abstract void setAlpha(int alpha);
    /**
     * Specify an optional color filter for the drawable.
     * <p>
     * If a Drawable has a ColorFilter, each output pixel of the Drawable's
     * drawing contents will be modified by the color filter before it is
     * blended onto the render target of a Canvas.
     * </p>
     * <p>
     * Pass {@code null} to remove any existing color filter.
     * </p>
     *
     * @param colorFilter The color filter to apply, or {@code null} to remove the
     *            existing color filter
     */
    public abstract void setColorFilter(ColorMatrix colorFilter);
    /**
     * Return the opacity/transparency of this Drawable.  The returned value is
     * one of the abstract format constants
     */
    public abstract int getOpacity();
}
