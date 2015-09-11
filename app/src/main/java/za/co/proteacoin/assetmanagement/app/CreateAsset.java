package za.co.proteacoin.assetmanagement.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by dutoitd1 on 2015/09/10.
 */
public class CreateAsset extends Activity {
    private Handler mHandler = new Handler();
    private GlobalState gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_asset);

        gs = (GlobalState) getApplication();
    }
}
