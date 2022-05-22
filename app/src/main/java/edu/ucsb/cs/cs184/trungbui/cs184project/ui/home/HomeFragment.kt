package edu.ucsb.cs.cs184.trungbui.cs184project.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.ActivityMainBinding
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    // constants
    private companion object {
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configuring the google Sign In
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(R.string.your_web_client_id.toString())
            .requestEmail() // we only need from google account
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)

        // Init Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        // Google SignIn Button, click to begin Google SignIn
        val googleSignInButton: SignInButton = binding.googleSignInBtn
        binding.googleSignInBtn.setOnClickListener {
            Log.d(TAG, "begin Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }

        return root
    }

    private fun checkUser() {
        // Check if the user is logged in or not
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) {
            // User is already logged in
            // start activity

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d(TAG, "inside onActivity result")
        // Result return from launching the Intent from googleSignInAPI
        if (requestCode == RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google SignIn Intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google SignIn success, now auth with Firebase
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            } catch (e: Exception) {
                // Failed Google Sign In
                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google account")

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Login success")

                // Get logged in user
                val firebaseUser = firebaseAuth.currentUser

                // Get user information
                val uuid = firebaseUser!!.uid
                val email = firebaseUser!!.email

                Log.d(TAG, "firebaseAuthWithGoogleAccount - uid: ${uuid}")
                Log.d(TAG, "firebaseAuthWithGoogleAccount - email: ${email}")

                // Check if the user is new or existing
                if (authResult.additionalUserInfo!!.isNewUser) {
                    // User is new - account created
                    Log.d(TAG, "firebaseAuthWithGoogleAccout: Account created: ${email}")


                } else {
                    // Exisiting user
                    Log.d(TAG, "firebaseAuthWithGoogleAccount - existing user")
                }

                // Start profile activity


            }
            .addOnFailureListener { e ->
                // Login failed
                Log.d(TAG, "FirebaseAuthWithGoogleAccout: Login failed due to ${e.message}")

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}