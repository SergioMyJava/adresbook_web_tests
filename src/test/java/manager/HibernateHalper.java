package manager;

import manager.hbm.GroupRecord;
import manager.hbm.UserRecord;
import model.GroupData;
import model.UserData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHalper extends HelperBase{
    private SessionFactory sessionFactory;


    public HibernateHalper(ApplicationManager manager) {
        super(manager);


    sessionFactory = new Configuration()
                    //.addAnnotatedClass(Book.class)
                    .addAnnotatedClass(GroupRecord.class)
            .addAnnotatedClass(UserRecord.class)
                    .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                    .setProperty(AvailableSettings.USER, "root")
                    .setProperty(AvailableSettings.PASS, "")
            .setProperty("hibernate.show_sql","true")
            .setProperty("hibernate.format_sql","true")
                    .buildSessionFactory();
    }

    static List<GroupData> groupConvertList(List<GroupRecord> records){
        List<GroupData> result = new ArrayList<>();
        for(var record : records){
            result.add(convertGroupRec(record));
        }
        return result;
    }

    private static GroupData convertGroupRec(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convertGroupRec(GroupData data) {
        var id = data.id() ;
        if(id.equals("")){
            id="0";
        }
        return new GroupRecord(Integer.parseInt(id) , data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList(){
        return groupConvertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();//
        }));
    }

    static List<UserData> userConvertList(List<UserRecord> records){
        List<UserData> result = new ArrayList<>();
        for(var record : records){
            result.add(convertUserRec(record));
        }
        return result;
    }

    private static UserData convertUserRec(UserRecord record) {
        return new UserData().UserDataFestLastMidlMob("" + record.id, record.firstname, record.middlename, record.lastname, record.mobile);
    }

    private static UserRecord convertUserRec(UserData data) {
        var id = data.id() ;
        if(id.equals("")){
            id="0";
        }
        return new UserRecord(Integer.parseInt(id) , data.getFirstname(), data.getMiddlename(), data.getLastname(), data.getMobile());
    }

    public List<UserData> getUserList(){
        return userConvertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from UserRecord", UserRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
           session.persist(convertGroupRec(groupData));
            session.getTransaction().commit();
        });
    }

    public void createUser(UserData data) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertUserRec(data));
            session.getTransaction().commit();
        });
    }

    public long getUserCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from UserRecord", Long.class).getSingleResult();
        });
    }

}
