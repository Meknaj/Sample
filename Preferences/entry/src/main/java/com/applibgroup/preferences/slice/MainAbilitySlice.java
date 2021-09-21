package com.applibgroup.preferences.slice;

import com.applibgroup.preferences.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Switch;
import ohos.agp.window.dialog.ToastDialog;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

public class MainAbilitySlice extends AbilitySlice {
    Switch switch1;
    Button save;
    public static final String SHARED_PREFS = "preferences";
    public static final String SWITCH1 = "switch1";
    public boolean switchOnOff;
    Preferences prefs;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initComponents();
        loadData();
        updateViews();
    }

    public void initComponents() {
        switch1 = (Switch) findComponentById(ResourceTable.Id_switch1);
        save = (Button) findComponentById(ResourceTable.Id_save);
        save.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                save();
            }
        });
    }

    public void save() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        prefs = dbHelper.getPreferences(SHARED_PREFS);
        prefs.putBoolean(SWITCH1, switch1.isChecked());
        new ToastDialog(this).setText("Data saved").show();
    }

    public void loadData() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        prefs = dbHelper.getPreferences(SHARED_PREFS);
        switchOnOff = prefs.getBoolean(SWITCH1, false);
    }

    public void updateViews() {
        switch1.setChecked(switchOnOff);
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
