package com.example.malvinedward.e_sos;

/**
 * Created by Malvin Edward on 01/12/2015.
 */
public class DBContact {

    private Integer id;
    private String nama;
    private String nomerhp;

    public DBContact(){}

    public DBContact(int id, String name, String _phone_number) {
        this.id = id;
        this.nama = name;
        this.nomerhp = _phone_number;
    }
    public Integer getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getNama(){
        return nama;

    }
    public void setNama(String nama){
        this.nama=nama;
    }
    public String getNomerhp(){
        return nomerhp;

    }
    public void setNomerhp(String nomerhp){
        this.nomerhp=nomerhp;
    }

    @Override
    public String toString()
    {
        return "Contact "+ nama +" "+ nomerhp;
    }

}
