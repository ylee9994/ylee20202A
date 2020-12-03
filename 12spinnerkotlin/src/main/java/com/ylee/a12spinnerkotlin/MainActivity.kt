package com.ylee.a12spinnerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "스피너테스트 코틀린 이용희"

        val movie = arrayOf("쿵푸팬더", "짱구는못말려", "아저씨",
                "아바타", "대부", "국가대표",
                "토이스토리3", "마당을 나온 암탉", "죽은시인의 사회", "서유기")
        val posterId = arrayOf(R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov23,R.drawable.mov24,
                R.drawable.mov25,R.drawable.mov26,
                R.drawable.mov27,R.drawable.mov28,
                R.drawable.mov29,R.drawable.mov30)

        val spinner = findViewById<View>(R.id.spinner1) as Spinner
        val adapter : ArrayAdapter<String>
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                    movie)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val ivpicture = findViewById<View>(R.id.ivPoster) as ImageView
                ivpicture.scaleType = ImageView.ScaleType.FIT_CENTER
                ivpicture.setPadding(5,5,5,5)
                ivpicture.setImageResource(posterId[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }
}
