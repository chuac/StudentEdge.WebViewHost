package au.com.studentedge.webviewhost

import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.webkit.WebView
import android.webkit.WebViewClient
import au.com.studentedge.webviewhost.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webview)
//        webView.webViewClient = SeWebViewClient()

//        webView = WebView(this)
//        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://studentedgecontent.blob.core.windows.net/scripts/index-0.0.12.html")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed()
        }
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        // Check if the key event was the Back button and if there's history
//        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
//            webView.goBack()
//            return true
//        }
//        // If it wasn't the Back key or there's no web page history, bubble up to the default
//        // system behavior (probably exit the activity)
//        return super.onKeyDown(keyCode, event)
//    }
}

// https://developer.android.com/guide/webapps/webview#HandlingNavigation
private class SeWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (Uri.parse(url).host == "www.studentedge.org") {
            // This is my web site, so do not override; let my WebView load the page
            return false
        }
        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        // We don't have another activity to handle URLs, let device open in any browser available
//        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
//            startActivity(this)
//        }
        return true
    }
}