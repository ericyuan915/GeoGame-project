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

        // [TODO]: INSERT API CALL TO FETCH ALL USERS INFO
        val userList: List<User> = List(20) { index ->
            User("firstName${index + 1}", "lastName${index + 1}", 20 - index, "email${index + 1}")
        }

        // SORT THE USER FROM HIGHEST TO LOWEST SCORE
        val sortedUserList = userList.sortedWith(Comparator { first: User, second: User ->
            if (first.score != second.score) {
                second.score - first.score
            } else {
                first.firstName.compareTo(second.firstName)
            }
        })

        // Adding header to the row
        val header = inflater.inflate(R.layout.leaderboard_header, leaderboardListView, false)
        leaderboardListView.addHeaderView(header)

        // Displaying list view
        leaderboardListView.adapter = LeaderboardAdapter(requireContext(), sortedUserList)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}