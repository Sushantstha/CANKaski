package com.sarvagya.www.snow.injection.modules;

import android.content.Context;

import com.sarvagya.www.snow.injection.scopes.PerActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {


    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerActivityScope
    public Context context() {
        return context;
    }
}
