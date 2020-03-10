package com.example.tipcalculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    //toggle variable is set to choose which textView is to be updated.
    var toggle = true
    //this function appends the typed string to the appropriate textView depending
    // on the value of the toggle variable
    fun appendExpression(string: String){
        if (toggle) {
            et_amount.append(string)
        } else {
            et_split.append(string)
        }
    }

    //reset the toggle value to true or false
    fun set_Toggle(tog: Boolean){
        toggle = tog
    }
    //Once the calculate function is called strings in Amount and split text box will be converted into
    //numbers and Tip and Total value will be further calculated
    @RequiresApi(Build.VERSION_CODES.N)
    fun calculate(){
        // if either of the two text boxes are left empty, default values will be added
        if(et_amount.text.toString()==""){
            et_amount.text="0"
        }
        if(et_split.text.toString()==""){
            et_split.text="1"
        }
        var amount: Float
        var split_between: Int
        //In case of any error from the user inputting the string, all values will be cleared and
        //user will have to enter everything again.
        try{
             amount = et_amount.text.toString().toFloat()
             split_between = et_split.text.toString().toInt()
        }catch(e: Exception) {
            clear()
            return
        }

        var tip = tv_precent.text.toString().substring(0,tv_precent.text.length-1).toFloat()

        if(split_between==0){
            split_between = 1
        }
        var tip_value = ((amount*tip)/100)/ split_between
        var total = (amount + tip_value) / split_between
        tip_value = 100*tip_value
        tip_value = round(tip_value)
        tip_value /= 100
        total = 100*total
        total = round(total)
        total /= 100
        tv_tip.text = tip_value.toString()
        tv_total.text = total.toString()
    }
    //To reset all values in the application
    @RequiresApi(Build.VERSION_CODES.N)
    fun clear(){
        et_amount.text = ""
        et_split.text = "1"
        tv_precent.text = "15%"
        tv_tip.text = "0.00"
        tv_total.text = "0.00"
        sb_percent.setProgress(15,true)
        textViewdot.isClickable = true
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_amount.setOnClickListener { set_Toggle(true) }
        et_split.setOnClickListener { set_Toggle(false)
        }
        textViewClear.setOnClickListener {clear()}
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
        //On click functions of all the buttons
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
        //Does not allow user to enter 2 decimals in one number
        textViewdot.setOnClickListener{
            if(!et_amount.text.contains(".")){
                appendExpression(".") }
        }

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
