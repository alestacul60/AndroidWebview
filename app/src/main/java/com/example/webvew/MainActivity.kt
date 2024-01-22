package com.example.webvew

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://marcianotoursrl.com.ar/login"

    // Declarar el objeto WebView
    private lateinit var webView: WebView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh = findViewById(R.id.swipeRefresh)

        // Inicializar el objeto WebView
        webView = findViewById(R.id.webView)

        // Habilitar JavaScript en WebView (marcado como obsoleto)
        @Suppress("DEPRECATION")
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Configurar el WebChromeClient
        webView.webChromeClient = object : WebChromeClient() {

        }

        // Configurar el WebViewClient
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }

        // Cargar la URL base en el WebView
        webView.loadUrl(baseUrl)

        // Configurar el SwipeRefreshLayout
        swipeRefresh.setOnRefreshListener {
            webView.reload()
            swipeRefresh.isRefreshing = false
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
