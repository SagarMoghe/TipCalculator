package com.example.tipcalculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var toggle = true

    fun appendExpression(string: String){
        if (toggle) {
            et_amount.append(string)
        } else {
            et_split.append(string)
        }
    }

    fun set_Toggle(tog: Boolean){
        toggle = tog

    }

    fun calculate(){
        var amount = et_amount.text.toString().toFloat()
        var tip = tv_precent.text.toString().substring(0,tv_precent.text.length-1).toFloat()
        var split_between = et_split.text.toString().toInt()
        if(split_between==0){
            split_between = 1
        }
        var tip_value = ((amount*tip)/100)/ split_between
        var total = (amount + tip_value) / split_between
        tv_tip.text = tip_value.toString()
        tv_total.text = total.toString()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_amount.setOnClickListener {set_Toggle(true)}
        et_split.setOnClickListener {set_Toggle(false)}
        textViewClear.setOnClickListener {
            et_amount.text = ""
            et_split.text = "1"
            tv_precent.text = "15%"
            tv_tip.text = "0.00"
            tv_total.text = "0.00"
            sb_percent.setProgress(15,true)

        }
        sb_percent.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(sb_percent: SeekBar, i: Int, b: Boolean) {
                tv_precent.text =  "$i%"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                 //To change body of created functions use File | Settings | File Templates.
            }
        })
        textView1.setOnClickListener{appendExpression("1")}
        textView2.setOnClickListener{appendExpression("2")}
        textView3.setOnClickListener{appendExpression("3")}
        textView4.setOnClickListener{appendExpression("4")}
        textView5.setOnClickListener{appendExpression("5")}
        textView6.setOnClickListener{appendExpression("6")}
        textView7.setOnClickListener{appendExpression("7")}
        textView8.setOnClickListener{appendExpression("8")}
        textView9.setOnClickListener{appendExpression("9")}
        textView0.setOnClickListener{appendExpression("0")}
        textViewdot.setOnClickListener{appendExpression(".")}
        textViewback.setOnClickListener{
            if(toggle){
                val string = et_amount.text.toString()
                if(string.isNotEmpty()){
                    et_amount.text = string.substring(0,string.length-1)
                }
            }else {
                val string = et_split.text.toString()
                if (string.isNotEmpty()) {
                    et_split.text = string.substring(0, string.length - 1)
                }
            }
        }
        textViewCalculate.setOnClickListener {calculate()}

    }
}
