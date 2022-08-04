package com.van.itunessearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.van.itunessearch.apis.RetrofitService
import com.van.itunessearch.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Timber.d("setContentView")
        setContentView(binding.root)

        binding.button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                RetrofitService.iTunesSearchApi.searchMusicByTerm(
                    "michael"
                )
            }
        }
    }
}