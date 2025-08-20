package com.example.noirpad.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

// Here getInstance provide the instance od db if not created -it creates ,
//if created already then provide and ensure only once instance of db instance
@Database(entities = [Note::class], version = 1)
abstract class NoteDB : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDB? = null
        fun getInstance(context: Context): NoteDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = databaseBuilder(
                        context = context.applicationContext,
                        NoteDB::class.java,
                        "notes_db"
                    ).build()
                }
                INSTANCE = instance
                return instance

            }
        }

    }
}
//First time you call getInstance(), there’s no database yet → so it creates it and saves it in INSTANCE.
//
//Next time you call getInstance(), it just returns the already created INSTANCE instead of making a new one.
//
//The synchronized(this) makes sure two threads don’t create two databases at the same time.
//
//The @Volatile makes sure if one thread updates INSTANCE, other threads immediately see that update.