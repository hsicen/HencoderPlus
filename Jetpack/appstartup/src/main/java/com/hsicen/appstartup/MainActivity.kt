package com.hsicen.appstartup

import androidx.appcompat.app.AppCompatActivity
import androidx.startup.AppInitializer

class MainActivity : AppCompatActivity() {

  fun initAppContext() {
    AppInitializer.getInstance(this)
      .initializeComponent(InitializationA::class.java)
  }
}