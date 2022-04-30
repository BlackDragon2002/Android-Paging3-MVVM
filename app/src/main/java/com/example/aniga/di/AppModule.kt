package com.example.aniga.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.aniga.api.ApiService
import com.example.aniga.room.AnimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.concurrent.TaskRunner.Companion.logger
import okhttp3.internal.http2.Http2Reader.Companion.logger
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Singleton
//    @Provides
//    fun provideMigration():Migration=object :Migration(1,2){
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("ALTER TABLE RemoteKey DROP COLUMN prev")
//        }
//
//    }

    @Singleton
    @Provides
    fun provideAnimeDb(@ApplicationContext context: Context,
    ):AnimeDatabase =
        Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "ANIME_DATABASE"
        )

            .build()

    @Singleton
    @Provides
    fun provideAnimeDao(db:AnimeDatabase)=db.animeDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDao(db:AnimeDatabase)=db.remoteKeyDao()

    @Singleton
    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor=
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d("Api",it)})


    @Singleton
    @Provides
    fun provideClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient=
        OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://kitsu.io/api/edge/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit):ApiService=
        retrofit.create(ApiService::class.java)


}