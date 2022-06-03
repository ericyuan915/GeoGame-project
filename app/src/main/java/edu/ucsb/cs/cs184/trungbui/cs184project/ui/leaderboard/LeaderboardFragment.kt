package edu.ucsb.cs.cs184.trungbui.cs184project.ui.leaderboard

import android.content.ContentValues.TAG
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
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.User
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentLeaderboardBinding

class LeaderboardFragment : Fragment() {

    private var _binding: FragmentLeaderboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var leaderboardListView: ListView
    private lateinit var database: DatabaseReference

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

        database = FirebaseDatabase.getInstance().getReference("users")
        database.keepSynced(true)

        // Adding header to the row
        val header = inflater.inflate(R.layout.leaderboard_header, leaderboardListView, false)
        leaderboardListView.addHeaderView(header)

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val userlist = mutableListOf<User>()

                Log.d("LeaderboardFragment", "onDataChange method")
                data.children.forEach{
                    val score = Integer.parseInt(it.child("score").value.toString())
                    val name: String = it.child("name").value.toString()
                    val email: String = it.child("email").value.toString()
                    userlist.add(User(name, score, email))
                }

                // SORT THE USER FROM HIGHEST TO LOWEST SCORE
                val sortedUserList = userlist.sortedWith(Comparator { first: User, second: User ->
                    if (first.score != second.score) {
                        second.score - first.score
                    } else {
                        first.name!!.compareTo(second.name!!)
                    }
                })

                // Displaying list view
                leaderboardListView.adapter = LeaderboardAdapter(requireContext(), sortedUserList)

            }
            override fun onCancelled(databaseError: DatabaseError) {
                // report/log the error
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}