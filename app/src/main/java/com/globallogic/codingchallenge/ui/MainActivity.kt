package com.globallogic.codingchallenge.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.globallogic.codingchallenge.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = ""

    }
}
