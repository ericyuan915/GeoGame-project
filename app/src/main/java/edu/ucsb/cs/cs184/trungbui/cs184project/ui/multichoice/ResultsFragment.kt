package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.User
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentResultsBinding
import edu.ucsb.cs.cs184.trungbui.cs184project.ui.home.HomeViewModel

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var gameDifficulty:Char = 'e'

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val resultsViewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Firebase user authentication

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        if (firebaseUser == null) {
            findNavController().navigate(R.id.nav_home)
            Toast.makeText(context, "User has not logged in", Toast.LENGTH_SHORT).show()
        } else {
            val email = firebaseUser!!.email
            val name = firebaseUser!!.displayName!!
            val database = FirebaseDatabase.getInstance().getReference("users")

            // Retrieving the result from the Multichoice fragment
            setFragmentResultListener(R.string.multichoice_result_request_key.toString()) { _, bundle ->
                val correctAnswers = bundle.getInt("correctAnswers")
                val totalQuestions = bundle.getInt("totalQuestions")
                gameDifficulty = bundle.getChar("gameDifficulty")

                Log.d("ResultFragment", "totalQuestions = $totalQuestions")
                Log.d("ResultFragment", "correctAnswers = $correctAnswers")

                var scoreMultiplier = 1
                if(gameDifficulty == 'e'){scoreMultiplier = 1}
                if(gameDifficulty == 'm'){scoreMultiplier = 2}
                if(gameDifficulty == 'h'){scoreMultiplier = 3}

                // Displaying user information onto the screen
                binding.tvName.text = name
                binding.tvScore.text = "Your Score is $correctAnswers out of $totalQuestions"

                resultsViewModel.userName.value = name
                resultsViewModel.resultText.value =
                    "Your Score is $correctAnswers out of $totalQuestions"
                resultsViewModel.displayLastResult.value = true

                // Record user score to the database
                val user = User(name, (correctAnswers*scoreMultiplier), email)
                database.child(name).setValue(user)
            }

            Log.d("ResultFragment", "userName = ${resultsViewModel.userName.value}")
            Log.d("ResultFragment", "text = ${resultsViewModel.resultText.value}")

            // Displaying user information onto the screen
            if (resultsViewModel.displayLastResult.value!!) {
                binding.tvName.text = resultsViewModel.userName.value
                binding.tvScore.text = resultsViewModel.resultText.value
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}