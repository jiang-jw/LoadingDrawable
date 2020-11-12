package app.dinus.com.example.slice;

import app.dinus.com.example.ResourceTable;
import app.dinus.com.loadingdrawable.LoadingView;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.DirectionalLayout.LayoutConfig;
import ohos.agp.components.Text;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;

public class GoodsActivitySlice extends AbilitySlice {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_activity_goods);
        LoadingView balloonView = (LoadingView) findComponentById(ResourceTable.Id_balloon_view);
        balloonView.setLoadingRenderer("BalloonLoadingRenderer");
        LoadingView waterBottleView = (LoadingView) findComponentById(ResourceTable.Id_water_bottle_view);
        waterBottleView.setLoadingRenderer("WaterBottleLoadingRenderer");
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
