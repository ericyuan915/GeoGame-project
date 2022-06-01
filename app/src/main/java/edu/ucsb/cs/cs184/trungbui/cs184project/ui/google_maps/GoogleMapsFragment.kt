package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
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

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGoogleMapsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mUserName = "username"
        // END

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

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

                            // START
//                            val intent =
//                                Intent(this@QuizQuestionsActivity, ResultActivity::class.java)
//                            intent.putExtra(Constants.USER_NAME, mUserName)
//                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
//                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
//                            startActivity(intent)
//                            finish()
                            // END
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

        val new_coord = LatLng(question.lat, question.long)
        mMap.addMarker(MarkerOptions()
            .position(new_coord)
            .title("Point in the US"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new_coord,6.0f))

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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in CA and move the camera
//        val sydney = LatLng(38.58, -121.49)
//        mMap.addMarker(MarkerOptions()
//            .position(sydney)
//            .title("Marker in CA"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,5.0f))


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