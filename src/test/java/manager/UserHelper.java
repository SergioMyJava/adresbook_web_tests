package manager;


public class UserHelper {
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

    public UserHelper(String firstname, String middlename, String lastname, String nickname, String title,
                    String company, String address, String home, String mobile, String work, String fax,
                    String email, String email2, String email3, String homepage, String byear) {
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
    }

    public UserHelper(){
        this("","","","","","","","","","",
                "","","","","","");
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

    public UserHelper userWithFullNameAdressMobile(String firstname, String middlename, String lastname, String address,
                                                 String mobile){
        return new UserHelper(firstname, middlename, lastname, this.nickname, this.title,
                this.company, address, this.home, mobile, this.work, this.fax,
                this.email, this.email2, this.email3, this.homepage, this.byear);
    }

    public UserHelper userWithFullNameAdressMobileEmail(String firstname, String middlename,String lastname,
                                                      String address,String mobile,String email){
        return new UserHelper(firstname, middlename, lastname, this.nickname, this.title,
                this.company, address, this.home, mobile, this.work, this.fax,
                email, this.email2, this.email3, this.homepage, this.byear);
    }

    public UserHelper userWithFullNameAdressMobileEmailCompany(String firstname, String middlename,String lastname,
                                                             String address,String mobile,String email,String company){
        return new UserHelper(firstname, middlename, lastname, this.nickname, this.title,
                company, address, this.home, mobile, this.work, this.fax,
                email, this.email2, this.email3, this.homepage, this.byear);
    }
}
