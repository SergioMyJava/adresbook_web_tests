package model;

public class GroupData {
    private final String name;
    private final String header;
    private final String footer;

    public GroupData(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public GroupData() {
        this("", "", "");
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

    public GroupData withName(String someName) {
        return new GroupData(someName, this.header, this.footer);
    }

    public GroupData withHeader(String someHeader) {
        return new GroupData(this.name, someHeader, this.footer);
    }

    public GroupData withFooter(String someFooter) {
        return new GroupData(this.name, this.header, someFooter);
    }
}
