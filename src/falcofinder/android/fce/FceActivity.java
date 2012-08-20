package falcofinder.android.fce;

import java.io.IOException;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdView;

import falcofinder.android.fce.db.DataBaseHelper;




import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FceActivity extends Activity implements AdListener {
    /** Called when the activity is first created. */
	
	  private static String MY_AD_UNIT_ID = "a14f79f6b947edc"; 
		private AdView adView;
	
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fcemain);
        
        /**
         * Creating all buttons instances
         * */
        // Dashboard News feed button
        Button btn1 = (Button) findViewById(R.id.btn1);
 
        // Dashboard Friends button
        Button btn2 = (Button) findViewById(R.id.btn2);
 
        // Dashboard Messages button
        Button btn3 = (Button) findViewById(R.id.btn3);
 
        // Dashboard Places button
        Button btn4 = (Button) findViewById(R.id.btn4);
 
        // Dashboard Events button
        Button btn5 = (Button) findViewById(R.id.btn5);
 
        // Dashboard Photos button
        Button btn6 = (Button) findViewById(R.id.btn6);
 
        
        Button btnabout = (Button) findViewById(R.id.btnabout);
        
        /**
         * Handling all button click events
         * */
 
        // Listening to News Feed button click
        btn1.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
             //   System.out.println("--->onClick MultipleActivity ");
            	
                Intent i = new Intent(getApplicationContext(), MultipleActivity.class);
                startActivity(i);
            }
        });
 
       // Listening Friends button click
        btn2.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), WordFormationActivity.class);
                startActivity(i);
            }
        });
 
        // Listening Messages button click
        btn3.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), WordFormationlistActivity.class);
                startActivity(i);
            }
        });
 
       
        /// ERRORS
        ActionItem addAction = new ActionItem();
        
        addAction.setTitle("Multiple Choice");
        addAction.setIcon(getResources().getDrawable(R.drawable.fceicona1sml));

        //Accept action item
        ActionItem accAction = new ActionItem();
       
        accAction.setTitle("Word Formation");
        accAction.setIcon(getResources().getDrawable(R.drawable.fceicona2sml));
       
  ActionItem resetAction = new ActionItem();
        
        resetAction.setTitle(getString(R.string.reset));
        resetAction.setIcon(getResources().getDrawable(R.drawable.reset));
   
       
        final QuickAction mQuickAction  = new QuickAction(this);
       
        mQuickAction.addActionItem(addAction);
        mQuickAction.addActionItem(accAction);
        mQuickAction.addActionItem(resetAction);
       
       
        //setup the action item click listener
        mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {                
                @Override
                public void onItemClick(int pos) {
                       
                		 if (pos == 0) { //Add item selected
                        	
                        	Intent intent = new Intent(FceActivity.this, ErrorsActivity.class);
                         	Bundle bundle = new Bundle();
                         	bundle.putString("type","multiple");
                		 	
                		   	intent.putExtras(bundle);
                           

                		    startActivity(intent);
                        } else if (pos == 2) { //Accept item selected
                        	Intent intent = new Intent(FceActivity.this, ErrorsActivity.class);
                         	Bundle bundle = new Bundle();
                         	bundle.putString("type","wf");
                		 	
                		   	intent.putExtras(bundle);
                           

                		    startActivity(intent);
                        } else if (pos == 3) { //Accept item selected
                        		
                        	reset();
                                
                        }      
                }
        });
        
       
        
        
        
 
        // Listening to Events button click
        btn4.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), PVListActivity.class);
                startActivity(i);
            }
        });
 
        btn5.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
            	  mQuickAction.show(view);
                  mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
            }
        });
        
        // Listening to Photos button click
        btn6.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
            	 finish();
            }
        });
        
        // Listening to Events button click
        btnabout.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), AboutFceActivity.class);
                startActivity(i);
            }
        });
        
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
         
         initDb();
         
    }

    private void initDb() {
		// TODO Auto-generated method stub
    	// TODO Auto-generated method stub
   	 DataBaseHelper mDbHelper = new DataBaseHelper(this);
		try {
			 
			 mDbHelper.createDataBase();
	 
	 	} catch (IOException ioe) {
	 		Toast.makeText(getApplicationContext(), 
	 				"Cannot write \"FCE database\" on the phone:( Please write the developer.", 
    		        Toast.LENGTH_LONG).show();
	 
	 	}
		mDbHelper.close();
	
	}

	private void reset() {
		// TODO Auto-generated method stub
		DataBaseHelper mDbHelper = new DataBaseHelper(this);
		SQLiteDatabase mDb = mDbHelper.getWritableDatabase();
		
		 int righe = mDb.delete(DataBaseHelper.TABLE_ERRORI, null ,null);
		 

	   mDb.close();
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