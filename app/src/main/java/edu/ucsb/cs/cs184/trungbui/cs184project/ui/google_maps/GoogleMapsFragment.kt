package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentGoogleMapsBinding


class GoogleMapsFragment : Fragment(), View.OnClickListener, OnMapReadyCallback {

    private var _binding: FragmentGoogleMapsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    private var mUserName: String? = null

    private var mMarker1: Marker? = null

    private lateinit var mMap: GoogleMap
    lateinit var googlemapsViewModel: GoogleMapsViewModel

    private val doneQs = mutableListOf<Int>()
    private var QID:Int = 0
    private lateinit var currentQuestion:Question


    var currentDifficulty:Char = 'e'
    var zoomMultiplier:Double = 1.0
    var optionSelected = false
    var questionAnswered = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGoogleMapsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mUserName = "username"
        // END

        googlemapsViewModel = ViewModelProvider(this).get(GoogleMapsViewModel::class.java)

        setFragmentResultListener(R.string.difficulty_multichoice_request_key.toString()) { _, bundle ->
            currentDifficulty = bundle.getChar("difficulty")

        }

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return root

    }

    override fun onClick(v: View?) {

        when (v) {

            binding.tvOptionOne -> {
                if (!questionAnswered) {
                    optionSelected = true
                    selectedOptionView(binding.tvOptionOne, 1)
                }
            }

            binding.tvOptionTwo -> {
                if (!questionAnswered) {
                    optionSelected = true
                    selectedOptionView(binding.tvOptionTwo, 2)
                }
            }

            binding.tvOptionThree -> {
                if (!questionAnswered) {
                    optionSelected = true
                    selectedOptionView(binding.tvOptionThree, 3)
                }
            }

            binding.tvOptionFour -> {
                if (!questionAnswered) {
                    optionSelected = true
                    selectedOptionView(binding.tvOptionFour, 4)
                }
            }

            binding.btnSubmit -> {

                if (!optionSelected) {
                    Toast.makeText(getActivity(),"Please select an option", Toast.LENGTH_SHORT).show();
                }
                else {
                    optionSelected = false
                    questionAnswered = true
                    if (mSelectedOptionPosition == 0) {

                        mCurrentPosition++

                        when {

                            mCurrentPosition <= 10 -> {
                                questionAnswered = false
                                setQuestion()
                            }
                            else -> {
                                // END OF GAME CONDITION

                                // Saving current value to the view model
                                googlemapsViewModel.correctAnswers.value = mCorrectAnswers.toString()
                                googlemapsViewModel.totalQuestions.value = mCurrentPosition.toString()

                                // Passing the correct result to the result fragment
                                val bundle = bundleOf(
                                    Pair("correctAnswers", mCorrectAnswers),
                                    Pair("totalQuestions", mCurrentPosition-1),
                                    Pair("gameDifficulty", currentDifficulty)
                                )
                                setFragmentResult(R.string.googlemaps_result_request_key.toString(), bundle)

                                // Navigating to the result fragment
                                findNavController().navigate(R.id.action_nav_google_maps_to_nav_gm_results)
                            }
                        }
                    } else {

                        // This is to check if the answer is wrong
                        if (currentQuestion!!.correctAnswer != mSelectedOptionPosition) {
                            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                        } else {
                            mCorrectAnswers++
                        }

                        // This is for correct answer
                        answerView(currentQuestion.correctAnswer, R.drawable.correct_option_border_bg)

                        if (mCurrentPosition == 10) {
                            optionSelected = true
                            binding.btnSubmit.text = "FINISH"
                        } else {
                            optionSelected = true
                            binding.btnSubmit.text = "GO TO NEXT QUESTION"
                        }

                        mSelectedOptionPosition = 0
                    }
                }
            }
        }
    }

    /**
     * A function for setting the question to UI components.
     */
    private fun setQuestion() {

        if(currentDifficulty == 'e'){zoomMultiplier = 0.9}
        if(currentDifficulty == 'm'){zoomMultiplier = 1.1}
        if(currentDifficulty == 'h'){zoomMultiplier = 1.3}

        if(mCurrentPosition == 1){
            QID = (0..(mQuestionsList!!.size-1)).shuffled().last()
            currentQuestion = mQuestionsList!!.get(QID)
            doneQs.add(QID)
            doneQs.add(QID)
        }
        else {
            QID = (0..(mQuestionsList!!.size - 1)).shuffled().last()
            currentQuestion = mQuestionsList!!.get(QID)
            while (doneQs.contains(QID)) {
                QID = (0..(mQuestionsList!!.size - 1)).shuffled().last()
                currentQuestion = mQuestionsList!!.get(QID)
            }
            doneQs.add(QID)
        }

        defaultOptionsView()

        if (mCurrentPosition == 10) {
            binding.btnSubmit.text = "FINISH"
        } else {
            binding.btnSubmit.text = "SUBMIT"
        }

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition" + "/" + binding.progressBar.getMax()

        val new_coord = LatLng(currentQuestion.lat, currentQuestion.long)

        mMarker1?.remove()

        mMarker1 = mMap.addMarker(MarkerOptions()
            .position(new_coord)
            .title("Point in the US" + zoomMultiplier.toString()))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new_coord,
            (5.0f * zoomMultiplier).toFloat()
        ))

        binding.tvOptionOne.text = currentQuestion.optionOne
        binding.tvOptionTwo.text = currentQuestion.optionTwo
        binding.tvOptionThree.text = currentQuestion.optionThree
        binding.tvOptionFour.text = currentQuestion.optionFour
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        try {
            val success = googleMap.setMapStyle(
                activity?.let {
                    MapStyleOptions.loadRawResourceStyle(
                        it, R.raw.style_json
                    )
                }
            )
        } catch (e: Resources.NotFoundException) {
        }

        with(mMap.uiSettings) {
            isZoomControlsEnabled = false
            isZoomGesturesEnabled = false
            isScrollGesturesEnabled = false
            isTiltGesturesEnabled = false
            isRotateGesturesEnabled = false
        }

        mQuestionsList = Constants.getQuestions()

        setFragmentResultListener(R.string.difficulty_multichoice_request_key.toString()) { _, bundle ->
            currentDifficulty = bundle.getChar("difficulty")

        }

        setQuestion()
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}