package app.dinus.com.example;

import app.dinus.com.example.slice.AnimalActivitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class AnimalActivity extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(AnimalActivitySlice.class.getName());
    }
}
