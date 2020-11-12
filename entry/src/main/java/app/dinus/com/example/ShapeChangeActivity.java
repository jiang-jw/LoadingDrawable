package app.dinus.com.example;

import app.dinus.com.example.slice.ShapeChangeActivitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class ShapeChangeActivity extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(ShapeChangeActivitySlice.class.getName());
    }
}
