package manager;

public class GroupHelper {
    private final String name;
    private final String header;
    private final String footer;

    public GroupHelper(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public GroupHelper() {
        this("","","");
    }

    public String name() {
        return name;
    }

    public String header() {
        return header;
    }

    public String footer() {
        return footer;
    }

    public GroupHelper withName(String someName) {
        return new GroupHelper(someName, this.header,this.footer);
    }

    public GroupHelper withHeader(String someHeader) {
        return new GroupHelper(this.name, someHeader,this.footer);
    }

    public GroupHelper withFooter(String someFooter) {
        return new GroupHelper(this.name, this.header,someFooter);
    }
}
