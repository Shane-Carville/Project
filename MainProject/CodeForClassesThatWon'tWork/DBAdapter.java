import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBAdapter {

	
	private static final String TAG = "DBAdapter";
	

	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	
	public static final String KEY_NAME = "title";
	public static final String KEY_STUDENTNUM = "releasedate";
	

	public static final int COL_NAME = 1;
	public static final int COL_STUDENTNUM = 2;

	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_TITLE, KEY_RELEASEDATE};
	

	public static final String DATABASE_NAME = "MyDb";
	public static final String DATABASE_TABLE = "mainTable";

	public static final int DATABASE_VERSION = 2;	
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			
			+ KEY_TITLE + " text not null, "
			+ KEY_RELEASEDATE + " integer not null"
			

			+ ");";
	
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;


	
	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	

	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	

	public void close() {
		myDBHelper.close();
	}
	

	public long insertRow(String title, String releasedate) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TITLE, title);
		initialValues.put(KEY_RELEASEDATE, releasedate);
		

		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			onCreate(_db);
		}
	}
}
