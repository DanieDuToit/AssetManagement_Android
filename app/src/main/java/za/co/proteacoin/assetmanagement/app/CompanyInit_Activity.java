package za.co.proteacoin.assetmanagement.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dutoitd1 on 2015/08/18.
 */
public class CompanyInit_Activity extends Activity {
    private GlobalState gs;
    private int numberrOfSecurityIDViews;
    private SharedPreferences sharedPref;
    private EditText etCalmDeviceId;
    Button btnSubmit;

    @Override
    protected void onResume() {
        super.onResume();
        GlobalState.setStartTime(SystemClock.uptimeMillis());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        GlobalState.setStartTime(SystemClock.uptimeMillis());
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalState.setStartTime(SystemClock.uptimeMillis());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.companyinitialization);

        gs = (GlobalState) getApplication();

        etCalmDeviceId = (EditText) findViewById(R.id.etDeviceId);
        btnSubmit = (Button) findViewById(R.id.btnSubmitCompanyInfo);
        TextView tv = (TextView) findViewById(R.id.tvAviKey);
        tv.setText(gs.getIvKey());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCalmDeviceId.getText().toString().equals("")) {
                    gs.showAlertDialog(CompanyInit_Activity.this, "Device Id", "You must supply the Company's Device Id", false);
                } else {
                    try {
                        gs.setCalmDeviceId(Integer.parseInt(etCalmDeviceId.getText().toString()));
                    } catch (NumberFormatException e) {
                        gs.showAlertDialog(CompanyInit_Activity.this, "Device Id", "The Device Id can only be numeric", false);
                        return;
                    }
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("calmDeviceId", gs.getCalmDeviceId());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
