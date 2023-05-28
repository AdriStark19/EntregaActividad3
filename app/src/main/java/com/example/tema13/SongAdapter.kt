package com.example.tema13
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tema13.databinding.ItemSongsBinding
import com.squareup.picasso.Picasso


class SongAdapter(private val playlist: PlaylistResponse.Playlist?) :
    RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        playlist?.let {
            val song = it.songs[position]
            holder.bind(song)
        }
    }

    override fun getItemCount(): Int {
        return playlist?.songs?.size ?: 0
    }

    inner class SongViewHolder(private val binding: ItemSongsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(song: PlaylistResponse.Playlist.Song) {
            playlist?.let {
                // Acceder a los datos de la playlist
                Picasso.get().load(it.imageUrl).into(binding.imageSongView)
                // Configurar los datos en las vistas de cada elemento de canción
                binding.songTitleTextView.text = song.name
                binding.artistTextView.text = song.artist

                // Modificamos los colores según la canción esté en favoritos o no
                val fondoOscuro = ContextCompat.getColor(binding.root.context, R.color.fondoOscuro)

                if (song.favorito) {
                    val backgroundColor = ContextCompat.getColor(binding.root.context, R.color.white)
                    val textColor = ContextCompat.getColor(binding.root.context, R.color.black)

                    binding.cancion.setCardBackgroundColor(backgroundColor)
                    binding.songTitleTextView.setTextColor(textColor)
                    binding.artistTextView.setTextColor(textColor)
                    binding.iconView.setColorFilter(textColor)
                    binding.darMegusta.setColorFilter(textColor)
                    binding.button2.setColorFilter(textColor)
                    binding.enlace.setColorFilter(textColor)
                } else {
                    val backgroundColor = fondoOscuro
                    val textColor = ContextCompat.getColor(binding.root.context, R.color.white)
                    binding.cancion.setCardBackgroundColor(backgroundColor)
                    binding.songTitleTextView.setTextColor(textColor)
                    binding.artistTextView.setTextColor(textColor)
                    binding.iconView.setColorFilter(textColor)
                    binding.darMegusta.setColorFilter(textColor)
                    binding.button2.setColorFilter(textColor)
                    binding.enlace.setColorFilter(textColor)
                }

                binding.darMegusta.setOnClickListener {
                    song.favorito = !song.favorito
                    updateSongItem(bindingAdapterPosition)
                    val songTitle = song.name
                    val message = if (song.favorito) {
                        "$songTitle se ha añadido a favoritos"
                    } else {
                        "$songTitle se ha quitado de favoritos"
                    }
                    Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
                }



                binding.enlace.setOnClickListener {
                    openSongUrl(song.url)
                }
            }
        }

        private fun updateSongItem(position: Int) {
            notifyItemChanged(position)
        }

        private fun openSongUrl(url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            binding.root.context.startActivity(intent)
        }
    }
}

