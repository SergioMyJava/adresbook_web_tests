package model;

import java.util.Objects;


public class GroupData {
    private String id;
    private final String name;
    private final String header;
    private final String footer;

    public GroupData(String id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;

    }


    public GroupData() {
        this("", "", "", "");
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

    public String id() {
        return id;
    }


    public GroupData withName(String someName) {
        return new GroupData(this.id, someName,
                this.header, this.footer);
    }

    public GroupData withHeader(String someHeader) {
        return new GroupData(this.id, this.name, someHeader, this.footer);
    }

    public GroupData withFooter(String someFooter) {
        return new GroupData(this.id, this.name, this.header, someFooter);
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.name, this.header, this.footer);
    }

    public GroupData withchekboxValue(String chekboxValue) {
        return new GroupData(this.id, this.name,
                this.header, this.footer);
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                ", id='" + id + '\'' +
                ", chekboxValue='" + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return Objects.equals(header, groupData.header)
                && Objects.equals(footer, groupData.footer)
                && Objects.equals(id, groupData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, footer, id);
    }
}
