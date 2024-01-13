package manager;


import model.UserData;
import org.openqa.selenium.By;

public class UserHelper {
    private final ApplicationManager manager;

    public UserHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void createUserInAdressbook(UserData newUser) {
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(newUser.getFirstname());
        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(newUser.getMiddlename());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(newUser.getLastname());
        manager.driver.findElement(By.name("nickname")).click();
        manager.driver.findElement(By.name("nickname")).sendKeys(newUser.getNickname());
        manager.driver.findElement(By.name("title")).click();
        manager.driver.findElement(By.name("title")).sendKeys(newUser.getTitle());
        manager.driver.findElement(By.name("company")).click();
        manager.driver.findElement(By.name("company")).sendKeys(newUser.getCompany());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(newUser.getAddress());
        manager.driver.findElement(By.name("home")).click();
        manager.driver.findElement(By.name("home")).sendKeys(newUser.getHome());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(newUser.getMobile());
        manager.driver.findElement(By.name("work")).click();
        manager.driver.findElement(By.name("work")).sendKeys(newUser.getWork());
        manager.driver.findElement(By.name("fax")).click();
        manager.driver.findElement(By.name("fax")).sendKeys(newUser.getFax());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.cssSelector("body")).click();
        manager.driver.findElement(By.name("email")).sendKeys(newUser.getEmail());
        manager.driver.findElement(By.name("email2")).click();
        manager.driver.findElement(By.name("email2")).sendKeys(newUser.getEmail2());
        manager.driver.findElement(By.name("email3")).click();
        manager.driver.findElement(By.name("email3")).sendKeys(newUser.getEmail3());
        manager.driver.findElement(By.name("homepage")).click();
        manager.driver.findElement(By.name("homepage")).sendKeys("Home page");
        manager.driver.findElement(By.cssSelector("input:nth-child(75)")).click();

//        {
//            WebElement dropdown = driver.findElement(By.name("bday"));
//            dropdown.findElement(By.xpath("//option[. = '1']")).click();
//        }
//        driver.findElement(By.name("bmonth")).click();
//        {
//            WebElement dropdown = driver.findElement(By.name("bmonth"));
//            dropdown.findElement(By.xpath("//option[. = 'January']")).click();
//        }
//        driver.findElement(By.name("byear")).click();
//        driver.findElement(By.name("byear")).sendKeys("1990");
//        driver.findElement(By.cssSelector("input:nth-child(75)")).click();
//        driver.findElement(By.linkText("home page")).click();
    }

    public void openAddNewPage() {
        if (!manager.elementPresent(By.name("add new"))) {
            manager.driver.findElement(By.linkText("add new")).click();
        }
    }

    public boolean isUserPresent() {
        return manager.elementPresent(By.name("selected[]"));
    }
//    private final String firstname;
//    private final String middlename;
//    private final String lastname;
//    private final String nickname;
//    private final String title;
//    private final String company;
//    private final String address;
//    private final String home;
//    private final String mobile;
//    private final String work;
//    private final String fax;
//    private final String email;
//    private final String email2;
//    private final String email3;
//    private final String homepage;
//    private final String byear;
//
//    public UserHelper(String firstname, String middlename, String lastname, String nickname, String title,
//                    String company, String address, String home, String mobile, String work, String fax,
//                    String email, String email2, String email3, String homepage, String byear) {
//        this.firstname = firstname;
//        this.middlename = middlename;
//        this.lastname = lastname;
//        this.nickname = nickname;
//        this.title = title;
//        this.company = company;
//        this.address = address;
//        this.home = home;
//        this.mobile = mobile;
//        this.work = work;
//        this.fax = fax;
//        this.email = email;
//        this.email2 = email2;
//        this.email3 = email3;
//        this.homepage = homepage;
//        this.byear = byear;
//    }
//
//    public UserHelper(){
//        this("","","","","","","","","","",
//                "","","","","","");
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public String getMiddlename() {
//        return middlename;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getCompany() {
//        return company;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getHome() {
//        return home;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public String getWork() {
//        return work;
//    }
//
//    public String getFax() {
//        return fax;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getEmail2() {
//        return email2;
//    }
//
//    public String getEmail3() {
//        return email3;
//    }
//
//    public String getHomepage() {
//        return homepage;
//    }
//
//    public String getByear() {
//        return byear;
//    }
//
//    public UserHelper userWithFullNameAdressMobile(String firstname, String middlename, String lastname, String address,
//                                                 String mobile){
//        return new UserHelper(firstname, middlename, lastname, this.nickname, this.title,
//                this.company, address, this.home, mobile, this.work, this.fax,
//                this.email, this.email2, this.email3, this.homepage, this.byear);
//    }
//
//    public UserHelper userWithFullNameAdressMobileEmail(String firstname, String middlename,String lastname,
//                                                      String address,String mobile,String email){
//        return new UserHelper(firstname, middlename, lastname, this.nickname, this.title,
//                this.company, address, this.home, mobile, this.work, this.fax,
//                email, this.email2, this.email3, this.homepage, this.byear);
//    }
//
//    public UserHelper userWithFullNameAdressMobileEmailCompany(String firstname, String middlename,String lastname,
//                                                             String address,String mobile,String email,String company){
//        return new UserHelper(firstname, middlename, lastname, this.nickname, this.title,
//                company, address, this.home, mobile, this.work, this.fax,
//                email, this.email2, this.email3, this.homepage, this.byear);
//    }


}
