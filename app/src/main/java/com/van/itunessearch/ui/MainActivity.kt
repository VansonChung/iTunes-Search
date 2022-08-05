package com.van.itunessearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.van.itunessearch.R
import com.van.itunessearch.databinding.ActivityMainBinding
import com.van.itunessearch.ui.adapter.ViewPager2Adapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewPager2: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager2 = binding.viewPager2
        bottomNavigationView = binding.bottomNavigationView

        val fragments = ArrayList<Fragment>()
        fragments.add(MusicFragment())
        fragments.add(MovieFragment())
        val viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2Adapter.setFragments(fragments)

        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        // let MovieFragment preload
        viewPager2.offscreenPageLimit = 2
        viewPager2.adapter = viewPager2Adapter
        viewPager2.registerOnPageChangeCallback(onPageChangeCallback)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                // use map ?!
                R.id.nav_music -> viewPager2.setCurrentItem(0, true)
                R.id.nav_movie -> viewPager2.setCurrentItem(1, true)
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2.unregisterOnPageChangeCallback(onPageChangeCallback)
    }

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            bottomNavigationView.menu.getItem(position).isChecked = true
        }
    }
}