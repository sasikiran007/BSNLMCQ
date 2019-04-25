package com.apps.kanchan.mcq.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.apps.kanchan.mcq.POJOs.StudyCard;

import java.util.ArrayList;

/**
 * Created by root on 12/2/19.
 */

public class StudyNotesDBHelper  extends SQLiteOpenHelper {

    private static String KEY_ID = "_id";
    private static String KEY_VERSION = "version";
    private static String KEY_EXAM = "exam";
    private static String KEY_STREAM = "stream";
    private static String KEY_CHAPTER = "chapter";
    private static String KEY_QUESTION = "question";
    private static String KEY_ANSWER = "answer";
    private static String KEY_ISREAD = "isRead";
    private static String KEY_CARDNUMBER = "cardNumber";
    private static String KEY_AQUAINTANCE = "acquaintance";
    private static StudyNotesDBHelper sInstance;

    public static String DATABASE_NAME = "studyNotes";
    private static int DATABASE_VERSION = 1;
    private static String TABLE_NAME_E3E4_MANAGEMENT_NOTES = "e3e4Management";
    private static String CREATE_TABLE_E3E4_MANAGEMENT = "CREATE TABLE " + TABLE_NAME_E3E4_MANAGEMENT_NOTES + "( "
            + KEY_ID + " INTEGER PRIMARY KEY," + " "
            + KEY_VERSION + " INTEGER," + " "
            + KEY_EXAM + " TEXT," + " "
            + KEY_STREAM + " TEXT," + " "
            + KEY_CHAPTER + " TEXT," + " "
            + KEY_QUESTION + " TEXT," + " "
            + KEY_ANSWER + " TEXT," + " "
            + KEY_ISREAD + " INTEGER," + " "
            + KEY_CARDNUMBER + " INTEGER," + " "
            + KEY_AQUAINTANCE + " INTEGER"
            + " )";

    private StudyNotesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static StudyNotesDBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new StudyNotesDBHelper(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE_E3E4_MANAGEMENT);
            Log.i("Info", TABLE_NAME_E3E4_MANAGEMENT_NOTES + " created");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_E3E4_MANAGEMENT_NOTES);
            Log.i("Info", TABLE_NAME_E3E4_MANAGEMENT_NOTES + " table dropped!!");
        }
        onCreate(sqLiteDatabase);
    }

    public void insertData(ArrayList<StudyCard> studyCards) {
        SQLiteDatabase database = getWritableDatabase();
        for (StudyCard studyCard : studyCards) {
            if (studyCard.getVersion() != DATABASE_VERSION) {
                onUpgrade(getReadableDatabase(), DATABASE_VERSION, studyCard.getVersion());
                Log.i("Info", "Version change , new db creating...");
            }
            if (studyCard.getExamName().equals("E3 TO E4") && studyCard.getmStreamName().equals("Management")) {
                try {
                    database.insertWithOnConflict(TABLE_NAME_E3E4_MANAGEMENT_NOTES, null, getContentValues(studyCard), SQLiteDatabase.CONFLICT_REPLACE);
                    Log.i("Info", " Row inserted");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.i("Info", "StudyNotesDBHelper : Error");
                return;
            }
        }
    }

    public ContentValues getContentValues(StudyCard studyCard) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_VERSION, studyCard.getVersion());
        contentValues.put(KEY_EXAM, studyCard.getExamName());
        contentValues.put(KEY_STREAM, studyCard.getmStreamName());
        contentValues.put(KEY_CHAPTER, studyCard.getChapterName());
        contentValues.put(KEY_QUESTION, studyCard.getQuestion());
        contentValues.put(KEY_ANSWER, studyCard.getAnswer());
        contentValues.put(KEY_ISREAD, studyCard.isRead());
        contentValues.put(KEY_CARDNUMBER, studyCard.getCardNumber());
        contentValues.put(KEY_AQUAINTANCE, studyCard.getAquaintance());
        return contentValues;
    }

    public ArrayList<StudyCard> getStudyCards(String exam, String stream) {
        ArrayList<StudyCard> studyCards = new ArrayList<>();
        if (exam.equals("E3 TO E4") && stream.equals("Management")) {
            String selectQuery = "SELECT * FROM " + TABLE_NAME_E3E4_MANAGEMENT_NOTES + " WHERE " +
                    KEY_EXAM + " = '" + exam + "' AND " + KEY_STREAM + " = '" + stream + "'";
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c != null) {
                StudyCard studyCard =  new StudyCard();
                if (c.moveToFirst()) {
                    do {
                        studyCard.setVersion(c.getInt(c.getColumnIndex(KEY_VERSION)));
                        studyCard.setExamName(c.getString(c.getColumnIndex(KEY_EXAM)));
                        studyCard.setmStreamName(c.getString(c.getColumnIndex(KEY_STREAM)));
                        studyCard.setChapterName(c.getString(c.getColumnIndex(KEY_CHAPTER)));
                        studyCard.setQuestion(c.getString(c.getColumnIndex(KEY_QUESTION)));
                        studyCard.setAnswer(c.getString(c.getColumnIndex(KEY_ANSWER)));
                        studyCard.setIsRead(c.getInt(c.getColumnIndex(KEY_STREAM)));
                        studyCard.setCardNumber(c.getInt(c.getColumnIndex(KEY_CARDNUMBER)));
                        studyCard.setAquaintance(c.getInt(c.getColumnIndex(KEY_AQUAINTANCE)));
                        studyCards.add(studyCard);
                    } while (c.moveToNext());
                }
            }
            c.close();
        }
        return studyCards;
    }

}
