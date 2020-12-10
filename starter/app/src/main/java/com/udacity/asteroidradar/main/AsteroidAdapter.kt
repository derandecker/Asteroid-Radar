package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.AsteroidItemBinding
import kotlinx.android.synthetic.main.fragment_main.view.*

class AsteroidAdapter() : RecyclerView.Adapter<AsteroidViewHolder>() {

    var asteroids: List<Asteroid> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val withDataBinding: AsteroidItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AsteroidViewHolder.LAYOUT,
            parent,
            false
        )
        return AsteroidViewHolder(withDataBinding)
    }

    override fun getItemCount() = asteroids.size

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.binding.also{
        it.asteroid = asteroids[position]
        }
    }
    }


    class AsteroidViewHolder (val binding: AsteroidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.asteroid_item
    }
}

//class AsteroidDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
//    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
//        return oldItem == newItem
//    }
//
//}