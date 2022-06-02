package edu.ucsb.cs.cs184.trungbui.cs184project.ui.leaderboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.User
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentLeaderboardBinding


class LeaderboardFragment : Fragment() {

    private var _binding: FragmentLeaderboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var leaderboardListView: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(LeaderboardViewModel::class.java)

        _binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        val root: View = binding.root



        // Fetching the leaderboard list view
        leaderboardListView = root.findViewById<ListView>(R.id.leaderboard_list_view)
        Log.d("LeaderboardFragment", leaderboardListView.toString())

        val recipeList = Array(3) { index ->
            User("firstName${index + 1}", "lastName${index + 1}", index + 1, "email${index + 1}")
        }

        val listItems = arrayOfNulls<String>(recipeList.size)

        for (i in 0 until recipeList.size) {
            val recipe = recipeList[i]
            listItems[i] = recipe.email
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listItems)
        leaderboardListView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}