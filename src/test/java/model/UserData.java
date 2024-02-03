package model;


import java.util.Objects;

public class UserData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String home;
    private final String mobile;
    private final String work;
    private final String fax;
    private final String email;
    private final String email2;
    private final String email3;
    private final String homepage;
    private final String byear;
    private final String id;
    private final String photo;

    public UserData(String id, String firstname, String middlename, String lastname, String nickname, String title,
                    String company, String address, String home, String mobile, String work, String fax,
                    String email, String email2, String email3, String homepage, String byear,String photo) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.fax = fax;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
        this.byear = byear;
        this.photo = photo;
    }
    public UserData userWithFullNameAdressMobile(String firstname, String lastname,
                                                 String address, String mobile) {
        return new UserData("", firstname, this.middlename, lastname, this.nickname, this.title,
                this.company, address, this.home, mobile, this.work, this.fax,
                this.email, this.email2, this.email3, this.homepage, this.byear, this.photo);
    }
    public UserData() {
        this("", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "","");
    }

    public UserData withId(String id) {
        return new UserData(id, this.firstname, this.middlename, this.lastname, this.nickname, this.title,
                this.company, address, this.home, mobile, this.work, this.fax,
                this.email, this.email2, this.email3, this.homepage, this.byear, this.photo);
    }

    public UserData withFirstnameLastname(String firstname,String lastname) {
        return new UserData(this.id, firstname, this.middlename, lastname, this.nickname, this.title,
                this.company, address, this.home, mobile, this.work, this.fax,
                this.email, this.email2, this.email3, this.homepage, this.byear, this.photo);
    }

    public UserData withPhoto(String photo) {
        return new UserData(this.id, firstname, this.middlename, lastname, this.nickname, this.title,
                this.company, address, this.home, mobile, this.work, this.fax,
                this.email, this.email2, this.email3, this.homepage, this.byear, photo);
    }

    public String id() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getByear() {
        return byear;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(firstname, userData.firstname) && Objects.equals(lastname, userData.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, middlename);
    }
}

