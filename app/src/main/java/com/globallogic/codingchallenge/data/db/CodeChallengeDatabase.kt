package com.globallogic.codingchallenge.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.globallogic.codingchallenge.data.db.entity.Item

@Database(entities = [(Item::class)], version = 1)
abstract class CodeChallengeDatabase : RoomDatabase(){
    abstract fun itemsDao() : ItemDao

    companion object {
        @Volatile private var  instance: CodeChallengeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                    CodeChallengeDatabase::class.java,
                    "codeChallengeEntries.db")
                    .build()
    }
}