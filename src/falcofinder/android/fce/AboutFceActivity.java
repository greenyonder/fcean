package falcofinder.android.fce;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutFceActivity  extends Activity  implements AdListener {
    /** Called when the activity is first created. */
	
	  private static String MY_AD_UNIT_ID = "a14f79f6b947edc"; 
		private AdView adView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fceabout_layout);

 adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);
        
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.footer);

       // Add the adView to it
         layout.addView(adView);

         
         TextView textabout = (TextView) findViewById(R.id.textabout);
         textabout.setText(textabout.getText() + "\n\n\nv."+Util.getVersion(getPackageManager())+"n\n\n\n\n\n\n");

         AdRequest request = new AdRequest();

         request.addKeyword(getString(R.string.keys));
                
          request.addTestDevice(AdRequest.TEST_EMULATOR);
         request.addTestDevice("HT08YPL02569");    // My T-Mobile G1 test phone
         request.addTestDevice("95CF54CD666F6634114A210B4B6B671B");
       // Initiate a generic request to load it with an ad
         adView.loadAd(request);
         
    }

public final void onClick(View v) {
	 	
    	String selview = "";
    	if (v.getTag()!=null) selview = ""+v.getTag();
    	
    	//System.out.println("--->selview"+selview);
    	
    	if (selview.equals("back")) {
    		
    		super.finish();
    	}
}
	@Override
	public void onDismissScreen(Ad arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeaveApplication(Ad arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPresentScreen(Ad arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveAd(Ad arg0) {
		// TODO Auto-generated method stub
		
	}

}
