package com.sarvagya.www.snow.injection.modules;

import com.sarvagya.www.snow.injection.scopes.PerActivityScope;
import com.sarvagya.www.snow.network.Api;
import com.sarvagya.www.snow.network.ApiInterface;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ApiInterfaceModule {

    public static Retrofit retrofit = null;

    @Provides
    @PerActivityScope
    public ApiInterface apiInterface(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @PerActivityScope
    public Retrofit retrofit(OkHttpClient okHttpClient){

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }
}
