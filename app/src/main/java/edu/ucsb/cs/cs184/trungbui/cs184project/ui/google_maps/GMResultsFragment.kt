package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentGmResultsBinding
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentGoogleMapsBinding
import android.graphics.Color
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import edu.ucsb.cs.cs184.trungbui.cs184project.R

class GMResultsFragment : Fragment() {

    private var _binding: FragmentGmResultsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var userName = "username"
    var totalQuestions = ""
    var correctAnswers = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val GoogleMapsViewModel =
            ViewModelProvider(this).get(GoogleMapsViewModel::class.java)
        GoogleMapsViewModel.totalQuestions.observe(viewLifecycleOwner) {
            totalQuestions = GoogleMapsViewModel.totalQuestions.value.toString()
        }
        GoogleMapsViewModel.correctAnswers.observe(viewLifecycleOwner) {
            correctAnswers = GoogleMapsViewModel.correctAnswers.value.toString()
        }

        _binding = FragmentGmResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.tvName.text = userName
        binding.tvScore.text = "Your Score is $correctAnswers out of $totalQuestions"
        return root

    }

//    override fun onClick(v: View?) {
//
//        when (v) {
//
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}