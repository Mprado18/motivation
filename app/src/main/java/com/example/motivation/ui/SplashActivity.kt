package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.motivation.databinding.ActivitySplashBinding
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var  mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonSave.setOnClickListener { handleSave() }

        mSecurityPreferences = SecurityPreferences(this)

        verifyName()
    }

    //verifica se nome já foi salvo e recupera o mesmo
    private fun verifyName() {
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)

        if (name != "") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //valida se foi inputado nome, caso não exibe toast, caso sim, salva nome e segue para outra tela
    private fun handleSave() {
        val name = binding.inputText.text

        if (name.isNotEmpty()) {
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }
}