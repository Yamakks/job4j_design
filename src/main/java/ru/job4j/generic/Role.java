package ru.job4j.generic;

public class Role extends Base {

private final String role;
private final int age;

public Role(String id, String role, int age) {
        super(id);
        this.role = role;
        this.age = age;

        }
public String getRole() {
        return role;
        }

        public int getAge() {
                return age;
        }
}
