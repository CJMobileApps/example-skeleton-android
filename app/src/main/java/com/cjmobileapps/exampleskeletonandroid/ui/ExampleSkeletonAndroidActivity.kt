package com.cjmobileapps.exampleskeletonandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cjmobileapps.exampleskeletonandroid.R
import com.cjmobileapps.exampleskeletonandroid.databinding.ActivityExampleSkeletonAndroidBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExampleSkeletonAndroidActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleSkeletonAndroidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_skeleton_android)

        binding = ActivityExampleSkeletonAndroidBinding.inflate(layoutInflater)
    }
}
