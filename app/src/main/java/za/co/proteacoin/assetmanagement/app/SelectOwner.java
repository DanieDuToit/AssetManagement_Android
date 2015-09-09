package za.co.proteacoin.assetmanagement.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dutoitd1 on 2015/06/04.
 */
public class SelectOwner extends Activity  implements View.OnClickListener{
	GlobalState gs;
	Spinner mCompaniesSpinner;
	Spinner mTypesSpinner;
	String selectedCompany;
	String selectedType;
	String url = "";
	// Requisition's JSONArray
	JSONArray data = null;
	private ProgressDialog pDialog;

	Integer selectedAssetTypeID;
	ArrayList<String> assetTypeList;
	String[] mAssetTypes;
	String[] mCompanies = {
			"Magnum",
			"Protea Coin",
			"Coin Security"
	};
	HashMap<Integer, Integer> assetTypeIDs;
	ArrayAdapter<String> typeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_owner);


		assetTypeIDs = new HashMap<Integer, Integer>();
		assetTypeList = new ArrayList<String>();

		gs = (GlobalState) getApplication();
		if (getActionBar() != null) {
			getActionBar().setTitle("Asset Capture");
		}
		url = GlobalState.INTERNET_URL;

	}


	private class GetRecords extends AsyncTask<Void, Void, Void> {
		// Get Asset Types
		// Creating service handler class instance
		ServiceHandler sh = new ServiceHandler();
		String getTypesURL = url + "GetAssetTypes.php";

		@Override
		protected Void doInBackground(Void... voids) {
			// Making a request to url and getting response
			List<NameValuePair> queryParams = new ArrayList<NameValuePair>();
			queryParams.add(new BasicNameValuePair("documentId", ""));
			String jsonStr = sh.makeServiceCall(getTypesURL, ServiceHandler.POST, queryParams);
			Log.e("JSONString", jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					data = jsonObj.getJSONArray("assetTypes");
					Log.e("JSONData", data.getString(0));

					// looping through All Contacts
					for (int i = 0; i < data.length(); i++) {
						// Data node is JSON Object
						JSONObject c = data.getJSONObject(i);
						Integer assetTypeID = c.getInt("AssetTypeID");
						String assetDescription = c.getString("Description");
						assetTypeIDs.put(i, assetTypeID);
						assetTypeList.add(assetDescription);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(SelectOwner.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			// Dismiss the progress dialog
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}
			mTypesSpinner.setAdapter(typeAdapter);
		}
	}

	/**
	 * Called when a view has been clicked.
	 *
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {

	}
}
