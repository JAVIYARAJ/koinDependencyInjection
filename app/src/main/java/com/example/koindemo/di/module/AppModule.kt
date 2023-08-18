package com.example.koindemo.di.module

import android.content.Context
import com.example.koindemo.App
import com.example.koindemo.data.api.ApiHelper
import com.example.koindemo.data.api.ApiHelperImpl
import com.example.koindemo.data.api.ApiService
import com.example.koindemo.data.api.MainViewModel
import com.example.koindemo.data.api.repository.MainRepository
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import org.koin.dsl.module

//private fun provideNetworkHelper(context: Context) = context

private fun provideBaseUrl(): String = "https://jsonplaceholder.typicode.com/"

private fun provideNetworkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {

    //debug version
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()

} else {
    //release version
    OkHttpClient.Builder().build()
}

private fun provideNetworkClient(okHttpClient: OkHttpClient, base_url: String): Retrofit {
    return Retrofit.Builder().baseUrl(base_url).client(okHttpClient).addConverterFactory(
        GsonConverterFactory.create()
    ).addCallAdapterFactory(ApiResponseCallAdapterFactory.create()).build()
}

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)


//main app di configuration
val appModule = module {

    single { App.mInstance.applicationContext }
    single { provideNetworkHttpClient() } //interceptor
    single { provideNetworkClient(get(), provideBaseUrl()) } //retrofit instance
    single { provideApiService(get()) } // retrofit client instance

    //single { provideNetworkHelper(androidContext()) }  //application instance

    single<ApiHelper> { ApiHelperImpl(get()) } // instance of api helper class

}

//app repo di configuration
val repoModule = module {
    single { MainRepository(get()) }
}

//app view model di configuration
val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

