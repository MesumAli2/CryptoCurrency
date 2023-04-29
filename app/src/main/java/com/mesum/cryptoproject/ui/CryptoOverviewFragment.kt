package com.mesum.cryptoproject.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mesum.cryptoproject.R
import com.mesum.cryptoproject.databinding.FragmentCryptoDetailBinding
import com.mesum.cryptoproject.databinding.FragmentCryptoOverviewBinding
import com.mesum.cryptoproject.ui.main.MainActivity

private const val CryptoArgs = "coin_url"
// Define a URI that uniquely identifies your fragment
val URI_CRYPTO_OVERVIEW = Uri.parse("cryptocompare://cryptoOverview")


class CryptoOverviewFragment : Fragment() {

    private var _binding : FragmentCryptoOverviewBinding? = null
    private val binding get() = _binding!!
    private var cryptoUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cryptoUrl = it.getString(CryptoArgs)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCryptoOverviewBinding.inflate(inflater, container, false)

        // Get the Intent that was used for deep linking from the hosting activity
        val intent = if (activity is MainActivity) {
            (activity as MainActivity).getDeepLinkIntent()
        } else {
            null
        }

        // Extract the "coin_url" parameter from the Intent's data
        var coinUrl: String? = null
        intent?.data?.let { data ->
            val coinUrlParam = data.getQueryParameter("coin_url")
            coinUrlParam?.let {
                coinUrl = Uri.decode(coinUrlParam)
                Toast.makeText(activity, "$coinUrl is ", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val navBackStackEntry = findNavController().currentBackStackEntry

        if (arguments?.containsKey("coinUrl") == true) {
            val uriString = "cryptoCompare://cryptoOverview?coin_url=${arguments?.getString("coinUrl")}"
            val uri = Uri.parse(uriString)

            Log.d("CryptoUrl", uri.toString())
            binding.progressBar.visibility = View.VISIBLE

            binding.cryptoWebViewWW.settings.javaScriptEnabled = true
            binding.cryptoWebViewWW.loadUrl(requireArguments().getString("coinUrl").toString())
            // Use the uri as needed
            binding.cryptoWebViewWW.webViewClient = object : WebViewClient() {

                override fun onPageFinished(view: WebView?, url: String?) {
                    // Hide the ProgressBar when the page has finished loading
                    binding.progressBar.visibility = View.GONE
                }

            }
        }
    }

}