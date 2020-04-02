package Project_Java_Advanced.entities;


import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "user_email")
    String email;
    @Column(name = "user_name")
    String firstName;
    @Column(name = "user_surname")
    String surname;
    @Column(name = "user_role")
    String role;
    @Column(name = "user_password")
    String password;

    public User() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        int id;
        String email;
        String firstName;
        String surname;
        String role;
        String password;


    public Builder setId(int id) {
        this.id = id;
        return this;
    }

    public Builder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

    public User build() {
        User user=new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setSurname(surname);
        user.setRole(role);
        user.setPassword(password);
        return user;
    }
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
        if(role==null){
            this.role=UserRoles.USER.name();
        }else{
            this.role = role;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User of(ResultSet resultSet) throws SQLException {
        int id=resultSet.getInt("id");
        String email=resultSet.getString("user_email");
        String firstName=resultSet.getString("user_name");
        String surname=resultSet.getString("user_surname");
        String role=resultSet.getString("user_role");
        String password=resultSet.getString("user_password");
        return User.builder()
                .setId(id)
                .setEmail(email)
                .setFirstName(firstName)
                .setSurname(surname)
                .setRole(role)
                .setPassword(password)
                .build();
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
