package com.example.bmi_calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val height = findViewById<EditText>(R.id.heightEditText)
        val weight = findViewById<EditText>(R.id.weightEditText)

        val calcButton = findViewById<Button>(R.id.button)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val bmiInfo = findViewById<TextView>(R.id.infoBMITextView)
        val heartRateButton = findViewById<Button>(R.id.heartRateButton)


        // make heart rate button inactive
        heartRateButton.visibility = View.GONE

        // Make info text invisible until Calculate button is pressed
        bmiInfo.visibility = View.INVISIBLE
        fun bmiResults(bmi:Double):String{
            lateinit var answer: String
            if(bmi<18.5){
                answer="Underweight"
            } else if(bmi > 18.5 && bmi < 24.9) {
                answer="Normal"
            } else if(bmi > 24.9 && bmi < 30) {
                answer="Overweight"
            } else {
                answer="Obese"
            }
            return answer
        }

        calcButton.setOnClickListener{
            var heightValue = 0.0
            var weightValue = 0.0
            if(height.text.toString().isNotEmpty()){
                heightValue = height.text.toString().toDouble()
            }
            if(weight.text.toString().isNotEmpty()){
                weightValue = weight.text.toString().toDouble()
            }
            if(weightValue > 0.0 && heightValue > 0.0){
                val bmiValue = String.format(" %.2f",( weightValue*703)/(heightValue*heightValue))
                val bmiDouble = bmiValue.toDouble()
                bmiInfo.text = "BMI is ${String.format("%.2f",bmiDouble)} | You are " + "${bmiResults((weightValue*703)/(heightValue*heightValue))}"
                bmiInfo.visibility = View.VISIBLE
            }
            else {
                Toast.makeText(
                    this, "Please input Weight and Height Values greater than 0",
                    Toast.LENGTH_LONG).show()
            }
        }

        clearButton.setOnClickListener {
            weight.text.clear()
            height.text.clear()
            bmiInfo.visibility = View.INVISIBLE

        }

    }
}

//class MainActivity : AppCompatActivity() {
//
//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
//}
