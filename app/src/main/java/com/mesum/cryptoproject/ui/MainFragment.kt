package com.mesum.cryptoproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mesum.bitcoinapp.viewmodel.CoinViewModel
import com.mesum.cryptoproject.R
import com.mesum.cryptoproject.databinding.FragmentMainBinding
import com.mesum.cryptoproject.ui.`interface`.OnCryptoClicked
import com.mesum.cryptoproject.ui.main.adapter.CoinListAdapter


class MainFragment : Fragment(), OnCryptoClicked {
    private var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel : CoinViewModel
  lateinit var  adapter : CoinListAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        viewModel.pullData()

        adapter = CoinListAdapter(requireContext(),this)
        val rv : RecyclerView =  binding.coinRecycler


        viewModel.coinData.observe(viewLifecycleOwner){
            val list = it.data.filterNot {  it.coinInfo.name.contains("COVER")}
            Log.d("activity", "${it.data}")
            adapter.submitList(list)
            rv.adapter = adapter
        }

    }

    override fun onItemClick(
        cryptoName: String,
        cryptoFulName: String,
        cryptoPrice: String,
        overviewUrl: String
    ) {

        val bundle = bundleOf("cryptoName" to cryptoName,
                   "cryptoNameFull" to cryptoFulName,
                    "cryptoPrice" to cryptoPrice,
                    "overview" to overviewUrl
            )

        findNavController().navigate(R.id.action_mainFragment_to_cryptoDetailFragment, bundle)
    }

}