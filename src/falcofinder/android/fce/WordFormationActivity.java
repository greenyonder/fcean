package falcofinder.android.fce;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdView;

import falcofinder.android.fce.db.DataBaseHelper;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WordFormationActivity  extends Activity implements AdListener {
    /** Called when the activity is first created. */
	
	  private static String MY_AD_UNIT_ID = "a14f79f6b947edc"; 
		private AdView adView;
ArrayList<String> wflist;
int bookmark=1;
ArrayList alwords;
	
	 EditText edittext;
	  String parola="";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fcewf_layout);
        
        wflist = Util.getList(this, getString(R.string.sqlwf), 3);
        bookmark = Util.getBookmark(this, getString(R.string.sqlbookmark) , "fcewf");
        
       
        
        Button btn = (Button) this.findViewById(R.id.buttonv) ;
        btn.setTag("check");
        
        edittext = (EditText) findViewById(R.id.edittext);
        edittext.setOnKeyListener(new OnKeyListener()
        {
        /**
          * This listens for the user to press the enter button on
          * the keyboard and then hides the virtual keyboard
          */
        	   @Override
    	    public boolean onKey(View arg0, int arg1, KeyEvent event) {
    	    // If the event is a key-down event on the "enter" button
    	    if ( (event.getAction() == KeyEvent.ACTION_DOWN ) &&
    	    (arg1 == KeyEvent.KEYCODE_ENTER) )
    	    {
    	    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    	    imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
    	    return true;
    	    }
    	    return false;
    	    }

    	
    	
        } );
        
        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {

                	WordFormationActivity.this.getWindow().setSoftInputMode(

                  WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                }

            }

        });
        
        setWords();
        
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
		
        alwords = Util.getAlFromString(""+wflist.get(bookmark-1));
        
      //  alwords = Util.getAlFromString(Util.getRandomArrayString(wflist));
        
        TextView textView1 = (TextView) this.findViewById(R.id.textView1);
       // System.out.println(alwords.get(0));
       //System.out.println("--->Util.replace(alwords.get(0)"+alwords.get(0));
      // System.out.println("--->Util.replace(alwords.get(2)"+ alwords.get(2));
       
        SpannableString primaparte=new SpannableString(Util.replace(Util.replace(""+alwords.get(0),"."," ."), " " + alwords.get(2).toString() + " ", "________")) ;
        

        
        textView1.setText(primaparte );
        
        Spannable WordtoSpan = new SpannableString(""+alwords.get(1).toString().toUpperCase());   
        
       
         
       edittext.setText(WordtoSpan);
       //edittext.requestFocus();
       TextView textViewRight = (TextView) this.findViewById(R.id.textViewRight);
		textViewRight.setText("");
    
      
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
			String s = "";
			
		//	System.out.println("--->his.edittext.getText()"+this.edittext.getText().toString().toUpperCase());
		//	System.out.println("--->"+alwords.get(2).toString().toUpperCase());
			
			if (this.edittext.getText().toString().toUpperCase().equals(""+alwords.get(2).toString().toUpperCase())) {
				
				 	 TextView textViewRight = (TextView) this.findViewById(R.id.textViewRight);
			 		textViewRight.setText(new SpannableString("RIGHT:)"));
			 		
				// Toast.makeText(this,"RIGHT:) " , Toast.LENGTH_LONG).show();
			} else {
				TextView textView1 = (TextView) this.findViewById(R.id.textView1);
				 
				inserisciRisultato(""+ textView1.getText(), this.edittext.getText().toString(), ""+alwords.get(2).toString().toUpperCase() ,"wf" );
				
				
				 
				 TextView textViewRight = (TextView) this.findViewById(R.id.textViewRight);
			 		textViewRight.setText(new SpannableString("WRONG:(\nCORRECT: "+alwords.get(2).toString().toUpperCase()));
			 		
				// Toast.makeText(this,"WRONG:( " , Toast.LENGTH_LONG).show();
			}
			
			
	        Button btn = (Button) this.findViewById(R.id.buttonv) ;
	        btn.setTag("continue");
	        btn.setText(R.string.btncontinue);
	        
	        setNewBookmark();
	        
		} else if (tag.equals("continue")) {
			Button btn = (Button) this.findViewById(R.id.buttonv) ;
	        btn.setTag("check");
	        btn.setText(R.string.btncheck);
	    
			
	        setWords();
	        
	        
		}
	}}

	 /***
     * ant risposta (checked, unchecked)
     * res = 0 sbagliato, 1 corretto
     */
    protected void inserisciRisultato(String question, String youranswer, String rightanswer, String type) {
    	
    	falcofinder.android.fce.db.DataBaseHelper mDbHelper = new falcofinder.android.fce.db.DataBaseHelper(this);
    	
		SQLiteDatabase mDb = mDbHelper.getWritableDatabase();
		
		
		 
		 ContentValues initialValues = new ContentValues();
		
		  initialValues.put("question", ""+question);
		  initialValues.put("youranswer", ""+youranswer);
		  initialValues.put("rightanswer", ""+rightanswer);
		  initialValues.put("type", ""+type);
		 
		  

		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

			   //get current date time with Calendar()
			   Calendar cal = Calendar.getInstance();

			   
		      initialValues.put("data", dateFormat.format(cal.getTime()));

	   long result = mDb.insert(DataBaseHelper.TABLE_ERRORI, null, initialValues);
	   
	  
	   mDb.close();
	 	  
   	
   }


	 private void setNewBookmark() {
			// TODO Auto-generated method stub
			if (bookmark==this.wflist.size()) bookmark=1;
			else bookmark++;
			Util.saveBookmark(this, "fcewf", bookmark);
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
