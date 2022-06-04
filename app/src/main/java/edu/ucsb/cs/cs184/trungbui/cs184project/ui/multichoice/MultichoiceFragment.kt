package edu.ucsb.cs.cs184.trungbui.cs184project.ui.multichoice

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.User
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentMultichoiceBinding


class MultichoiceFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentMultichoiceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private lateinit var firebaseAuth: FirebaseAuth

    private var mUserName: String? = null
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val MultichoiceViewModel =
//            ViewModelProvider(this).get(MultichoiceViewModel::class.java)

        _binding = FragmentMultichoiceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Firebase authentication check
        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        if (firebaseUser == null) {
            findNavController().navigate(R.id.nav_home)
            Toast.makeText(context, "User have not logged in", Toast.LENGTH_SHORT).show()
        } else {

            mUserName = firebaseUser!!.displayName
            // END

            mQuestionsList = Constants.getQuestions()

            setQuestion()
            binding.tvOptionOne.setOnClickListener(this)
            binding.tvOptionTwo.setOnClickListener(this)
            binding.tvOptionThree.setOnClickListener(this)
            binding.tvOptionFour.setOnClickListener(this)
            binding.btnSubmit.setOnClickListener(this)

        }
        return root

    }

    override fun onClick(v: View?) {

        when (v) {

            binding.tvOptionOne -> {

                selectedOptionView(binding.tvOptionOne, 1)
            }

            binding.tvOptionTwo -> {

                selectedOptionView(binding.tvOptionTwo, 2)
            }

            binding.tvOptionThree -> {

                selectedOptionView(binding.tvOptionThree, 3)
            }

            binding.tvOptionFour -> {

                selectedOptionView(binding.tvOptionFour, 4)
            }

            binding.btnSubmit -> {

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {
                            val MultichoiceViewModel =
                                ViewModelProvider(this).get(MultichoiceViewModel::class.java)
                            MultichoiceViewModel.userName.value = "username"
                            MultichoiceViewModel.correctAnswers.value = mCorrectAnswers.toString()
                            MultichoiceViewModel.totalQuestions.value = mCurrentPosition.toString()
                            findNavController().navigate(R.id.action_nav_multichoice_to_nav_results)
                            firebaseAuth = FirebaseAuth.getInstance()
                            val firebaseUser = firebaseAuth.currentUser

//                            val uuid = firebaseUser!!.uid
                            val email = firebaseUser!!.email
                            val name = firebaseUser!!.displayName
                            database = FirebaseDatabase.getInstance().getReference("users")
                            val user = User(name, mCorrectAnswers, email)
                            database.child(name!!).setValue(user)
//                            val ldf = ResultsFragment()
//                            val args = Bundle()
//                            args.putString("correctAnswers", mCorrectAnswers.toString())
//                            args.putString("totalQuestions", mCurrentPosition.toString())
//                            ldf.arguments = args
//                            requireFragmentManager().beginTransaction().add(R.id.action_nav_multichoice_to_nav_results, ldf).commit()

//                            val transection: FragmentTransaction =
//                                requireFragmentManager().beginTransaction()
//                            val mfragment = ResultsFragment()
//                            val bundle = Bundle()
//                            bundle.putString("correctAnswers", mCorrectAnswers.toString())
//                            bundle.putString("totalQuestions", mCurrentPosition.toString())
//                            mfragment.setArguments(bundle) //data being send to SecondFragment
//
//                            transection.replace(R.id.fragment_multichoice, mfragment)
//                            transection.commit()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }

                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        binding.btnSubmit.text = "FINISH"
                    } else {
                        binding.btnSubmit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    /**
     * A function for setting the question to UI components.
     */
    private fun setQuestion() {

        val question =
            mQuestionsList!!.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            binding.btnSubmit.text = "FINISH"
        } else {
            binding.btnSubmit.text = "SUBMIT"
        }

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition" + "/" + binding.progressBar.getMax()

        binding.tvQuestion.text = question.question
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour
    }

    /**
     * A function to set the view of selected option view.
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.selected_option_border_bg
        )
    }

    /**
     * A function to set default options view when the new question is loaded or when the answer is reselected.
     */
    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.default_option_border_bg
            )
        }
    }

    /**
     * A function for answer view which is used to highlight the answer is wrong or right.
     */
    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    requireContext(),
                    drawableView
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}