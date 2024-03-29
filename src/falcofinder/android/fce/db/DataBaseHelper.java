package falcofinder.android.fce.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import falcofinder.android.fce.Util;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;




public class DataBaseHelper extends SQLiteOpenHelper {
        /**
         * Database creation sql statement
         */
    //The Android's default system path of your application database.

 
	private static final int DATABASE_VERSION = 14;
	 public static final String TABLE_ERRORI = "ERRORI";
	 public static final String TABLE_MULTIPLE = "fcemultiple";
	 public static final String TABLE_PV = "fcepv";
	 public static final String TABLE_WF = "fcewf";
	 public static final String TABLE_WFLIST = "fcewflist";
	public static final String TABLE_BOOKMARKS = "BOOKMARKS";
	 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
    private  String DB_PATH = "/data/data/"
            + Util.getPackageName()
            + "/databases/";
	
	private static String DB_NAME = "fce.sqlite";//the extension may be .sqlite or .db

	
	
     /*   
	 * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {
 
    	super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
	//	System.out.println("--->dbExist"+dbExist);
		
    	if(dbExist){
    		
    		//System.out.println("--->dbExist dbExist dbExist 1");
    		
    		// By calling this method here onUpgrade will be called on a writeable database,
    		// but only if the version number has been bumped
    		this.getReadableDatabase();
    	//	System.out.println("--->dopo getReadableDatabase");
    	}
    	
    	
    	dbExist = checkDataBase();
		
    	//System.out.println("--->dbExist dbExist dbExist 2" + dbExist);
		
    	if(!dbExist){
   		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
        	//	System.out.println("---> copyDataBase");
    			copyDataBase();
 
    			
    		//	System.out.println("--->copyDataBase copyDataBase copyDataBase");
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    	//	System.out.println("myPath" + myPath);
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
    		System.out.println("database does't exist yet.");
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			
			myContext.deleteDatabase(DB_NAME);
			}
         
	}
 
 
	

    }



