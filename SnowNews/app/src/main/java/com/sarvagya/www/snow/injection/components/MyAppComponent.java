package com.sarvagya.www.snow.injection.components;

import com.sarvagya.www.snow.activities.MainActivity;
import com.sarvagya.www.snow.injection.modules.ApiInterfaceModule;
import com.sarvagya.www.snow.injection.modules.PicassoModule;
import com.sarvagya.www.snow.injection.scopes.PerActivityScope;

import dagger.Component;

@PerActivityScope
@Component(modules = {ApiInterfaceModule.class, PicassoModule.class})
public interface MyAppComponent {

    void injectMainActivity(MainActivity mainActivity);
}
