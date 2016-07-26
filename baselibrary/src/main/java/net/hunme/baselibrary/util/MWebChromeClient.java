package net.hunme.baselibrary.util;

import android.app.Activity;
import android.content.Context;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;

/**
 * 作者： Administrator
 * 时间： 2016/7/20
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MWebChromeClient  extends WebChromeClient {

    private Context context;

    public MWebChromeClient(Context context) {
        super();
        this.context = context;
    }

    // 处理Alert事件
    @Override
    public boolean onJsAlert(WebView view, String url, String message,
                             JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    // onReceivedTitle()方法修改网页标题
    @Override
    public void onReceivedTitle(WebView view, String title) {
        ((Activity)context).setTitle("可以用onReceivedTitle()方法修改网页标题");
        super.onReceivedTitle(view, title);
    }

    // 处理Confirm事件
    @Override
    public boolean onJsConfirm(WebView view, String url, String message,
                               JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    // 处理提示事件
    @Override
    public boolean onJsPrompt(WebView view, String url, String message,
                              String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
    //扩充缓存的容量
    @Override
    public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(spaceNeeded * 2);
    }
}
