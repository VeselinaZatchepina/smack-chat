package com.github.veselinazatchepina.swoosh.controller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.veselinazatchepina.swoosh.R
import com.github.veselinazatchepina.swoosh.model.Player
import com.github.veselinazatchepina.swoosh.utils.EXTRA_PLAYER
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        searchLeagueText.text = "Looking for ${player.league} ${player.skill} league near you..."
    }
}
