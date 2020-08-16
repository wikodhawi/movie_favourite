package com.stickearn.moviefavourite.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stickearn.moviefavourite.service.database.AppDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseModule().provideSqlChiper() }
    single { DatabaseModule().provideAppDatabase(androidContext(), get()) }
    single { DatabaseModule().provideDummyEntityDao(get()) }
    single { DatabaseModule().providePopularMovieDetailDao(get()) }
}

class DatabaseModule {
    fun provideSqlChiper(): SupportFactory {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(DATABASE_PASSWORD.toCharArray())
        return SupportFactory(passphrase)
    }

    fun provideAppDatabase(context: Context, supportFactory: SupportFactory): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }).openHelperFactory(supportFactory)
            .build()

    fun provideDummyEntityDao(appDatabase: AppDatabase) = appDatabase.dummyEntityDao()
    fun providePopularMovieDetailDao(appDatabase: AppDatabase) = appDatabase.popularMovieDetailDao()

    companion object {
        private const val DATABASE_NAME = "movieFavouriteDb"
        private const val DATABASE_PASSWORD = "movieFavouritePass"
    }
}