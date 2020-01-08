package com.ad.onlyinterad
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(v: View) {
        when (v.id) {
            R.id.sample_text -> {
                AdClass.showInterstitialAd(this)
            }
            R.id.load -> {
                AdClass.loadAd()
            }
        }

    }
}
