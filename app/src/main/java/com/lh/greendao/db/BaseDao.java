package com.lh.greendao.db;

import android.database.sqlite.SQLiteDatabase;

import com.lh.greendao.annotation.DbField;
import com.lh.greendao.annotation.DbTable;

import java.lang.reflect.Field;
import java.util.List;

/**
 * User: LuHao
 * Date: 2019/9/25 19:24
 * Describe:dao接口的实现
 */
public class BaseDao<T> implements IBaseDao<T> {

    //数据库索引
    private SQLiteDatabase sqLiteDatabase;

    //表名
    private String tableName;

    //持有操作数据库所对应的javaBean类型
    private Class<T> entityClass;

    //架构内部的逻辑不要提供构造方法给调用层使用，表明靠反射来获得
    public BaseDao(SQLiteDatabase sqLiteDatabase, Class<T> entityClass) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.entityClass = entityClass;
        //通过反射和注解获得表名
        //自动建表
        //如果没有添加注解，则通过反射去拿表明
        if (entityClass.getAnnotation(DbTable.class) != null) {
            //注解拿表名
            this.tableName = entityClass.getAnnotation(DbTable.class).value();
        } else {
            //反射拿表名
            this.tableName = entityClass.getSimpleName();
        }
        //创建表的sql语句
        String createTableSql = getCreateTableSql();
        this.sqLiteDatabase.execSQL(createTableSql);
    }

    //创建表的sql
    private String getCreateTableSql() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists ");
        stringBuffer.append(tableName).append("(");
        //反射得到所有的成员变量
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();//拿到成员的类型
            String fieldName;
            if (field.getAnnotation(DbField.class) != null) {
                //有注解
                fieldName = field.getAnnotation(DbField.class).value();
            } else {
                //通过反射
                fieldName = field.getName();
            }
            if (type == String.class) {
                stringBuffer.append(fieldName).append(" varchat,");
            } else if (type == Integer.class) {
                stringBuffer.append(fieldName).append(" INTEGER,");
            } else if (type == Long.class) {
                stringBuffer.append(fieldName).append(" BIGINT,");
            } else if (type == Double.class) {
                stringBuffer.append(fieldName).append(" DOUBLE,");
            } else if (type == Float.class) {
                stringBuffer.append(fieldName).append(" FLOAT,");
            } else if (type == Boolean.class) {
                stringBuffer.append(fieldName).append(" int,");
            } else if (type == byte[].class) {
                stringBuffer.append(fieldName).append(" BLOB,");
            } else {
                //其他未不支持
                continue;
            }
        }
        if (stringBuffer.charAt(stringBuffer.length() - 1) == ',') {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    @Override
    public long insert(T entity) {
        return 0;
    }

    @Override
    public long update(T entity, T where) {
        return 0;
    }

    @Override
    public int delete(T where) {
        return 0;
    }

    @Override
    public List<T> queryAll() {
        return null;
    }

    @Override
    public List<T> queryByWhere(T where) {
        return null;
    }

    @Override
    public List<T> queryByWhere(T where, String orderBy, int startIndex, int limit) {
        return null;
    }
}
