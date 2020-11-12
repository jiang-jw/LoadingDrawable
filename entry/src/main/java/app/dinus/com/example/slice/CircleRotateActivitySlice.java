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

public class CircleRotateActivitySlice extends AbilitySlice {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_activity_circle_rotate);
        LoadingView whorlView = (LoadingView) findComponentById(ResourceTable.Id_whorl_view);
        whorlView.setLoadingRenderer("WhorlLoadingRenderer");
        LoadingView materialView = (LoadingView) findComponentById(ResourceTable.Id_material_view);
        materialView.setLoadingRenderer("MaterialLoadingRenderer");
        LoadingView gearView = (LoadingView) findComponentById(ResourceTable.Id_gear_view);
        gearView.setLoadingRenderer("GearLoadingRenderer");
        LoadingView levelView = (LoadingView) findComponentById(ResourceTable.Id_level_view);
        levelView.setLoadingRenderer("LevelLoadingRenderer");
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
