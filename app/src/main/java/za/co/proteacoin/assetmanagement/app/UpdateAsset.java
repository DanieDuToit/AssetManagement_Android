package za.co.proteacoin.assetmanagement.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by dutoitd1 on 2015/09/10.
 */
public class UpdateAsset extends Activity {
    private Handler mHandler = new Handler();
    private GlobalState gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_asset);

        gs = (GlobalState) getApplication();
    }
}
