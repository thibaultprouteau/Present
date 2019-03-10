package com.example.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Attendance.db";
    /************* Table Course  ***************/

    public static final String COURSE_TABLE_NAME = "Course";

    public static final String COURSE_COLUMN_ID = "idCourse";
    public static final String COURSE_COLUMN_NAME = "courseName";
    public static final String COURSE_COLUMN_DESCRIPTION = "description";


    /*************** Table Lecture **********************/
    public static final String LECTURE_TABLE_NAME = "Lecture";

    public static final String LECTURE_COLUMN_ID = "idLecture";
    public static final String LECTURE_COLUMN_START_TIME = "startTime";
    public static final String LECTURE_COLUMN_END_TIME = "endTime";
    public static final String LECTURE_COLUMN_LECTURER = "lecturer";
    public static final String LECTURE_COLUMN_LOCATION = "location";
    public static final String LECTURE_COLUMN_COURSEID = "idCourse";
    public static final String LECTURE_COLUMN_GROUPID = "idGroup";

    /*************** Table Groups *******************/
    public static final String GROUPS_TABLE_NAME = "Groups";

    public static final String GROUPS_COLUMN_ID = "idGroup";
    public static final String GROUPS_COLUMN_NAME = "groupName";

    /***************** Table People *****************/
    public static final String PERSON_TABLE_NAME = "Person";


    public static final String PERSON_COLUMN_ID = "idPerson";
    public static final String PERSON_COLUMN_FIRST_NAME = "firstName";
    public static final String PERSON_COLUMN_LAST_NAME = "lastName";
    public static final String PERSON_COLUMN_GROUPID = "idGroup";


    /***************** Table Attendance ***********/

    public static final String ATTENDANCE_TABLE_NAME = "attendance";

    public static final String ATTENDANCE_COLUMN_LECTUREID = "idLecture";
    public static final String ATTENDANCE_COLUMN_PERSONID = "idPerson";
    public static final String ATTENDANCE_COLUMN_STATUS = "status";

    /*************** Types *******************/
    public static final String AUTOINCREMENT = "AUTOINCREMENT";
    public static final String INTEGER = "INTEGER";
    public static final String CHARLONG = "VARCHAR(255)";
    public static final String CHARSHORT = "VARCHAR(30)";
    public static final String DATE = "DATETIME";

    public static final String PRIMARYKEY = "PRIMARY KEY";
    public static final String SPACE = " ";
    public static final String COMMA = ",";
    public static final String NOTNULL = "NOT NULL";
    private static final String CREATION = "";
    private static final String DATABASEDEBUG = "databasedebug";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Course (idCourse  integer PRIMARY KEY, courseName text NOT NULL UNIQUE, description text NOT NULL);");
        Log.i(CREATION, "onCreate: TABLE COURSE CREATED");
        db.execSQL("CREATE TABLE Lecture (idLecture integer PRIMARY KEY, startTime text NOT NULL, endTime text NOT NULL, lecturer text NOT NULL, location text, idCourse integer NOT NULL, idGroup text NOT NULL, FOREIGN KEY(idGroup) REFERENCES Groups(idGroup), FOREIGN KEY(idCourse) REFERENCES Course(idCourse), UNIQUE(idCourse,idGroup) );");
        Log.i(CREATION, "onCreate: TABLE LECTURE CREATED ");
        db.execSQL("CREATE TABLE Groups (\n" +
                "  idGroup   integer PRIMARY KEY, \n" +
                "  groupName text NOT NULL UNIQUE);\n");
        db.execSQL("CREATE TABLE Person (\n" +
                "  idPerson  integer PRIMARY KEY, \n" +
                "  firstName text NOT NULL, \n" +
                "  lastName  text NOT NULL, \n" +
                "  idGroup   integer NOT NULL, \n" +
                " UNIQUE(firstName, lastName, idGroup), \n" +
                "  FOREIGN KEY(idGroup) REFERENCES Groups(idGroup));\n");
        db.execSQL("CREATE TABLE Attendance (\n" +
                "  idLecture integer NOT NULL, \n" +
                "  idPerson  integer NOT NULL, \n" +
                "  status    integer NOT NULL, \n" +
                "  UNIQUE(idLecture, idPerson), \n" +
                "  FOREIGN KEY (idLecture) REFERENCES Lecture(idLecture), \n" +
                "  FOREIGN KEY (idPerson) REFERENCES Person(idPerson), \n" +
                "  PRIMARY KEY (idLecture, \n" +
                "  idPerson));\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LECTURE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GROUPS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ATTENDANCE_TABLE_NAME);
        onCreate(db);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public void cleanDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + GROUPS_TABLE_NAME);
        db.execSQL("DELETE FROM " + COURSE_TABLE_NAME);
        db.execSQL("DELETE FROM " + LECTURE_TABLE_NAME);
        db.execSQL("DELETE FROM " + PERSON_TABLE_NAME);
    }

    public boolean insertCourse(String courseName, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_COLUMN_NAME, courseName);
        contentValues.put(COURSE_COLUMN_DESCRIPTION, description);
        db.insert(COURSE_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertLecture(String startTime, String endTime, String lecturer, String location, String idCourse, String idGroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LECTURE_COLUMN_START_TIME, startTime);
        contentValues.put(LECTURE_COLUMN_END_TIME, endTime);
        contentValues.put(LECTURE_COLUMN_LECTURER, lecturer);
        contentValues.put(LECTURE_COLUMN_LOCATION, location);
        contentValues.put(LECTURE_COLUMN_COURSEID, idCourse);
        contentValues.put(LECTURE_COLUMN_GROUPID, idGroup);
        db.insert(LECTURE_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertGroups(String groupName, Integer idGroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GROUPS_COLUMN_NAME, groupName);
        contentValues.put(GROUPS_COLUMN_ID, idGroup);
        db.insert(GROUPS_TABLE_NAME, null, contentValues);
        return true;
    }
    public boolean insertGroups(String groupName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GROUPS_COLUMN_NAME, groupName);
        db.insert(GROUPS_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertPerson(String firstName, String lastName, String idGroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_COLUMN_FIRST_NAME, firstName);
        contentValues.put(PERSON_COLUMN_LAST_NAME, lastName);
        contentValues.put(PERSON_COLUMN_GROUPID, idGroup);
        db.insert(PERSON_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertAttendance(String idLecture, String idPerson, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ATTENDANCE_COLUMN_LECTUREID, idLecture);
        contentValues.put(ATTENDANCE_COLUMN_PERSONID, idPerson);
        contentValues.put(ATTENDANCE_COLUMN_STATUS, status);
        db.insert(ATTENDANCE_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean deletePerson(String firstName, String lastName, String idGroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + PERSON_TABLE_NAME + " WHERE " + PERSON_COLUMN_FIRST_NAME + "=? AND " + PERSON_COLUMN_LAST_NAME + "=? AND " + PERSON_COLUMN_GROUPID + "=?", new String[]{firstName, lastName, idGroup});
        db.execSQL("DELETE FROM " + ATTENDANCE_TABLE_NAME + " WHERE " +
                ATTENDANCE_COLUMN_PERSONID + " NOT IN (SELECT " + PERSON_COLUMN_ID + " FROM " + PERSON_TABLE_NAME + ");");

        return true;
    }

    public boolean deleteGroup(String groupName) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PERSON_COLUMN_GROUPID, "-1");
        db.execSQL("DELETE FROM " + GROUPS_TABLE_NAME + " WHERE " + GROUPS_COLUMN_NAME + "=?", new String[]{groupName});
        db.update(PERSON_TABLE_NAME, cv, PERSON_COLUMN_GROUPID + "=?", new String[]{getGroupId(groupName)});
        db.execSQL("DELETE FROM " + LECTURE_TABLE_NAME + " WHERE " +
                LECTURE_COLUMN_GROUPID + " NOT IN " +
                "(SELECT " + GROUPS_COLUMN_NAME + " FROM " + GROUPS_TABLE_NAME + ");");
        db.execSQL("DELETE FROM " + ATTENDANCE_TABLE_NAME + " WHERE " +
                ATTENDANCE_COLUMN_LECTUREID + " NOT IN (SELECT " + LECTURE_COLUMN_ID + " FROM " + LECTURE_TABLE_NAME + ");");

        return true;
    }

    public boolean deleteCourse(String courseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PERSON_COLUMN_GROUPID, "-1");
        db.execSQL("DELETE FROM " + COURSE_TABLE_NAME + " WHERE " + COURSE_COLUMN_NAME + "=?", new String[]{courseName});
        db.execSQL("DELETE FROM " + LECTURE_TABLE_NAME + " WHERE " +
                LECTURE_COLUMN_COURSEID + " NOT IN " +
                "(SELECT " + COURSE_COLUMN_ID + " FROM " + COURSE_TABLE_NAME + ");", null);
        db.execSQL("DELETE FROM " + ATTENDANCE_TABLE_NAME + " WHERE " +
                ATTENDANCE_COLUMN_LECTUREID + " NOT IN (SELECT " + LECTURE_COLUMN_ID + " FROM " + LECTURE_TABLE_NAME + ");", null);
        return true;
    }

    public boolean deleteLecture(String lectureId) {
        //TODO delete lecture + attendance records
        return true;
    }


    public boolean truncateTable(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM TABLE " + tableName + "; VACUUM;");
        return true;
    }


    public Cursor getCourseName() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select " + COURSE_COLUMN_NAME + " from " + COURSE_TABLE_NAME, null);
        return res;
    }

    public Cursor getAll(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName, null);
        return res;
    }

    public Cursor getLecturesCursor(Integer idCourse) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + LECTURE_TABLE_NAME + " where " + LECTURE_COLUMN_COURSEID + " =?", new String[]{idCourse.toString()});
        return res;
    }

    public Cursor getGroupMembers(String idGroup) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + PERSON_TABLE_NAME + " where " + PERSON_COLUMN_GROUPID + " =?", new String[]{idGroup.toString()});
        return res;
    }

    public Cursor getAttendanceRecordsCursor(Integer idLecture) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + PERSON_TABLE_NAME + " where " + PERSON_COLUMN_GROUPID + " =?", new String[]{idLecture.toString()});
        return res;
    }


    public ArrayList<String> getAllCoursesNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor courses = getCourseName();
        if (courses.moveToFirst()) {
            do {
                arrayList.add(new String(
                        courses.getString(courses.getColumnIndex(COURSE_COLUMN_NAME))));
            } while (courses.moveToNext());
        }
        return arrayList;
    }


    public String getCourseId(String courseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor course = this.getAll(COURSE_TABLE_NAME);
        HashMap<String, String> coursesWithIds = new HashMap<>();
        if (course.moveToFirst()) {
            do {
                coursesWithIds.put(course.getString(course.getColumnIndex(COURSE_COLUMN_NAME)), course.getString(course.getColumnIndex(COURSE_COLUMN_ID)));
            } while (course.moveToNext());
        }
        return coursesWithIds.get(courseName);
    }

    public ArrayList<Lecture> getLectures(Integer idCourse) {
        ArrayList<Lecture> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor lectures = getLecturesCursor(idCourse);
        if (lectures.moveToFirst()) {
            do {
                arrayList.add(new Lecture(
                        lectures.getInt(lectures.getColumnIndex(LECTURE_COLUMN_ID)),
                        lectures.getString(lectures.getColumnIndex(LECTURE_COLUMN_START_TIME)),
                        lectures.getString(lectures.getColumnIndex(LECTURE_COLUMN_END_TIME)),
                        lectures.getString(lectures.getColumnIndex(LECTURE_COLUMN_LECTURER)),
                        lectures.getString(lectures.getColumnIndex(LECTURE_COLUMN_LOCATION)),
                        lectures.getInt(lectures.getColumnIndex(LECTURE_COLUMN_GROUPID)),
                        lectures.getInt(lectures.getColumnIndex(LECTURE_COLUMN_COURSEID))));
            } while (lectures.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<Groups> getGroups() {
        ArrayList<Groups> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor groups = getAll(GROUPS_TABLE_NAME);
        if (groups.moveToFirst()) {
            do {
                arrayList.add(new Groups(
                        groups.getInt(groups.getColumnIndex(GROUPS_COLUMN_ID)),
                        groups.getString(groups.getColumnIndex(GROUPS_COLUMN_NAME))));
            } while (groups.moveToNext());
        }
        return arrayList;
    }

    public String getGroupId(String groupName) {
        HashMap<String, String> groups = new HashMap<>();
        for (Groups g : getGroups()) {
            groups.put(g.getGroupName(), g.getIdGroup().toString());
        }
        return (groups.get(groupName));
    }

    public String getGroupName(String groupId) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor groups = db.rawQuery("SELECT groupName from Groups WHERE idGroup =?", new String[]{groupId});
        if (groups.moveToFirst()) {
            return (groups.getString(groups.getColumnIndex(GROUPS_COLUMN_NAME)));
        } else throw new CursorIndexOutOfBoundsException("id not found in table");
    }

    public ArrayList<Person> getPeople() {
        ArrayList<Person> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor people = getAll(PERSON_TABLE_NAME);
        if (people.moveToFirst()) {
            do {
                arrayList.add(new Person(
                        people.getInt(people.getColumnIndex(PERSON_COLUMN_ID)),
                        people.getString(people.getColumnIndex(PERSON_COLUMN_FIRST_NAME)),
                        people.getString(people.getColumnIndex(PERSON_COLUMN_LAST_NAME)),
                        people.getInt(people.getColumnIndex(PERSON_COLUMN_GROUPID))));
            } while (people.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<Person> getPeople(String idGroup) {
        ArrayList<Person> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor people = getGroupMembers(idGroup);
        if (people.moveToFirst()) {
            do {
                arrayList.add(new Person(
                        people.getInt(people.getColumnIndex(PERSON_COLUMN_ID)),
                        people.getString(people.getColumnIndex(PERSON_COLUMN_FIRST_NAME)),
                        people.getString(people.getColumnIndex(PERSON_COLUMN_LAST_NAME)),
                        people.getInt(people.getColumnIndex(PERSON_COLUMN_GROUPID))));
            } while (people.moveToNext());
        }
        return arrayList;
    }

    public ArrayList<AttendanceRecord> getAttendanceRecords(Integer idLecture) {
        ArrayList<AttendanceRecord> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor aR = getAttendanceRecordsCursor(idLecture);
        if (aR.moveToFirst()) {
            do {
                arrayList.add(new AttendanceRecord(
                        aR.getInt(aR.getColumnIndex(ATTENDANCE_COLUMN_PERSONID)),
                        aR.getInt(aR.getColumnIndex(ATTENDANCE_COLUMN_PERSONID)),
                        aR.getString(aR.getColumnIndex(ATTENDANCE_COLUMN_STATUS))));
            } while (aR.moveToNext());
        }
        return arrayList;

    }






}
