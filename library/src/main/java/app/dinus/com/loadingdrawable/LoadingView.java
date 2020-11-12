package app.dinus.com.loadingdrawable;

import app.dinus.com.loadingdrawable.render.LoadingDrawable;
import app.dinus.com.loadingdrawable.render.LoadingRenderer;
import app.dinus.com.loadingdrawable.render.animal.FishLoadingRenderer;
import app.dinus.com.loadingdrawable.render.animal.GhostsEyeLoadingRenderer;
import app.dinus.com.loadingdrawable.render.circle.jump.CollisionLoadingRenderer;
import app.dinus.com.loadingdrawable.render.circle.jump.DanceLoadingRenderer;
import app.dinus.com.loadingdrawable.render.circle.jump.GuardLoadingRenderer;
import app.dinus.com.loadingdrawable.render.circle.jump.SwapLoadingRenderer;
import app.dinus.com.loadingdrawable.render.circle.rotate.GearLoadingRenderer;
import app.dinus.com.loadingdrawable.render.circle.rotate.LevelLoadingRenderer;
import app.dinus.com.loadingdrawable.render.circle.rotate.MaterialLoadingRenderer;
import app.dinus.com.loadingdrawable.render.circle.rotate.WhorlLoadingRenderer;
import app.dinus.com.loadingdrawable.render.goods.BalloonLoadingRenderer;
import app.dinus.com.loadingdrawable.render.goods.WaterBottleLoadingRenderer;
import app.dinus.com.loadingdrawable.render.scenery.DayNightLoadingRenderer;
import app.dinus.com.loadingdrawable.render.scenery.ElectricFanLoadingRenderer;
import app.dinus.com.loadingdrawable.render.shapechange.CircleBroodLoadingRenderer;
import app.dinus.com.loadingdrawable.render.shapechange.CoolWaitLoadingRenderer;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ProgressBar;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.render.Texture;
import ohos.agp.utils.Color;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.HashMap;
import java.util.Map;

public class LoadingView extends Component {
    private LoadingDrawable mLoadingDrawable;
    static final HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0x1000000, "MY_TAG");
    Context mContext = null;
    public LoadingView(Context context) {
        super(context);
        this.mContext = context;
    }
    private static final Map<String,Integer> LOADING_RENDERERS = new HashMap<String,Integer>();
    static {
        //circle rotate
        LOADING_RENDERERS.put("MaterialLoadingRenderer",0);
        LOADING_RENDERERS.put("LevelLoadingRenderer",1);
        LOADING_RENDERERS.put("WhorlLoadingRenderer",2);
        LOADING_RENDERERS.put("GearLoadingRenderer",3);
        //circle jump
        LOADING_RENDERERS.put("SwapLoadingRenderer",4);
        LOADING_RENDERERS.put("GuardLoadingRenderer",5);
        LOADING_RENDERERS.put("DanceLoadingRenderer",6);
        LOADING_RENDERERS.put("CollisionLoadingRenderer",7);
        //scenery
        LOADING_RENDERERS.put("DayNightLoadingRenderer",8);
        LOADING_RENDERERS.put("ElectricFanLoadingRenderer",9);
        //animal
        LOADING_RENDERERS.put("FishLoadingRenderer",10);
        LOADING_RENDERERS.put("GhostsEyeLoadingRenderer",11);
        //goods
        LOADING_RENDERERS.put("BalloonLoadingRenderer",12);
        LOADING_RENDERERS.put("WaterBottleLoadingRenderer",13);
        //shape change
        LOADING_RENDERERS.put("CircleBroodLoadingRenderer",15);
        LOADING_RENDERERS.put("CoolWaitLoadingRenderer",15);
    }
    public LoadingView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        this.mContext = context;
    }

    public void setLoadingRenderer(String loadingRenderer) {
        HiLog.info(label,"========setLoadingRenderer>>%{public}s.",loadingRenderer);
        int loadingRendererId = LOADING_RENDERERS.get(loadingRenderer);
        LoadingRenderer renderer = null;
        try {
            renderer = LoadingRendererFactory.createLoadingRenderer(mContext, loadingRendererId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLoadingRenderer(renderer);
//        addDrawTask(new DrawTask(){
//            @Override
//            public void onDraw(Component component, Canvas canvas) {
//                try {
//                    int loadingRendererId = LOADING_RENDERERS.get(loadingRenderer);
//                    LoadingRenderer loadingRenderer = LoadingRendererFactory.createLoadingRenderer(mContext, loadingRendererId);
//                    setLoadingRenderer(loadingRenderer,canvas);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
    public void setLoadingRenderer(LoadingRenderer loadingRenderer) {
        HiLog.info(label,">>>>LoadingView>>>>setLoadingRenderer>>>1");
        mLoadingDrawable = new LoadingDrawable(loadingRenderer,this);
        HiLog.info(label,">>>>LoadingView>>>>setLoadingRenderer>>>2"+mLoadingDrawable);
    }

    public void startAnimation() {
        HiLog.info(label,">>>>LoadingView>>>>startAnimation>>>1"+mLoadingDrawable);
        if (mLoadingDrawable != null) {
            HiLog.info(label,">>>>LoadingView>>>>startAnimation>>>1<<<<<<1");
            mLoadingDrawable.start();
            HiLog.info(label,">>>>LoadingView>>>>startAnimation>>>1<<<<<<2");
        }
        HiLog.info(label,">>>>LoadingView>>>>startAnimation>>>2");
    }

    public void stopAnimation() {
        if (mLoadingDrawable != null) {
            mLoadingDrawable.stop();
        }
    }
}
