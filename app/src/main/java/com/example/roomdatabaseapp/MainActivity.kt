package com.example.roomdatabaseapp

// https://www.youtube.com/watch?v=yPL13Iwy6oM&list=PLRKyZvuMYSIO0jLgj8g6sADnD0IBaWaw2&index=10&t=1010s
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(applicationContext,
        ContactDatabase::class.java,
        "contactDB").build()

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"Jackie","0418"))
        }

    }

    fun getContactsList(view: android.view.View) {
        database.contactDao().getContact().observe(this, Observer {
            Log.d("Test",it.toString())
        })
    }
}