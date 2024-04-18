package com.toledo.control.models;


import java.util.ArrayList;

public class Control {
    private ArrayList<DataBase> listaEstudiantes=new ArrayList<>();
    private DataBase baseDeDatos;
    private MySQL mySQL;
    private  Access access;
    private SQLite sqLite;
    public Control(DataBase baseDeDatos1){
        this.baseDeDatos=baseDeDatos;
        mySQL=new MySQL();
        access=new Access();
        sqLite=new SQLite();

        listaEstudiantes.add(mySQL);
        listaEstudiantes.add(access);
        listaEstudiantes.add(sqLite);

    }

    public void save(Student student){
        for (DataBase baseDeDatos1:listaEstudiantes){
            baseDeDatos1.saveStudent(student);
        }
    }

    public void update(Student student){
        for (DataBase iBaseDeDatos:listaEstudiantes){
            iBaseDeDatos.updateStude(student);
        }
    }


    public MySQL getMySQL() {
        return mySQL;
    }

    public Access getAccess() {
        return access;
    }

    public SQLite getSQLite() {
        return sqLite;
    }
}
