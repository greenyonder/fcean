package falcofinder.android.fce;


import java.util.ArrayList;
import java.util.Collections;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PVListActivity extends Activity implements AdListener {
    /** Called when the activity is first created. */
	
	  private static String MY_AD_UNIT_ID = "a14f79f6b947edc"; 
		private AdView adView;
		ArrayList<String> pvlist;
	int bookmark=1;
	ArrayList alwords;
	  
	  
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fcepvlist_layout);

        setWords();
        
        Button btn = (Button) this.findViewById(R.id.buttonv) ;
        btn.setTag("check");
        
 adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);
        
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.footer);

       // Add the adView to it
         layout.addView(adView);


         AdRequest request = new AdRequest();

         request.addKeyword(getString(R.string.keys));
                
          request.addTestDevice(AdRequest.TEST_EMULATOR);
         request.addTestDevice("HT08YPL02569");    // My T-Mobile G1 test phone
         request.addTestDevice("95CF54CD666F6634114A210B4B6B671B");
       // Initiate a generic request to load it with an ad
         adView.loadAd(request);
    
    }
    
   
    
    

	private void setWords() {
		// TODO Auto-generated method stub
		//pvlist = getResources().getStringArray(R.array.pv);
        
		pvlist = Util.getList(this, getString(R.string.sqlpv), 3);
	     
	        bookmark = Util.getBookmark(this, getString(R.string.sqlbookmark) , "fcepv");

	        alwords = Util.getAlFromString(""+pvlist.get(bookmark-1));
        
        TextView textView2 = (TextView) this.findViewById(R.id.textView2);
     
        textView2.setText(new SpannableString(""+ alwords.get(0).toString().toUpperCase() + "\n\n " + alwords.get(1) ));


	}





	@Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = Util.getSharedPreferences(this);
      //  System.out.println("--->this.modedefault"+this.modedefault);
        //  System.out.println("--->this.posizione"+this.posizione);
        
        
    }
	
	public final void onClick(View v) {
	String tag =""+v.getTag();
	
	if (tag.equals("back")) {
		
		super.finish();
		
	} else {
		if (tag.equals("check")) {
			String s = "\n" + alwords.get(2);
		
			TextView textViewResult = (TextView) this.findViewById(R.id.textViewResult);
			textViewResult.setText(new SpannableString(s));
			
	        Button btn = (Button) this.findViewById(R.id.buttonv) ;
	        btn.setTag("continue");
	        btn.setText(R.string.btncontinue);
	        setNewBookmark();
	        
		} else {
			Button btn = (Button) this.findViewById(R.id.buttonv) ;
	        btn.setTag("check");
	        btn.setText(R.string.btncheck);
	    	TextView textViewResult = (TextView) this.findViewById(R.id.textViewResult);
			textViewResult.setText(new SpannableString(""));
			
	        setWords();
	        
	        
		}
	}}


	 private void setNewBookmark() {
			// TODO Auto-generated method stub
			if (bookmark==this.pvlist.size()) bookmark=1;
			else bookmark++;
			Util.saveBookmark(this, "fcepv", bookmark);
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
