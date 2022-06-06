package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentGmDifficultyBinding


class GMDifficultyFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentGmDifficultyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var selectedDifficulty:Char = '\u0000'

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//

        _binding = FragmentGmDifficultyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Firebase authentication check
        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        // Redirect the user to the login page if they have not logged in
        if (firebaseUser == null) {
            findNavController().navigate(R.id.nav_home)
            Toast.makeText(context, "User has not logged in", Toast.LENGTH_SHORT).show()
        } else {
            binding.tvText.text = "Select your difficulty:"
            binding.tvOptionEasy.text = "Easy"
            binding.tvOptionMedium.text = "Medium"
            binding.tvOptionDifficult.text = "Difficult"

            binding.tvOptionEasy.setOnClickListener(this)
            binding.tvOptionMedium.setOnClickListener(this)
            binding.tvOptionDifficult.setOnClickListener(this)
            binding.btnStart.setOnClickListener(this)
        }

        return root

    }

    override fun onClick(v: View?) {
        when(v){
            binding.tvOptionEasy -> {

                selectedOptionView(binding.tvOptionEasy, 1)
                selectedDifficulty = 'e'
            }

            binding.tvOptionMedium -> {

                selectedOptionView(binding.tvOptionMedium, 1)
                selectedDifficulty = 'm'
            }

            binding.tvOptionDifficult -> {

                selectedOptionView(binding.tvOptionDifficult, 1)
                selectedDifficulty = 'h'
            }

            binding.btnStart -> {
                val bundle = bundleOf(
                    Pair("difficulty", selectedDifficulty)
                )
                setFragmentResult(R.string.difficulty_multichoice_request_key.toString(), bundle)

                // Navigating to the result fragment
                findNavController().navigate(R.id.action_GMDifficultyFragment_to_nav_google_maps)

            }

        }

    }//end of onclick

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.selected_option_border_bg
        )
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionEasy)
        options.add(1, binding.tvOptionMedium)
        options.add(2, binding.tvOptionDifficult)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.default_option_border_bg
            )
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}