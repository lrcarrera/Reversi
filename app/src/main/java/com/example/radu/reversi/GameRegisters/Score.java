package com.example.radu.reversi.GameRegisters;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Score implements Parcelable {

    private String alias;
    private String fecha;
    private int tamany;
    private String control_tiempo;
    private int negras;
    private int blancas;
    private int empleado;
    private String resultado;

    public Score(Cursor c){
        this.alias = c.getString(1);
        this.fecha = c.getString(2);
        this.tamany = c.getInt(3);
        this.control_tiempo = c.getString(4);
        this.negras = c.getInt(5);
        this.blancas = c.getInt(6);
        this.empleado = c.getInt(7);
        this.resultado = c.getString(8);
    }

    public String getAlias() {
        return alias;
    }

    public String getFecha() {
        return fecha;
    }

    public int getTamany() {
        return tamany;
    }

    public String getControlTiempo() {
        return control_tiempo;
    }

    public int getNegras() {
        return negras;
    }

    public int getBlancas() {
        return blancas;
    }

    public int getEmpleado() {
        return empleado;
    }

    public String getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "Score{" +
                "alias='" + alias + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tamany=" + tamany +
                ", control_tiempo='" + control_tiempo + '\'' +
                ", negras=" + negras +
                ", blancas=" + blancas +
                ", empleado=" + empleado +
                ", resultado='" + resultado + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.alias);
        dest.writeString(this.fecha);
        dest.writeInt(this.tamany);
        dest.writeString(this.control_tiempo);
        dest.writeInt(this.negras);
        dest.writeInt(this.blancas);
        dest.writeInt(this.empleado);
        dest.writeString(this.resultado);
    }

    protected Score(Parcel in) {
        this.alias = in.readString();
        this.fecha = in.readString();
        this.tamany = in.readInt();
        this.control_tiempo = in.readString();
        this.negras = in.readInt();
        this.blancas = in.readInt();
        this.empleado = in.readInt();
        this.resultado = in.readString();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel source) {
            return new Score(source);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };
}
