package com.mesum.cryptoproject.ui.detail

import android.graphics.BitmapShader
import android.graphics.Color
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
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
        Toast.makeText(this, "${intent.getStringExtra("cryptoName")}", Toast.LENGTH_SHORT).show()
      //  createGraphData(price = price?.toList())

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





            val set1: LineDataSet = LineDataSet(values, "Chart")
            set1.color = resources.getColor(android.R.color.transparent)
            set1.setCircleColor(Color.GRAY)
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
