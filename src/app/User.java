package app;

// レコードに変更できる?

public class User {
    private static int counter = 1;
    private int id;
    private String name;
    private String email;
    
    public User(String name, String email) {
        this.id = counter++;
        this.name = name;
        this.email = email;
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name=" + name + ", email=" + email + "}";
    }
    
    public static void setCounter(int value) {
        counter = value;
    }

}
