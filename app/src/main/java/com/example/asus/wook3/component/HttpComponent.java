package com.example.asus.wook3.component;

import com.example.asus.wook3.module.HttpModule;
import com.example.asus.wook3.ui.satin.MainActivity;

import dagger.Component;

/**
 * Created by asus on 2018/5/26.
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(MainActivity mainActivity);
}
