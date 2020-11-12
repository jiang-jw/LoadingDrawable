package app.dinus.com.example;

import app.dinus.com.example.slice.MainActivitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;

public class MainActivity extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainActivitySlice.class.getName());
    }
}
