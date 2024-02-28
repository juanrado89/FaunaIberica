package com.albertorado.faunaiberica.entities;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class Animal implements Serializable, Parcelable {
    private String nombre;
    private int foto;
    private int resumen;

    public Animal(String nombre, int foto, int resumen) {
        this.nombre = nombre;
        this.foto = foto;
        this.resumen = resumen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getResumen() {
        return resumen;
    }

    public void setResumen(int resumen) {
        this.resumen = resumen;
    }

    protected Animal(Parcel in) {
        nombre = in.readString();
        foto = in.readInt();
        resumen = in.readInt();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(foto);
        dest.writeInt(resumen);
    }
}
