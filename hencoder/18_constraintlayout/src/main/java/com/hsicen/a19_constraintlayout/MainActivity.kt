package com.hsicen.a19_constraintlayout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a19_constraintlayout.constraint.ConstraintActivity
import com.hsicen.a19_constraintlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnConstraint.setOnClickListener {
            startActivity(
                Intent(this, ConstraintActivity::class.java)
            )
        }

        binding.btnMotion.setOnClickListener {
            startActivity(Intent(this, MotionActivity::class.java))
        }
    }
}
