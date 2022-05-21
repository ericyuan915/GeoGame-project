package edu.ucsb.cs.cs184.trungbui.cs184project.ui.google_maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentGoogleMapsBinding

class GoogleMapsFragment : Fragment() {

    private var _binding: FragmentGoogleMapsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val googleMapsViewModel =
            ViewModelProvider(this).get(GoogleMapsViewModel::class.java)

        _binding = FragmentGoogleMapsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGoogleMaps
        googleMapsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}