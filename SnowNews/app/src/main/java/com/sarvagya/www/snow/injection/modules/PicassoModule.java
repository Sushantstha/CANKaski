package com.sarvagya.www.snow.injection.modules;

import android.content.Context;

import com.sarvagya.www.snow.injection.scopes.PerActivityScope;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = {NetworkModule.class, ContextModule.class})
public class PicassoModule {

    @Provides
    @PerActivityScope
    public Picasso picasso(Context context, OkHttpClient okHttpClient){
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }
}
