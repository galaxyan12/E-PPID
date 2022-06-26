package id.pemprov.ppidkpu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import id.pemprov.ppidkpu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.wvMain.setWebViewClient(new myWebViewClient());
        binding.wvMain.getSettings().setJavaScriptEnabled(true);
        binding.wvMain.loadUrl("https://pontianakkotappid.kpu.go.id");
        binding.wvMain.getSettings().setDomStorageEnabled(true);
    }

    public class myWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            binding.progressBar.setVisibility(View.GONE);
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView wv, String url) {
            if(url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                binding.wvMain.goBack();

                return true;
            }
            return false;
        }
    }

    public void onBackPressed() {
        if (binding.wvMain.canGoBack()) {
            binding.wvMain.goBack();
        } else {
            super.onBackPressed();
        }
    }
}