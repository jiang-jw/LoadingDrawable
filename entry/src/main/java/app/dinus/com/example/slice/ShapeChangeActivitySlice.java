package app.dinus.com.example.slice;

import app.dinus.com.example.ResourceTable;
import app.dinus.com.loadingdrawable.LoadingView;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

import ohos.agp.components.*;
import ohos.agp.components.DirectionalLayout.LayoutConfig;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class ShapeChangeActivitySlice extends AbilitySlice {
    static final HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0x1000000, "MY_TAG");
    private DirectionalLayout myLayout = new DirectionalLayout(this);

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_activity_shape_change);
        LoadingView  circleBroodView = (LoadingView) findComponentById(ResourceTable.Id_circle_brood_view);
        circleBroodView.setLoadingRenderer("CircleBroodLoadingRenderer");
        LoadingView  coolWaitView = (LoadingView) findComponentById(ResourceTable.Id_cool_wait_view);
        coolWaitView.setLoadingRenderer("CoolWaitLoadingRenderer");
        coolWaitView.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                HiLog.info(label,">>>>>aaaaaaaaaaaa");
                coolWaitView.startAnimation();
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
