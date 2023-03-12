package com.mesum.cryptoproject.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.mesum.bitcoinapp.viewmodel.CoinViewModel
import com.mesum.cryptoproject.R
import java.text.SimpleDateFormat
import java.util.*


class DetailActivity : AppCompatActivity() {
    private var graphView: LineChart? = null
    lateinit var viewModel : CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        graphView =  findViewById(R.id.chart)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        val cryptoName = intent.getStringExtra("cryptoName")
        val cryptoPrice = intent.getStringExtra("cryptoPrice")
        val cryptoFullName = intent.getStringExtra("cryptoNameFull")
        window.statusBarColor = resources.getColor(R.color.gray)
        val actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#1F2630"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        getSupportActionBar()?.setTitle("Overview");
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //  createGraphData(price = price?.toList())

        val cryptoText = findViewById<TextView>(R.id.crypto_name)
        cryptoText.text = cryptoName.toString()
        val cryptoFullText = findViewById<TextView>(R.id.crypto_name_full)
        cryptoFullText.text = cryptoFullName.toString()
        val cryptoPriceText = findViewById<TextView>(R.id.crypto_price)
        cryptoPriceText.text = cryptoPrice.toString()
        if (cryptoName != null) {
            viewModel.getCoin(cryptoName)
            viewModel.coinList.observe(this){
                val price = arrayListOf<Float>()
                val unixTime = arrayListOf<Long>()
                val data = it.data
                for (i in data){
                    price.add(i.open.toFloat())
                    unixTime.add(i.time.toLong())
                }
                createGraphData(price,unixTime )
                graphView!!.xAxis.setDrawGridLines(false);

            }
        }


    }


    fun getTime(unixTime : Long): String {
        val unixTime: Long = unixTime // Replace with your own Unix timestamp

        val date = Date(unixTime * 1000L) // Convert seconds to milliseconds

        val sdf = SimpleDateFormat("EEE")
        return   sdf.format(date)

    }

    override fun onSupportNavigateUp(): Boolean {
        this.onBackPressed()
        return true;
    }

    private fun createGraphData(price: List<Float>?, unixTime: ArrayList<Long>){

        Log.d("Sizes", "sixe of price ${price?.size.toString()} size of time ${unixTime.size.toString()}")
        if (graphView != null) {
            val values = mutableListOf<Entry>()
            var a = 0f;
            if (price != null) {
                for (i in price) {
                    val lineChartEntry = Entry(a, i)
                    a++
                    values.add(lineChartEntry)
                }


            }

            Log.d("Tag", "g")
            val set1: LineDataSet = LineDataSet(values, "")
            set1.color = resources.getColor(android.R.color.transparent)
            set1.setCircleColor(Color.GRAY)
            set1.valueTextColor = resources.getColor(R.color.white)
            set1.valueTextSize = 30f
            set1.setDrawFilled(true)
            val fillGradient = ContextCompat.getDrawable(this, R.drawable.reg_gradient)
            set1.fillDrawable = fillGradient
            val dataSet = ArrayList<ILineDataSet>()
            dataSet.add(set1)
            val data = LineData(dataSet)
            configureChartAppearance();
            prepareChartData(data = data)


            val xaxis: XAxis = graphView!!.xAxis
            xaxis.granularity = 1f
            xaxis.axisLineColor = resources.getColor(android.R.color.transparent)
            xaxis.textColor = resources.getColor(R.color.white)
            xaxis.valueFormatter =
            IAxisValueFormatter { value, _ ->

                getTime(unixTime[value.toInt()])
            }
            xaxis.position = XAxis.XAxisPosition.BOTTOM;




            val yAxisRight = graphView!!.getAxisRight();
            yAxisRight.setEnabled(false);

            val leftAxis: YAxis = graphView!!.getAxisLeft()
            leftAxis.isEnabled = false

            leftAxis.setDrawAxisLine(false);

        }

    }



    private fun prepareChartData(data: LineData) {
        data.setValueTextSize(12f)
        graphView!!.data = data
        graphView!!.invalidate()
    }

    private fun configureChartAppearance() {
        graphView!!.setDrawGridBackground(false)

        graphView!!.description.isEnabled = false

        val xAxis: XAxis = graphView!!.xAxis
        xAxis.granularity = 1f

        val leftAxis: YAxis = graphView!!.axisLeft
        leftAxis.setDrawGridLines(false)


        val rightAxis: YAxis = graphView!!.axisRight
        rightAxis.setDrawGridLines(false)

    }


}
