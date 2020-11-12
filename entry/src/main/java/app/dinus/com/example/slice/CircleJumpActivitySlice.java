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

public class CircleJumpActivitySlice extends AbilitySlice {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_activity_circle_jump);
        LoadingView collisionView = (LoadingView) findComponentById(ResourceTable.Id_collision_view);
        collisionView.setLoadingRenderer("CollisionLoadingRenderer");
        LoadingView swapView = (LoadingView) findComponentById(ResourceTable.Id_swap_view);
        swapView.setLoadingRenderer("SwapLoadingRenderer");
        LoadingView guardView = (LoadingView) findComponentById(ResourceTable.Id_guard_view);
        guardView.setLoadingRenderer("GuardLoadingRenderer");
        LoadingView danceView = (LoadingView) findComponentById(ResourceTable.Id_dance_view);
        danceView.setLoadingRenderer("DanceLoadingRenderer");
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
