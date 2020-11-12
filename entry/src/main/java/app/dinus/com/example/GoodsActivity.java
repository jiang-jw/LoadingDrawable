package app.dinus.com.example;

import app.dinus.com.example.slice.GoodsActivitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class GoodsActivity extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(GoodsActivitySlice.class.getName());
    }
}
