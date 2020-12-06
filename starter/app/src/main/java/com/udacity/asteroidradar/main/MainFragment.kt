package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val manager = LinearLayoutManager(activity)
        binding.asteroidRecycler.layoutManager = manager

        //add AsteroidAdapter ListAdapter class in main package and update code here with
        //onclick listener
        val adapter = AsteroidAdapter()
        binding.asteroidRecycler.adapter = adapter

        setHasOptionsMenu(true)


        var asteroidList = mutableListOf<Asteroid>()

        val asteroid = Asteroid(1, "Test1", "8/23/2020", isPotentiallyHazardous = true)
        val asteroid2 = Asteroid(2, "Test2", "8/23/2020", isPotentiallyHazardous = true)
        val asteroid3 = Asteroid(3, "Test3", "8/23/2020", isPotentiallyHazardous = false)
        val asteroid4 = Asteroid(4, "Test4", "8/23/2020", isPotentiallyHazardous = true)
        val asteroid5 = Asteroid(5, "Test5", "8/23/2020", isPotentiallyHazardous = true)
        val asteroid6 = Asteroid(6, "Test6", "8/23/2020", isPotentiallyHazardous = false)

        asteroidList.add(asteroid)
        asteroidList.add(asteroid2)
        asteroidList.add(asteroid3)
        asteroidList.add(asteroid4)
        asteroidList.add(asteroid5)
        asteroidList.add(asteroid6)

        adapter.submitList(asteroidList)

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
