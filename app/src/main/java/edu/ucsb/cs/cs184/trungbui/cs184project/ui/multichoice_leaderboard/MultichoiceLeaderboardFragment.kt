package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice_leaderboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.User
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentMultichoiceLeaderboardBinding

class MultichoiceLeaderboardFragment : Fragment() {

    private var _binding: FragmentMultichoiceLeaderboardBinding? = null

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

        _binding = FragmentMultichoiceLeaderboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Fetching the leaderboard list view
        leaderboardListView = root.findViewById<ListView>(R.id.multichoice_leaderboard_list_view)

        database = FirebaseDatabase.getInstance().getReference("users")
        database.keepSynced(true)

        // Adding header to the row
        val header = inflater.inflate(R.layout.leaderboard_header, leaderboardListView, false)
        leaderboardListView.addHeaderView(header)

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val userlist = mutableListOf<User>()

                Log.d("MultichoiceLeaderboardFragment", "onDataChange method")
                data.children.forEach{
                    val multichoiceScore = Integer.parseInt(it.child("multichoiceScore").value.toString())
                    val name: String = it.child("name").value.toString()
                    val email: String = it.child("email").value.toString()
                    val mapScore = Integer.parseInt(it.child("mapScore").value.toString())
                    userlist.add(User(name, mapScore, email, multichoiceScore))
                }

                // SORT THE USER FROM HIGHEST TO LOWEST SCORE
                val sortedUserList = userlist.sortedWith(Comparator { first: User, second: User ->
                    if (first.multichoiceScore != second.multichoiceScore) {
                        second.multichoiceScore - first.multichoiceScore
                    } else {
                        first.name!!.compareTo(second.name!!)
                    }
                })

                // Displaying list view
                if (context != null) {
                    leaderboardListView.adapter = MultichoiceLeaderboardAdapter(requireContext(), sortedUserList)
                }

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