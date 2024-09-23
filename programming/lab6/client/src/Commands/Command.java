package Commands;

import java.io.IOException;
import java.util.Objects;

public abstract class Command implements Executable, Describable {
    private final String name;

    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean resolve(String name) {
        return name.equals(this.name);
    }

    public String getDescription() {
        return description;
    }


    public String getName() {
        return name;
    }


    public abstract boolean apply(String[] arguments) throws ClassNotFoundException, IOException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                '}';
    }
}
