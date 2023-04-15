package com.mesum.cryptoproject.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.mesum.bitcoinapp.viewmodel.CoinViewModel
import com.mesum.cryptoproject.R
import com.mesum.cryptoproject.databinding.FragmentCryptoDetailBinding
import java.text.SimpleDateFormat
import java.util.*


class CryptoDetailFragment : Fragment() {

    private var _binding : FragmentCryptoDetailBinding? = null
    val binding get() = _binding!!
    private var cryptoName: String? = null
    private var cryptoPrice: String? = null
    private var cryptoFullName: String? = null

    private var graphView: LineChart? = null
    lateinit var viewModel : CoinViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the argument values
        arguments?.let {
            cryptoName = it.getString("cryptoName")
            cryptoPrice = it.getString("cryptoPrice")
            cryptoFullName = it.getString("cryptoNameFull")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCryptoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        graphView =  binding.chart
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]


        val cryptoText = binding.cryptoName
        cryptoText.text = cryptoName.toString()
        val cryptoFullText = binding.cryptoNameFull
        cryptoFullText.text = cryptoFullName.toString()
        val cryptoPriceText = binding.cryptoPrice
        cryptoPriceText.text = cryptoPrice.toString()
        if (cryptoName != null) {
            viewModel.getCoin(cryptoName!!)

            viewModel.coinList.observe(viewLifecycleOwner){
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
        Log.d("ghv", "b")
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
            Log.d("Tag", "g")
            val set1: LineDataSet = LineDataSet(values, "")
            set1.color = resources.getColor(android.R.color.transparent)
            set1.setCircleColor(Color.GRAY)
            set1.valueTextColor = resources.getColor(R.color.white)
            set1.valueTextSize = 30f
            set1.setDrawFilled(true)
            val fillGradient = ContextCompat.getDrawable(requireContext(), R.drawable.reg_gradient)
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