package CoreClasses;

import java.io.Serializable;

public class User  implements Validatable, Serializable {
    private int id;
    private final String name;
    private final String password;
    private final String ph;

    public User(int id, String name, String password, String ph) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.ph = ph;
    }

    public User copy(int id) {
        return new User(id, getName(), getPassword(), getPh());
    }

    public void setId(int a) {
        this.id = a;
    }

    public int getId() {return id;}

    public String getPh(){return ph;}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean validate() {
        return getName().length() < 40;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='********'" +
                '}';
    }


}
