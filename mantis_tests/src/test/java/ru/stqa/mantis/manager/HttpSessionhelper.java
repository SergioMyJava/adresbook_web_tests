package ru.stqa.mantis.manager;


public class HttpSessionhelper extends HelperBase {

    OkHttpClient client;
    public HttpSessionhelper(ApplicationManager manager){
    super(manager);
    }

    public void login(String username, String password) {

    }

    public boolean isLoggedIn() {
        return false;
    }
}
