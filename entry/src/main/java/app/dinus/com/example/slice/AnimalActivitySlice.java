package app.dinus.com.example.slice;

import app.dinus.com.example.ResourceTable;
import app.dinus.com.loadingdrawable.LoadingView;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.DirectionalLayout.LayoutConfig;
import ohos.agp.components.Text;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;

public class AnimalActivitySlice extends AbilitySlice {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_activity_animal);
        LoadingView fishView = (LoadingView) findComponentById(ResourceTable.Id_fish_view);
        fishView.setLoadingRenderer("FishLoadingRenderer");
        LoadingView ghostsEyeView = (LoadingView) findComponentById(ResourceTable.Id_ghosts_eye_view);
        ghostsEyeView.setLoadingRenderer("GhostsEyeLoadingRenderer");
        fishView.setClickedListener(new Component.ClickedListener(){

            @Override
            public void onClick(Component component) {
                fishView.startAnimation();
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
