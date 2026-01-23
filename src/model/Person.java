package model;

public abstract class Person {
    protected int id;
    protected String name;

    public Person() {}

    public Person(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {return id;}
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id can't be negative");
        }
        this.id = id;
    }

    public String getName() {return name;}
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be blank or null");
        }
        this.name = name;
    }

    // polymorphic method
    public abstract String getRole();
    public abstract void work();

    @Override
    public String toString() {
        return getRole() + " " + name + ", ID :  " + id + ".";
    }
}
