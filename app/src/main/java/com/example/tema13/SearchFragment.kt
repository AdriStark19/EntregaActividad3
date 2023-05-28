package com.example.tema13
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tema13.databinding.FragmentSearchBinding
import com.google.gson.Gson
import java.io.IOException

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerView
        val json = readJsonFromFile("playlist.json")
        val playlistResponse = Gson().fromJson(json, PlaylistResponse::class.java)
        val playlists = playlistResponse.data

        val adapterPlaylist = SearchAdapter(playlists) { playlist ->
            val fragment = PlaylistDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable("playlist", playlist)
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit()

        }
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapterPlaylist
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun readJsonFromFile(fileName: String): String {
        var json = ""
        try {
            val bufferedReader = requireContext().assets.open(fileName).bufferedReader()
            json = bufferedReader.use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }
}

