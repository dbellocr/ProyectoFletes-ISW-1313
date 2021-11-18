package com.isw.proyectofletes_isw_1313

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.isw.proyectofletes_isw_1313.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id_r))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btIniciarSesion.setOnClickListener(){
            iniciarSesion()
        }

        auth = Firebase.auth
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 90210){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try{
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            }
            catch (e: Throwable){
                val er = e
            }

        }


    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        var credenciales = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credenciales).addOnCompleteListener(this) {
                task ->
            if(task.isSuccessful){
                updateUI(auth.currentUser)
            }else{
                updateUI(null)
            }
        }

    }

    private fun updateUI(currentUser: FirebaseUser?) = if(currentUser != null){
        val intento = Intent(baseContext, Principal::class.java)
        startActivity(intento)


    }else{
        Toast.makeText(baseContext, "No se autenticó", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        updateUI(auth.currentUser)
    }

    private fun iniciarSesion() {
        val intentGoogle = googleSignInClient.signInIntent
        startActivityForResult(intentGoogle, 90210)
    }
}