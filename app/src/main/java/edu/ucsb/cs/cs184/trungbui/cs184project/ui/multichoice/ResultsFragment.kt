package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null

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
        val MultichoiceViewModel =
            ViewModelProvider(this).get(MultichoiceViewModel::class.java)
        MultichoiceViewModel.totalQuestions.observe(viewLifecycleOwner) {
            totalQuestions = MultichoiceViewModel.totalQuestions.value.toString()
        }
        MultichoiceViewModel.correctAnswers.observe(viewLifecycleOwner) {
            correctAnswers = MultichoiceViewModel.correctAnswers.value.toString()
        }
//        correctAnswers = requireArguments().getString("correctAnswers").toString()
//        totalQuestions = requireArguments().getString("totalQuestions").toString()

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
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