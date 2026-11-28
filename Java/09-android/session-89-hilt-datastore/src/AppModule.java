// AppModule.java — Hilt module برای dependencies
// نیازمند Android SDK
package com.example.myapp;

import android.content.Context;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public AppDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app_database")
            .build();
    }

    @Provides
    @Singleton
    public ContactDao provideContactDao(AppDatabase db) {
        return db.contactDao();
    }
}
