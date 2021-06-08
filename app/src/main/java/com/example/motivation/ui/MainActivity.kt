package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private lateinit var  mSecurityPreferences: SecurityPreferences

    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.hide()

        mSecurityPreferences = SecurityPreferences(this)

        //exibe msg de olá para nome recebido
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        binding.textName.text = "Olá, $name!"

        handleNewPhrase()

        //gera nova frase
        binding.buttonNewPhrase.setOnClickListener { handleNewPhrase() }

        val all = binding.imageAll
        all.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
        val happy = binding.imageHappy
        val sun = binding.imageMorning

        //evento click em modo all
        all.setOnClickListener {
            all.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
            happy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            sun.setColorFilter(ContextCompat.getColor(this, R.color.white))
            mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
        }

        //evento click em modo happy
        happy.setOnClickListener {
            all.setColorFilter(ContextCompat.getColor(this, R.color.white))
            happy.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
            sun.setColorFilter(ContextCompat.getColor(this, R.color.white))
            mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
        }

        //evento click em modo morning
        sun.setOnClickListener {
            all.setColorFilter(ContextCompat.getColor(this, R.color.white))
            happy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            sun.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
            mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
        }
    }

    //recebe nova frase randomizada e filtrada
    private fun handleNewPhrase() {
        binding.textPhrase.text = Mock().getPhrase(mPhraseFilter)
    }
}