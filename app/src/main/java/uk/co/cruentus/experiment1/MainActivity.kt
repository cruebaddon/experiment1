package uk.co.cruentus.experiment1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val tickReceiver by lazy {makeBroadcastReceiver()}

    fun getCurrentTime(timeunits:String): String {
        val simpleDateFormat =
                when (timeunits){
                    "hoursandminutes" -> SimpleDateFormat("HH:mm", Locale.UK)
                    "hours" -> SimpleDateFormat("HH")
                    "minutes" -> SimpleDateFormat("mm")
                    else -> SimpleDateFormat("HH:mm", Locale.US)
                }
        return simpleDateFormat.format(currentTimeMillis())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(tickReceiver, IntentFilter(Intent.ACTION_TIME_TICK))
       italianClock1.text = tellItalianTime()
    }

    private fun makeBroadcastReceiver():BroadcastReceiver{
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                if (intent?.action == Intent.ACTION_TIME_TICK) {
                    italianClock1.text = tellItalianTime()
                }
            }
        }
    }

    private fun tellItalianTime():String{
        return "Sono le "+ numeri[getCurrentTime("hours").toInt()] + " e " + numeri[getCurrentTime("minutes").toInt()]
    }

    val numeri = arrayOf(
            "zero","uno","due","tre","quattro","cinque","sei","sette","otto","nove",
            "dieci","undici","dodici","tredici","quattordici","quindici","sedici","diciassette","diciotto","diciannove",
            "venti","ventuno","ventidue","ventitré","ventiquattro","venticinque","ventisei","ventisette","ventotto","ventinove",
            "trenta","trentuno","trentadue","trentatré","trentaquattro","trentacinque","trentasei","trentasette","trentotto","trentanove",
            "quaranta","quarantuno","quarantadue","quarantatré","quarantaquattro","quarantacinque","quarantasei","quarantasette","quarantotto","quarantanove",
            "cinquanta","cinquantuno","cinquantadue","cinquantatré","cinquantaquattro","cinquantacinque","cinquantasei","cinquantasette","cinquantotto","cinquantanove",
            "sessanta","sessantuno","sessantadue","sessantatré","sessantaquattro","sessantacinque","sessantasei","sessantasette","sessantotto","sessantanove",
            "settanta","settantuno","settantadue","settantatré","settantaquattro","settantacinque","settantasei","settantasette","settantotto","settantanove",
            "ottanta","ottantuno","ottantadue","ottantatré","ottantaquattro","ottantacinque","ottantasei","ottantasette","ottantotto","ottantanove",
            "novanta","novantuno","novantadue","novantatré","novantaquattro","novantacinque","novantasei","novantasette","novantotto","novantanove"
    )
}
