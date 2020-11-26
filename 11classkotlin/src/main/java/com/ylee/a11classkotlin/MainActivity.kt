package com.ylee.a11classkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tvInfo1 : TextView
    lateinit var tvInfo2 : TextView
    lateinit var tvInfo3 : TextView
    lateinit var tvInfo4 : TextView
    lateinit var tvInfo5 : TextView
    var i1 : Int = 0
    lateinit var v1 : Vehicle
    lateinit var c1 : Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInfo1 = findViewById<TextView>(R.id.tvInfo1)
        tvInfo2 = findViewById(R.id.tvInfo2)
        tvInfo3 = findViewById(R.id.tvInfo3)
        tvInfo4 = findViewById(R.id.tvInfo4)
        tvInfo5 = findViewById(R.id.tvInfo5)

        v1 = Vehicle("내차", 50, 200, 60,
        4, false)
        c1 = Car("내두번째차", 40, 300, 60,
                4, true, "v3")
        tvInfo1.setText(v1.toString())
        tvInfo2.setText(c1.toString())
        tvInfo5.setText(v1.sound())
        tvInfo4.setText(c1.sound())
    }

}
