package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.User
import edu.ucsb.cs.cs184.trungbui.cs184project.ui.home.HomeViewModel
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentGmResultsBinding
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentGoogleMapsBinding
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.database.FirebaseDatabase

class GMResultsFragment : Fragment() {

    private var _binding: FragmentGmResultsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var gameDifficulty:Char = 'e'

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val gmResultsViewModel = ViewModelProvider(this).get(GMResultsViewModel::class.java)

        _binding = FragmentGmResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

        if (firebaseUser == null) {
            findNavController().navigate(R.id.nav_home)
            Toast.makeText(context, "User has not logged in", Toast.LENGTH_SHORT).show()
        } else {
            val email = firebaseUser!!.email
            val name = firebaseUser!!.displayName!!
            val database = FirebaseDatabase.getInstance().getReference("users")

            // Retrieving the result from the Google map fragment
            setFragmentResultListener(R.string.googlemaps_result_request_key.toString()) { _, bundle ->
                val correctAnswers = bundle.getInt("correctAnswers")
                val totalQuestions = bundle.getInt("totalQuestions")
                gameDifficulty = bundle.getChar("gameDifficulty")

                Log.d("GMResultsFragment", "totalQuestions = $totalQuestions")
                Log.d("GMResultsFragment", "correctAnswers = $correctAnswers")

                var scoreMultiplier = 1
                if(gameDifficulty == 'e'){scoreMultiplier = 1}
                if(gameDifficulty == 'm'){scoreMultiplier = 2}
                if(gameDifficulty == 'h'){scoreMultiplier = 3}

                // Displaying user information onto the screen
                binding.tvName.text = name
                binding.tvScore.text = "Your Score is $correctAnswers out of $totalQuestions"

                gmResultsViewModel.userName.value = name
                gmResultsViewModel.resultText.value =  "Your Score is $correctAnswers out of $totalQuestions"
                gmResultsViewModel.displayLastResult.value = true

                // Retrieve the user current multichoiceScore to persist the data
                database.child(name).get().addOnSuccessListener {
                    val multichoiceScore = Integer.parseInt(it.child("multichoiceScore").value.toString())
                    val user = User(name, (correctAnswers*scoreMultiplier), email, multichoiceScore)
                    // Record user score to the database
                    database.child(name).setValue(user)
                }.addOnFailureListener{
                    Log.e("GMResultsFragment", "Error getting data", it)
                }

            }

            Log.d("GMResultsFragment", "userName = ${gmResultsViewModel.userName.value}")
            Log.d("GMResultsFragment", "text = ${gmResultsViewModel.resultText.value}")

            // Displaying user information onto the screen
            if (gmResultsViewModel.displayLastResult.value!!) {
                binding.tvName.text = gmResultsViewModel.userName.value
                binding.tvScore.text = gmResultsViewModel.resultText.value
            }

            binding.startOverBtnGM.setOnClickListener() {
                findNavController().navigate(R.id.nav_gm_difficulty)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}