package org.geekbang.thinking.in.spring.ioc.overview.domain;

import org.geekbang.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author cf
 * @date 2020/8/15
 */
public class User implements BeanNameAware {
    private Long id;
    private String name;
    private City city;
    private City[] workCities;
    private List<City> lifeCities;
    private Resource configFileLocation;
    private transient String beanName;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public String toSampleString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }



    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
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

    public static String userCollection2String(Collection<User> users) {
        StringBuilder sbd = new StringBuilder();
        sbd.append("[");
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                sbd.append(user.toSampleString());
                sbd.append(", ");
            }
        }
        sbd.append("]");
        return sbd.toString();
    }

    @PostConstruct
    public void init() {
        System.out.println("user bean [" + beanName + "] initialization....");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("user bean [" + beanName + "] destroing...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
