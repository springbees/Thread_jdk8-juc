package org.laiqilin.juc;

import java.util.Arrays;
import java.util.List;

class User {
    private int id;
    private String username;
    private int age;

    public User() {
    }

    public User(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * @author laiqilin
 * @create 2019-10-11 22:32
 */
public class StreamDemo {
    public static void main(String[] args) {
        /**
         * 请按照给出数据，
         * 找出同时
         * 满足偶数ID
         * 且年龄大于24
         * 且用户名转为大写
         * 且用户名字母倒排序
         * 最后只输出一个用户名字
         */
        User u1 = new User (11, "a", 23);
        User u2 = new User (12, "b", 24);
        User u3 = new User (13, "c", 22);
        User u4 = new User (14, "d", 28);
        User u5 = new User (16, "e", 26);

        List<User> list = Arrays.asList (u1, u2, u3, u4, u5);
        list.stream ().filter (u -> {
            return u.getId () % 2 == 0; //过滤不是偶数
        }).filter (u -> {
            return u.getAge () > 24; //过滤小于年龄24
        }).map (u -> {
            return u.getUsername ().toUpperCase (); //返回一个对象
        }).sorted ((o1, o2) -> {
            return o2.compareTo (o1);
        }).limit (1).forEach (System.out::println);


    }
}
