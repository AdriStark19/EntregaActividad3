package com.example.tema13

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tema13.databinding.FragmentPlaylistDetailBinding

class PlaylistDetailFragment : Fragment() {
    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)

        val playlist: PlaylistResponse.Playlist? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("playlist", PlaylistResponse.Playlist::class.java)
        } else {
            arguments?.getParcelable("playlist")
        }

        val recyclerViewSongs = binding.recyclerViewSongs
        val songAdapter = SongAdapter(playlist)
        binding.tituloPlaylist.text = playlist?.playlistTitulo
        recyclerViewSongs.adapter = songAdapter
        recyclerViewSongs.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewSongs.isNestedScrollingEnabled = false
        binding.irPlaylist.setOnClickListener {
            val fragment = SearchFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
