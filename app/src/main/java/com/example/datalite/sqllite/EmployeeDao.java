package com.example.datalite.sqllite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datalite.R;
import com.example.datalite.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    SQLiteDatabase db;

    public EmployeeDao(Context context) {
        DBHelper helper=new DBHelper(context);
        db =helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Employee> get(String sql, String ...selectArgs){
        List<Employee>list=new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            Employee emp =new Employee();
            emp.setId(cursor.getString(cursor.getColumnIndex("id")));
            emp.setName(cursor.getString(cursor.getColumnIndex("id")));
            emp.setSalary(cursor.getFloat(cursor.getColumnIndex("id")));

            list.add(emp);
        }
        return list;
    }
    public List<Employee>getAll(){
        String sql = "SELECT * FROM sinhvien";
        return  get(sql);
    }
    public Employee getById(String id){
        String sql = "SELECT *FROM sinhvien WHERE id = ?";
        List<Employee>list=get(sql,id);
        return list.get(0);
    }
    public long insert(Employee emp){
        ContentValues values=new ContentValues();
        values.put("id", emp.getId());
        values.put("name", emp.getName());
        values.put("salary", emp.getSalary());

        return db.insert("sinhvien",null,values);

    }
    public long update(Employee emp){
        ContentValues values=new ContentValues();
        values.put("id", emp.getId());
        values.put("name", emp.getName());
        values.put("salary", emp.getSalary());

        return db.update("sinhvien",values,"id=",new  String[]{emp.getId()});

    }
    public int delete(String id){
        return db.delete("sinhvien","id",new String[]{id});
    }
}
