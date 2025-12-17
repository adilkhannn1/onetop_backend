package onetop.platform.user.entity;

import jakarta.persistence.*;
import onetop.platform.event.entity.EventEntity;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private Integer age;
    private String city;
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<EventEntity> events;

    @OneToMany(mappedBy = "creator")
    private List<EventEntity> createdEvents;

    public UserEntity(){

    }

    public UserEntity(String email, String name, Integer age, String city, String password) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.city = city;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public List<EventEntity> getEvents(){
        return events;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEvents(List<EventEntity> events){
        this.events = events;
    }
}
