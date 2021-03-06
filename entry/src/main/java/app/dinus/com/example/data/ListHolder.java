package app.dinus.com.example.data;

import app.dinus.com.example.ResourceTable;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.ListContainer;

public class ListHolder {
    private static final String TAG = "ListHolder";

    private AbilitySlice mSlice;

    private ComponentContainer mRootLayout;

    private ListContainer listContainer;

    private ListItemProvider listItemProvider;

    public ListHolder(AbilitySlice abilitySlice) {
        mSlice = abilitySlice;
        listItemProvider = new ListItemProvider(abilitySlice);
    }

    public ComponentContainer createComponent() {
        Component mainComponent = LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_ability_main, null, false);
        if (!(mainComponent instanceof ComponentContainer)) {
            return null;
        }
        mRootLayout = (ComponentContainer) mainComponent;
        listContainer = (ListContainer) mRootLayout.findComponentById(ResourceTable.Id_list);
        listContainer.setItemProvider(listItemProvider);
        return mRootLayout;
    }
}
