package com.lh.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

/**
 * User: LuHao
 * Date: 2019/9/24 18:01
 * Describe:
 */
@Entity//告诉数据库，该类是要生成表的实体类
public class User {
    @Generated//编译后自动生成构造函数、方法等
    @Id(autoincrement = true)//自增
    private long id;
    @Property(nameInDb = "testName")//自定义字段名，外键不可以使用
    @NotNull//不能为null
    @Unique//不可重复
    private String name;
    private int age;
    private boolean six;
    @Transient//该字段不会被存储进数据库
    private String password;
    @Generated(hash = 1006192708)
    public User(long id, @NotNull String name, int age, boolean six) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.six = six;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean getSix() {
        return this.six;
    }
    public void setSix(boolean six) {
        this.six = six;
    }

}
