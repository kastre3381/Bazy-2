package com.bd2.bd2.model;

import java.io.Serializable;

public class XmlToTableResults implements Serializable {

    private Long id_osoby;
    private String imie;
    private String nazwisko;
    private String data_urodzenia;
    private String data_smierci;
    private String plec;
    private Long id_ojca;
    private Long id_matki;
    private Long id_partnera;

    public XmlToTableResults(Long id_osoby, String imie, String nazwisko, String data_urodzenia, String data_smierci, String plec, Long id_ojca, Long id_matki, Long id_partnera) {
        this.id_osoby = id_osoby;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzenia = data_urodzenia;
        this.data_smierci = data_smierci;
        this.plec = plec;
        this.id_ojca = id_ojca;
        this.id_matki = id_matki;
        this.id_partnera = id_partnera;
    }

    public XmlToTableResults() {
    }

    public Long getId_osoby() {
        return id_osoby;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getData_urodzenia() {
        return data_urodzenia;
    }

    public String getData_smierci() {
        return data_smierci;
    }

    public String getPlec() {
        return plec;
    }

    public Long getId_ojca() {
        return id_ojca;
    }

    public Long getId_matki() {
        return id_matki;
    }

    public Long getId_partnera() {
        return id_partnera;
    }

    public void setId_osoby(Long id_osoby) {
        this.id_osoby = id_osoby;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setData_urodzenia(String data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public void setData_smierci(String data_smierci) {
        this.data_smierci = data_smierci;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public void setId_ojca(Long id_ojca) {
        this.id_ojca = id_ojca;
    }

    public void setId_matki(Long id_matki) {
        this.id_matki = id_matki;
    }

    public void setId_partnera(Long id_partnera) {
        this.id_partnera = id_partnera;
    }

    @Override
    public String toString() {
        return "XmlToTableResults{" +
                "id_osoby=" + id_osoby +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", data_urodzenia='" + data_urodzenia + '\'' +
                ", data_smierci='" + data_smierci + '\'' +
                ", plec='" + plec + '\'' +
                ", id_ojca=" + id_ojca +
                ", id_matki=" + id_matki +
                ", id_partnera=" + id_partnera +
                '}';
    }
}
