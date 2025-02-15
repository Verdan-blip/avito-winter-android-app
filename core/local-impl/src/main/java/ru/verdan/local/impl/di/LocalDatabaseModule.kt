package ru.verdan.local.impl.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.verdan.local.impl.database.AppDatabase

@Module
internal class LocalDatabaseModule {

    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = AppDatabase::class.java,
            name = "films-database"
        ).build()
    }
}
