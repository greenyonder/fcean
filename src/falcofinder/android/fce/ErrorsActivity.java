package falcofinder.android.fce;


import java.util.ArrayList;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdView;

import falcofinder.android.fce.db.DataBaseHelper;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ErrorsActivity extends Activity implements AdListener {
    /** Called when the activity is first created. */
	
	  private static String MY_AD_UNIT_ID = "a14f79f6b947edc"; 
		private AdView adView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.errors_layout);

        ListView lv = (ListView)findViewById(R.id.listView1);
        Bundle bundle = this.getIntent().getExtras();
    
        errors(bundle.getString("type"));
        
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
    
    private void errors(String type) {
		// TODO Auto-generated method stub
			DataBaseHelper mDbHelper = new DataBaseHelper(this);
			SQLiteDatabase mDb = mDbHelper.getWritableDatabase();
	    	 Cursor cur = mDb.rawQuery("select question, youranswer, rightanswer from " + falcofinder.android.fce.db.DataBaseHelper.TABLE_ERRORI + " where type= ? ", new String[] { type }) ;
 
	    	//System.out.println("--->select posizione from " + DataBaseHelper.TABLE_INSULTI );
	         
	         cur.moveToFirst();
	         ArrayList questions = new ArrayList();
	         while (cur.isAfterLast() == false) {        	 
	        	 
	        	 

	        //	 System.out.println("--->insulto " + insulto);
	        	 Question q = new Question(""+ cur.getString(0),""+ cur.getString(1),""+ cur.getString(2), type);
	        	 questions.add(q);
	        	 
	        	   cur.moveToNext();
	         }
				 
	         
	         if (questions!=null){ 
					  ListView lv = (ListView) findViewById(R.id.listView1);
					         
					        // System.out.println("--->ListView"+lv);
					         lv.setAdapter(new QuestionAdapter(this, android.R.layout.simple_list_item_1, questions));
				  }
		
	         
	         mDb.close();
		  
	  
	}
public final void onClick(View v) {
	 	
    	String selview = "";
    	if (v.getTag()!=null) selview = ""+v.getTag();
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
