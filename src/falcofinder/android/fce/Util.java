package falcofinder.android.fce;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.StringTokenizer;

import falcofinder.android.fce.db.DataBaseHelper;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Util {
	  /**
     * Key for shared preferences.
     */
    private static final String SHARED_PREFS = "fuehrerschein_PREFS";
	 /**
     * Helper method to get a SharedPreferences instance.
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFS, 0);
    }
    
    public static void setSharedPreferences(Context context, String key, String value) {
    	  
    	SharedPreferences prefs = Util.getSharedPreferences(context);
    	    prefs.edit().putString(key, value).commit();
    	    
    }
    
    
    /**
     * Returns the package name of this class.
     */
    public static String getPackageName() {
        return Util.class.getPackage().getName();
    }
    /*
    public static String getRandomArrayString(String[] list) {
		   
		  
		  ArrayList<Integer> l = new ArrayList<Integer>();
		   //filling up array
		   for (int i=0;i<list.length;i++) {
			   
			   l.add(i);
			   
		   }
		   
		   Collections.shuffle(l);
		   
		   
		return list[l.get(0)];

	   }*/
    
	   public static ArrayList getAlFromString(String s) {
	  
		  ArrayList l = new ArrayList();
		   //filling up array
		  StringTokenizer st = new StringTokenizer(s,";");
		  while (st.hasMoreElements()) {
			  l.add(st.nextElement());
		  }

		   
		   
		return l;

	   }
	   
	  
	   
	   public static ArrayList<String> getList(Context ctx, String sql , int numerocolonne) {
		   DataBaseHelper mDbHelper = new DataBaseHelper(ctx);
			
		ArrayList<String> result = new ArrayList<String>();
			
			
			 SQLiteDatabase mDb = mDbHelper.getWritableDatabase();
		 	
			
	  	 Cursor cur = mDb.rawQuery(sql  , null) ;
	  	
	  		  cur.moveToFirst();
	        
	  	  
	        while (cur.isAfterLast() == false) {        	 
	       	String record="";
	        	for (int i=0;i<numerocolonne;i++) {
	        		if (i>0) record +=";";
	        		record += cur.getString(i);
	        	}

	        	result.add(record);
	       	 
	       	    cur.moveToNext();
	        }
	        
	        
	        cur.close();
	       
	      
	       mDb.close();
	       
	       
		return result;
		   
	   }
    
	   public static int getBookmark(Context ctx, String sql , String tipo ) {
		   DataBaseHelper mDbHelper = new DataBaseHelper(ctx);
			
		ArrayList<String> result = new ArrayList<String>();
			
			
			 SQLiteDatabase mDb = mDbHelper.getWritableDatabase();
	
			
	  	 Cursor cur = mDb.rawQuery(sql  , new String[] { tipo }) ;
	  	
	  		  cur.moveToFirst();
	        
	  	  int posizione = 1;
	  	  
	        if (cur.isAfterLast() == false) {        	 
	       	
	        
	        		posizione = cur.getInt(0);
	        	
	       	    cur.moveToNext();
	        }
	        
	        
	        cur.close();
	       
	      
	       mDb.close();
	       
	       
		return posizione;
		   
	   }
	   
	   public static void saveBookmark(Context ctx, String tipo , int position) {
		   DataBaseHelper mDbHelper = new DataBaseHelper(ctx);
	    	
	    	
	    	SQLiteDatabase mDb = mDbHelper.getWritableDatabase();
			
			
			 ContentValues initialValues = new ContentValues();

			  initialValues.put("POSITION", position);

			  String where = "TIPO=?";
			  String[] whereArgs = {tipo};

		   long result = mDb.update(DataBaseHelper.TABLE_BOOKMARKS, initialValues, where, whereArgs);
		//   System.out.println("--->aggiornaBookmark quid"+this.quid+" - bookmark " + posizione + " - quizmode"+modedefault);
		//   System.out.println("--->aggiornaBookmark result " + result);
		   mDb.close();
		   
	   }
	   
	   public static String replace(String str, String pattern, String replace) {
			int s = 0;
			int e = 0;
			StringBuffer result = new StringBuffer();
	    
			while ((e = str.indexOf(pattern, s)) >= 0) {
				result.append(str.substring(s, e));
				result.append(replace);
				s = e+pattern.length();
			}
	        
	 
			result.append(str.substring(s));
			return result.toString();
		}
	   
	   public static String getVersion(PackageManager pm) {
			// TODO Auto-generated method stub
			PackageInfo pInfo;
			try {
				pInfo = pm.getPackageInfo(getPackageName(), 0);
				return pInfo.versionName;
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "";
			
			
		}

	public static Intent getIntentChat(Context applicationContext,
			String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
	   

}
