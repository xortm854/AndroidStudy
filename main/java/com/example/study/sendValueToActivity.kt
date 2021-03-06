package com.example.study

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.android.synthetic.main.sendintent_main.*
//import kotlinx.android.synthetic.main.activity_result.*

class sendValueToActivity : AppCompatActivity() {
    companion object{
        val REQUEST = 0
        val ID = "ID"
        val PASSWD ="PASSWD"
        val RESULT = "RESULT"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode != REQUEST) return
        data?.getStringExtra(RESULT).let{
            txtMessage.text = it
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sendintent_main)
        edtId.setOnFocusChangeListener() {
                v, hasFocus ->
            val edt = v as EditText
            val color = if (hasFocus) {
                Color.TRANSPARENT
            } else {
                Color.LTGRAY
            }
            edt.setBackgroundColor(color)
        }
        edtPassWD.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txtMessage.text = s
            }
        })
        btnLogin.setOnClickListener {
            val i = Intent(this,ResultActivity::class.java)
//            val i = Intent(this, ResultActivity::class.java)
            i.putExtra(ID, edtId.text.toString())
            i.putExtra(PASSWD, edtPassWD.text.toString())
            startActivityForResult(i, REQUEST)
//            startActivity(i)
        }
    }
}
