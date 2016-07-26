package net.hunme.baselibrary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 作者： Administrator
 * 时间： 2016/7/20
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MWebViewClient extends WebViewClient {

    private WebView webView;
    private Context context;

    public MWebViewClient(WebView webView) {
        super();
        this.webView = webView;
    }

    public MWebViewClient(WebView webView, Context context) {
        super();
        this.webView = webView;
        this.context = context;

    }

    /**
     * 在点击请求的是链接是才会调用，
     * 重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
        if (webView==null){
            webView.loadUrl(url);
        }
        // 记得消耗掉这个事件。给不知道的朋友再解释一下，Android中返回True的意思就是到此为止,
        // 事件就会不会冒泡传递了，我们称之为消耗掉
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.e("WebActivity", "页面加载开始");
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.e("WebActivity", "页面加载完成");
        super.onPageFinished(view, url);
    }

    /**
     * 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
     */
    @Override
    public void onLoadResource(WebView view, String url) {
//      Toast.makeText(context, "WebViewClient.onLoadResource", Toast.LENGTH_SHORT).show();
        Log.e("WebActivity", "正在加载页面.....");
        super.onLoadResource(view, url);
    }

    /**
     * 重写此方法可以让webview处理https请求    [拓展]
     */
    @Override
    public void onReceivedSslError(WebView view,
                                   SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
    }
}