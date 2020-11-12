package app.dinus.com.example;

import app.dinus.com.example.slice.CircleJumpActivitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class CircleJumpActivity extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(CircleJumpActivitySlice.class.getName());
    }
}
