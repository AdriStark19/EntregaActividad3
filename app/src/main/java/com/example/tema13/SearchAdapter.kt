package com.example.tema13

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tema13.PlaylistResponse.Playlist
import com.example.tema13.databinding.ItemPlaylistBinding
import com.squareup.picasso.Picasso

class SearchAdapter(private val playlists: List<Playlist>, private val onItemClick: (Playlist) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.bind(playlist)
        holder.itemView.setOnClickListener { onItemClick(playlist) }
    }

    override fun getItemCount(): Int {
        return playlists.size
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(playlist: Playlist) {
            binding.titleTextView.text = playlist.playlistTitulo
            binding.subtitleTextView.text = "${playlist.numberOfFollowers} SEGUIDORES"
            Picasso.get().load(playlist.imageUrl).into(binding.imageView)
        }
    }
}
