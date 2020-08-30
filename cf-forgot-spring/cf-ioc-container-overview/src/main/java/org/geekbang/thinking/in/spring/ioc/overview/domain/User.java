package org.geekbang.thinking.in.spring.ioc.overview.domain;

/**
 * @author cf
 * @date 2020/8/15
 */
public class User {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static User create(Long id, String name) {
        return new User() {
            {
                setId(id);
                setName(name);
            }
        };
    }

    public static User create() {
        return new User() {
            {
                setId(1L);
                setName("coding life");
            }
        };
    }
}
