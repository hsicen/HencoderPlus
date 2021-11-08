package com.hsicen.a19_constraintlayout.constraint

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a19_constraintlayout.databinding.ActivityConstraintBinding

class ConstraintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConstraintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRelative.setOnClickListener {
            startActivity(
                Intent(this, ConstraintPositionActivity::class.java)
            )
        }

        binding.btnHelper.setOnClickListener {
            startActivity(Intent(this, ConstraintHelperActivity::class.java))
        }
    }
}
