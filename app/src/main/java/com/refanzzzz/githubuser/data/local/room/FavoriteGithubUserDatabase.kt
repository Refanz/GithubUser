package com.refanzzzz.githubuser.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.refanzzzz.githubuser.data.local.entity.FavoriteGithubUser

@Database(entities = [FavoriteGithubUser::class], version = 1)
abstract class FavoriteGithubUserDatabase : RoomDatabase() {

    abstract fun githubUserDao(): FavoriteGithubUserDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteGithubUserDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteGithubUserDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteGithubUserDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteGithubUserDatabase::class.java,"favorite_user_database")
                        .build()
                }
            }

            return INSTANCE as FavoriteGithubUserDatabase
        }
    }

}