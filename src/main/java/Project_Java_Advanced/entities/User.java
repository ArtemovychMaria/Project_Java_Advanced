package Project_Java_Advanced.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User {
    int id;
    String email;
    String firstName;
    String surname;
    String role;

    public User(int id, String email, String firstName, String surname, String role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static User of(ResultSet resultSet) throws SQLException {
        int id=resultSet.getInt("id");
        String email=resultSet.getString("user_email");
        String firstName=resultSet.getString("user_name");
        String surname=resultSet.getString("user_surname");
        String role=resultSet.getString("user_role");
        return new User(id,email,firstName,surname,role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User)) return false;
        User user = (User) object;
        return getId() == user.getId() &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getRole(), user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getFirstName(), getSurname(), getRole());
    }
}
