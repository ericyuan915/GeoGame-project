package edu.ucsb.cs.cs184.trungbui.cs184project.ui.home

import android.content.Intent
import android.graphics.Insets.add
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.User
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.ActivityMainBinding
import edu.ucsb.cs.cs184.trungbui.cs184project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference


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
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configuring the google Sign In
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail() // we only need from google account
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        // Google SignIn Button, click to begin Google SignIn
        binding.googleSignInBtn.setOnClickListener {
            Log.d(TAG, "begin Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }

        binding.logoutButton.setOnClickListener {
            Log.d(TAG, "logout on click listener")
            firebaseAuth.signOut()
            checkUser()
        }

        return root
    }

    private fun checkUser() {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

        if (firebaseUser == null) {
            // User has not logged in
            Log.d(TAG, "checkUser - firebase user is null")
            homeViewModel.setFirebaseUser(firebaseUser)
            homeViewModel.setLogoutBtnStatus(false)
            homeViewModel.setLoginBtnStatus(true)
            homeViewModel.setUserStatusText("No user has logged in")
        } else {
            // User logged in
            Log.d(TAG, "checkUser - firebase user is not null")
            homeViewModel.setFirebaseUser(firebaseUser)
            homeViewModel.setLogoutBtnStatus(true)
            homeViewModel.setLoginBtnStatus(false)
            homeViewModel.setUserStatusText("Welcome! ${homeViewModel.firebaseUser.value!!.email}")
        }
        binding.googleSignInBtn.isEnabled = homeViewModel.loginBtnStatus.value!!
        binding.logoutButton.isEnabled = homeViewModel.logoutBtnStatus.value!!
        binding.userStatusText.text = homeViewModel.userStatusText.value
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result return from launching the Intent from googleSignInAPI
        if (requestCode == RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google SignIn Intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                 // Google SignIn success, now auth with Firebase
                val account = accountTask.getResult(ApiException::class.java)
                Log.d(TAG, "User has successfully logged in")
                firebaseAuthWithGoogleAccount(account)
            } catch (e: Exception) {
                // Failed Google Sign In
                Log.d(TAG, "onActivityResult-error: ${e.message}")
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
                val name = firebaseUser!!.displayName

                Log.d(TAG, "firebaseAuthWithGoogleAccount - uid: ${uuid}")
                Log.d(TAG, "firebaseAuthWithGoogleAccount - email: ${email}")
                Log.d(TAG, "firebaseAuthWithGoogleAccount - name: ${name}")

                // Check if the user is new or existing
                if (authResult.additionalUserInfo!!.isNewUser) {
                    // User is new - account created
                    Log.d(TAG, "firebaseAuthWithGoogleAccout: Account created: ${email}")

                    database = FirebaseDatabase.getInstance().getReference("users")
                    val user = User(name, 0, email, 0)
                    database.child(name!!).setValue(user)

                } else {
                    // Exisiting user
                    Log.d(TAG, "firebaseAuthWithGoogleAccount - existing user")
                }

                database = FirebaseDatabase.getInstance().getReference("users")
                database.keepSynced(true)

                checkUser()
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