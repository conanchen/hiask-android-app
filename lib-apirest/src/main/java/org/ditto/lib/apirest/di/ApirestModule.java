package org.ditto.lib.apirest.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.ditto.lib.apirest.ApirestFascade;
import org.ditto.lib.apirest.GithubService;
import org.ditto.lib.apirest.RxGithubService;
import org.ditto.lib.apirest.util.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amirziarati on 10/4/16.
 */
@Singleton
@Module
public class ApirestModule {


    @Singleton
    @Provides
    ApirestFascade provideApirestFascade(GithubService githubService,RxGithubService rxGithubService) {
        return new ApirestFascade(githubService,rxGithubService);
    }

    @Singleton
    @Provides
    GithubService provideGithubService() {
        // enable logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(client)
                .build()
                .create(GithubService.class);
    }


    @Singleton
    @Provides
    RxGithubService provideRxGithubService() {
        // enable logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();


        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(RxGithubService.class);
    }


} 