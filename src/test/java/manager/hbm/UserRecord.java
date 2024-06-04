package manager.hbm;

import jakarta.persistence.*;


@Entity
@Table(name = "addressbook")
public class UserRecord {
    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "middlename")
    public String middlename;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "mobile")
    public String 	mobile;
    @Column(name = "home")
    public  String home;
    @Column(name = "work")
    public  String work;
    @Column(name = "phone2")
    public  String secondary;

    @Column(name = "email")
    public  String email="";
    @Column(name = "email2")
    public  String email2="";
    @Column(name = "email3")
    public  String email3="";

    @Column(name = "nickname")
    public  String nickname="";
    @Column(name = "title")
    public  String title="";
    @Column(name = "company")
    public  String company="";
    @Column(name = "address")
    public  String address="";
    @Column(name = "fax")
    public  String fax="";

    @Column(name = "homepage")
    public  String homepage="";
    @Column(name = "byear")
    public  String byear="";
    @Column(name = "photo")
    public  String photo;


    public UserRecord() {
    }

    public UserRecord(int id, String firstname, String middlename
            ,String lastname, String mobile,String home
            ,String work,String secondary, String email
            ,String email2,String email3) {
        this.id=id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.middlename=middlename;
        this.mobile = mobile;
        this.home = home;
        this.work = work;
        this.secondary = secondary;
        this.email= email;
        this.email2 = email2;
        this.email3 = email3;
    }

}



