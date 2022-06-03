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

//        database = FirebaseDatabase.getInstance().getReference("users")
//        val user = User("Eric", "Yuan", 10, "hi@gmail.com")
//        database.child("1").setValue(user)
//
        database = FirebaseDatabase.getInstance().getReference("users")
        database.keepSynced(true)
        val eric = database.child("users/1")
//        database.addChildEventListener(object : ChildEventListener {
//            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
//                Log.d(TAG, "onChildAdded:" + dataSnapshot.key!!)
//
//                // A new comment has been added, add it to the displayed list
////                val comment = dataSnapshot.getValue<Comment>()
//
//            }
//        })
        val userList: List<User> = List(20) { index ->
            User("firstName${index + 1}", 20 - index, "email${index + 1}")
        }
//        val userlistTest: MutableList<User> = emptyList().toMutableList<User>()
        val userlist1 = mutableListOf<User>()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                // do something with data
//                Log.v("fb", data.children.toString())
//                var model = data.getValue(User::class.java)
//                Log.v("fb", model.toString())
//                userlist1.add(model as User)

//                Log.v("hi", data.children.value.toString())
//                for (snapshot in data){
//                    var model = snapshot.getValue(User::class.java)
//                    Log.v("fb", model.toString())
//                    userlist1.add(model as User)
//                }
                val user = data.getValue()
                Log.v("fb", data.key + ": " + data.value)
                Log.v("hi", user.toString())
//                Log.v("test", user?.name.toString())
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // report/log the error
            }
        })

        // [TODO]: INSERT API CALL TO FETCH ALL USERS INFO
//        val userList: List<User> = List(20) { index ->
//            User("firstName${index + 1}", 20 - index, "email${index + 1}")
//        }

        // SORT THE USER FROM HIGHEST TO LOWEST SCORE
        val sortedUserList = userList.sortedWith(Comparator { first: User, second: User ->
            if (first.score != second.score) {
                second.score - first.score
            } else {
                first.name!!.compareTo(second.name!!)
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