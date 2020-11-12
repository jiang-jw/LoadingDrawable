package app.dinus.com.example;

import app.dinus.com.example.slice.CircleRotateActivitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class CircleRotateActivity extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(CircleRotateActivitySlice.class.getName());
    }
}
