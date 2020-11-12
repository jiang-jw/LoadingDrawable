package app.dinus.com.example.slice;

import app.dinus.com.example.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.PathMatcher;
import ohos.aafwk.content.Intent;

import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.DirectionalLayout.LayoutConfig;
import ohos.agp.components.Text;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.render.Path;
import ohos.agp.utils.Color;
import ohos.agp.utils.RectFloat;
import ohos.agp.utils.TextAlignment;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainActivitySlice extends AbilitySlice implements Component.ClickedListener {
    static final HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0x1000000, "MY_TAG");

    private Button mBtnGoods;
    private Button mBtnAnimal;
    private Button mBtnScenery;
    private Button mBtnCircleJump;
    private Button mBtnShapeChange;
    private Button mBtnCircleRotate;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_activity_main);

        mBtnGoods = (Button) findComponentById(ResourceTable.Id_goods);
        mBtnAnimal = (Button) findComponentById(ResourceTable.Id_animal);
        mBtnScenery = (Button) findComponentById(ResourceTable.Id_scenery);
        mBtnCircleJump = (Button) findComponentById(ResourceTable.Id_circle_jump);
        mBtnShapeChange = (Button) findComponentById(ResourceTable.Id_shape_change);
        mBtnCircleRotate = (Button) findComponentById(ResourceTable.Id_circle_rotate);

        mBtnGoods.setClickedListener(this);
        mBtnAnimal.setClickedListener(this);
        mBtnScenery.setClickedListener(this);
        mBtnCircleJump.setClickedListener(this);
        mBtnShapeChange.setClickedListener(this);
        mBtnCircleRotate.setClickedListener(this);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
        Intent secondIntent = new Intent();
        // 指定待启动FA的bundleName和abilityName
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName("app.dinus.com.example")
                .withAbilityName(getAbilityName(component.getId()))
                .build();
        secondIntent.setOperation(operation);
        // 通过AbilitySlice的startAbility接口实现启动另一个页面
        startAbility(secondIntent);
    }

    /**
     * 通过按钮的ID取得启动页面的AbilityName
     * @param btnId
     * @return AbilityName
     */
    private String getAbilityName(int btnId){
        String abilityName = "app.dinus.com.example.MainAbility";
        switch (btnId){
            case ResourceTable.Id_shape_change:
                abilityName = "app.dinus.com.example.ShapeChangeActivity";
                break;
            case ResourceTable.Id_goods:
                abilityName = "app.dinus.com.example.GoodsActivity";
                break;
            case ResourceTable.Id_animal:
                abilityName = "app.dinus.com.example.AnimalActivity";
                break;
            case ResourceTable.Id_scenery:
                abilityName = "app.dinus.com.example.SceneryActivity";
                break;
            case ResourceTable.Id_circle_jump:
                abilityName = "app.dinus.com.example.CircleJumpActivity";
                break;
            case ResourceTable.Id_circle_rotate:
                abilityName = "app.dinus.com.example.CircleRotateActivity";
                break;
            default:
                break;
        }
        return abilityName;
    }
}
