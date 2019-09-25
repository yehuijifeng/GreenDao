package com.lh.greendao.db;

import java.util.List;

/**
 * User: LuHao
 * Date: 2019/9/25 19:18
 * Describe:
 */
public interface IBaseDao<T> {
    //插入
    long insert(T entity);

    //更新
    long update(T entity,T where);

    //删除
    int delete(T where);

    //查询全部
    List<T> queryAll();

    //条件查询
    List<T> queryByWhere(T where);

    //条件查询，排序，分页
    List<T> queryByWhere(T where,String orderBy,int startIndex,int limit);
}
