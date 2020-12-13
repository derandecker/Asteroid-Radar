package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {

        val activity = requireNotNull(this.activity) {}

        ViewModelProvider(
            this,
            MainViewModelFactory(activity.application)
        ).get(MainViewModel::class.java)
    }

    private var viewModelAdapter: AsteroidAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.asteroids.observe(viewLifecycleOwner, Observer<List<Asteroid>> { asteroids ->
            asteroids?.apply {
                viewModelAdapter?.asteroids = asteroids
            }
            Log.i("livedata change", asteroids[0].codename)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        val manager = LinearLayoutManager(context)
        binding.asteroidRecycler.layoutManager = manager

        viewModelAdapter = AsteroidAdapter()

        //add AsteroidAdapter ListAdapter class in main package and update code here with
        //onclick listener
        binding.asteroidRecycler.adapter = viewModelAdapter

        setHasOptionsMenu(true)

//
//        var asteroidList = mutableListOf<Asteroid>()
//
//        val asteroid = Asteroid(1, "Test1", "8/23/2020", isPotentiallyHazardous = true)
//        val asteroid2 = Asteroid(2, "Test2", "8/23/2020", isPotentiallyHazardous = true)
//        val asteroid3 = Asteroid(3, "Test3", "8/23/2020", isPotentiallyHazardous = false)
//        val asteroid4 = Asteroid(4, "Test4", "8/23/2020", isPotentiallyHazardous = true)
//        val asteroid5 = Asteroid(5, "Test5", "8/23/2020", isPotentiallyHazardous = true)
//        val asteroid6 = Asteroid(6, "Test6", "8/23/2020", isPotentiallyHazardous = false)
//
//        asteroidList.add(asteroid)
//        asteroidList.add(asteroid2)
//        asteroidList.add(asteroid3)
//        asteroidList.add(asteroid4)
//        asteroidList.add(asteroid5)
//        asteroidList.add(asteroid6)
//
//        adapter.submitList(asteroidList)

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
