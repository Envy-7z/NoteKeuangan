package com.wisnu.notekeuangan.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wisnu.notekeuangan.model.Note

//Database annotation to specify the entities and set version
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomDb : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDb? = null

        fun getDatabase(context: Context): NoteRoomDb {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDb::class.java,
                    "note_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate table if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getNoteDao() : NoteDao
}
