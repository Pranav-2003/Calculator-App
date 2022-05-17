package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvinput : TextView? = null
    var lastnum : Boolean = false
    var lastdot : Boolean = false
    var c : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvinput = findViewById(R.id.input)
    }
    fun ondigit(view: View){
        tvinput?.append((view as Button).text)
        lastnum = true
        lastdot = false
    }
    fun onclear(view: View){
        tvinput?.text = ""
    }
    fun ondecimal(view: View){
        if(lastnum && !lastdot){
            tvinput?.append(".")
            lastnum = false
            lastdot = true
        }
    }
    fun onoperator(view: View){
        tvinput?.text?.let{
            if(lastnum && !isoperator(it.toString())){
                tvinput?.append((view as Button).text)
                lastnum = false
                lastdot = false
            }
        }}
    fun onequal(view: View){
        if(lastnum){
            var tvvalue = tvinput?.text.toString()
            var prefix = ""
            try{
                if(tvvalue.startsWith("-")){
                    prefix = "-"
                    tvvalue = tvvalue.substring(1)
                }
                if(tvvalue.contains("-")){
                    val split =  tvvalue.split("-")
                    var one = split[0]
                    var two = split[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezero((one.toDouble() - two.toDouble()).toString())
                }else if(tvvalue.contains("+")){
                    val split =  tvvalue.split("+")
                    var one = split[0]
                    var two = split[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezero((one.toDouble() + two.toDouble()).toString())
                }else if(tvvalue.contains("*")){
                    val split =  tvvalue.split("*")
                    var one = split[0]
                    var two = split[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezero((one.toDouble() * two.toDouble()).toString())
                }else if(tvvalue.contains("/")){
                    val split =  tvvalue.split("/")
                    var one = split[0]
                    var two = split[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvinput?.text = removezero((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removezero(result: String) : String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }

    private  fun isoperator(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{value.contains("/") || value.contains("+") || value.contains("*")
                || value.contains("-")}
    }
    fun ondelete(view: View){
        var value = tvinput?.text.toString()
        tvinput?.text = value.substring(0,value.length-1)
    }
}