package com.example.mynewsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val songsObjects:MutableList<Song> = mutableListOf<Song>()
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        songsObjects.add(Song("Akram","rrrrrrrrrrrrrr"))
        val recyclerView :RecyclerView
        recyclerView=findViewById(R.id.recycler)
        recyclerView.adapter=MyAdapter(songsObjects)
        recyclerView.layoutManager=LinearLayoutManager(this)
    }
}