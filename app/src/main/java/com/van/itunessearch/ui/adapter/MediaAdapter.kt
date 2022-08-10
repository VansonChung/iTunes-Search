package com.van.itunessearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.van.itunessearch.R
import com.van.itunessearch.databinding.MediaItemBinding
import com.van.itunessearch.viewmodel.MediaInfo

class MediaAdapter : ListAdapter<MediaInfo, MediaAdapter.MediaViewHolder>(DiffCallback) {

    class MediaViewHolder(
        private val binding: MediaItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mediaInfo: MediaInfo) {
            binding.ivCover.load(mediaInfo.imgUrl) { placeholder(R.drawable.loading_animation) }
            binding.tvArtist.text = mediaInfo.artist
            binding.tvTrack.text = mediaInfo.track
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MediaInfo>() {

        override fun areItemsTheSame(oldItem: MediaInfo, newItem: MediaInfo): Boolean {
            // TODO more detail
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MediaInfo, newItem: MediaInfo): Boolean {
            // TODO more detail
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        return MediaViewHolder(
            MediaItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val mediaInfo = getItem(position)
        holder.bind(mediaInfo)
    }
}