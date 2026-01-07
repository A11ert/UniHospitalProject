public abstract class Person {
    protected int id;
    protected String name;

    public Person() {}

    public Person(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {return id;}
    public boolean setId(int id) {
        if (id <= 0) {
            return false;
        }
        this.id = id;
        return true;
    }

    public String getName() {return name;}
    public boolean setName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        this.name = name;
        return true;
    }

    // Polymorphic method: each subclass MUST define what it is
    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + " " + name + ", ID :  " + id + ".";
    }
}
