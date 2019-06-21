package com.wefyns.customdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val lay : RelativeLayout = findViewById(R.id.MainLayout)
//        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog, lay, false)
//        val dialog = CustomDialog(this, dialogView, lay)
//
//        val openDialogBtn = findViewById<Button>(R.id.OpenDialogBtn).apply {
//            setOnClickListener{show(dialog)}
//        }
    }

//    fun show(dialog: CustomDialog){
//        dialog.alignBottom()
//        dialog.setCanceble()
//        dialog.setMargins(24, 24, 24, 24)
//        dialog.setBackgroundBlackout(0.5f)
//        dialog.show()
//    }
}
