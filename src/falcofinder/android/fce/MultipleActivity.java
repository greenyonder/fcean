package falcofinder.android.fce;

import java.io.IOException;
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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MultipleActivity  extends Activity implements AdListener {
    /** Called when the activity is first created. */
	
	  private static String MY_AD_UNIT_ID = "a14f79f6b947edc"; 
		private AdView adView;

		ArrayList list;
	
	ArrayList alwords;
	int bookmark=1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fcemultiple);

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
        list = Util.getList(this, getString(R.string.sqlmultiple), 3);
        		 //getResources().getStringArray(R.array.multiple);
        
        bookmark = Util.getBookmark(this, getString(R.string.sqlbookmark) , "fcemultiple");
        //alwords = Util.getAlFromString(Util.getRandomArrayString(list));
        alwords = Util.getAlFromString(""+list.get(bookmark-1));
        
        TextView textView1 = (TextView) this.findViewById(R.id.textView1);
        
     //   System.out.println("---->alwords"+alwords);
   	//  System.out.println("---->alwords"+alwords.get(5));
   //	  
     Integer poscorretta = Integer.parseInt(""+alwords.get(5));
     
     textView1.setText(new SpannableString(Util.replace(Util.replace(""+alwords.get(0),"."," ."), " "+alwords.get(poscorretta) + " ","________")) );
     
     RadioButton rb1 = (RadioButton)findViewById(R.id.radio1);
     rb1.setText(new SpannableString("A)"+ alwords.get(1)));
	 
	 RadioButton rb2 = (RadioButton)findViewById(R.id.radio2);
	 rb2.setText(new SpannableString("B)"+ alwords.get(2)));

	 RadioButton rb3 = (RadioButton)findViewById(R.id.radio3);
	 rb3.setText(new SpannableString("C)"+ alwords.get(3)));	 
	 
	 RadioButton rb4 = (RadioButton)findViewById(R.id.radio4);
	 rb4.setText(new SpannableString("D)"+ alwords.get(4)));	 
	 
	 RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
	 rg.clearCheck();
	 
	 Button buttonv = (Button)findViewById(R.id.buttonv);
	 buttonv.setText(R.string.btncheck);
	 buttonv.setTag("check");
	 
     TextView textViewRight = (TextView) this.findViewById(R.id.textViewRight);
		textViewRight.setText("");
	 
	}
    
	

	public final void onClick(View v) {
	 	
    	String selview = "";
    	if (v.getTag()!=null) selview = ""+v.getTag();
    	
    	//System.out.println("--->selview"+selview);
    	
    	if (selview.equals("back")) {
    		
    		super.finish();
    	}
    	
 	    if (selview.equals("check")){
 	       
 	     RadioButton b1 = (RadioButton)findViewById(R.id.radio1);
 	    RadioButton b2 = (RadioButton)findViewById(R.id.radio2);
 	   RadioButton b3 = (RadioButton)findViewById(R.id.radio3);
 	  RadioButton b4 = (RadioButton)findViewById(R.id.radio4);
 	
 	   
 	   String risposta = "";

 	  TextView textViewRight = (TextView) this.findViewById(R.id.textViewRight);
		
		
 	  if (b1.isChecked() && "1".equals(alwords.get(5))) {
 		 textViewRight.setText(new SpannableString("RIGHT:)"));
// 		  Toast.makeText(this,"RIGHT:) " , Toast.LENGTH_LONG).show();
 	  } else if (b2.isChecked() && "2".equals(alwords.get(5))) {
 //		 Toast.makeText(this,"RIGHT:) " , Toast.LENGTH_LONG).show();
 		 textViewRight.setText(new SpannableString("RIGHT:)"));
 	  } else if (b3.isChecked() && "3".equals(alwords.get(5))) {
 		 textViewRight.setText(new SpannableString("RIGHT:)"));
 		 //Toast.makeText(this,"RIGHT:) " , Toast.LENGTH_LONG).show();
 	  } else if (b4.isChecked() && "4".equals(alwords.get(5))) {
 		 textViewRight.setText(new SpannableString("RIGHT:)"));
// 		 Toast.makeText(this,"RIGHT:) " , Toast.LENGTH_LONG).show(); 		 
 	  } else {
 //		 Toast.makeText(this,"WRONG:( " , Toast.LENGTH_LONG).show();
 		// textViewRight.setText(new SpannableString("WRONG:("));
 		 String myanswer="";
 		if (b1.isChecked()) myanswer = ""+alwords.get(1);
 		if (b2.isChecked()) myanswer = ""+alwords.get(2);
 		if (b3.isChecked()) myanswer = ""+alwords.get(3);
 		if (b4.isChecked()) myanswer = ""+alwords.get(4);
 		//TextView textViewRight = (TextView) this.findViewById(R.id.textViewRight);
 		textViewRight.setText(new SpannableString("WRONG:(\nCORRECT:"+alwords.get(Integer.parseInt(""+alwords.get(5)))));
 		
 		TextView textView1 = (TextView) this.findViewById(R.id.textView1);
 		inserisciRisultato(textView1.getText().toString(), myanswer, ""+alwords.get(Integer.parseInt(""+alwords.get(5))) ,"multiple" );
 	  }
 
		 	 Button buttonv = (Button)findViewById(R.id.buttonv);
		 	 buttonv.setText(R.string.btncontinue);
			 buttonv.setTag("continue");
			 
			 setNewBookmark();
 	    }
 	    
 	   if (selview.equals("continue")){
		 	 Button buttonv = (Button)findViewById(R.id.buttonv);
		 	 buttonv.setText(R.string.btncheck);
			 buttonv.setTag("check");
			 setWords();
 	   }
	 }
	
	
	 private void setNewBookmark() {
		// TODO Auto-generated method stub
		if (bookmark==this.list.size()) bookmark=1;
		else bookmark++;
		Util.saveBookmark(this, "fcemultiple", bookmark);
	}

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
