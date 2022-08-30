/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.DemoCRUD.mhs;

public class Mahasiswa {
    private int nim;
    private String nama;
    private String programStudi;
    private String fakultas;

    public Mahasiswa(int nim, String nama, String programStudi, String fakultas) {
        this.nim = nim;
        this.nama = nama;
        this.programStudi = programStudi;
        this.fakultas = fakultas;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(String programStudi) {
        this.programStudi = programStudi;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }
   
    
}
