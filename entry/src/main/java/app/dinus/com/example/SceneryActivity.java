package app.dinus.com.example;

import app.dinus.com.example.slice.SceneryActivitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class SceneryActivity extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SceneryActivitySlice.class.getName());
    }
}
