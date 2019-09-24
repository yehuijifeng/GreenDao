package com.lh.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.lh.greendao.daoPackage.DaoMaster;
import com.lh.greendao.daoPackage.DaoSession;

/**
 * User: LuHao
 * Date: 2019/9/24 18:12
 * Describe:
 */
public class App extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test_dao.db");
        //获取可读写数据库对象
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        //获取dao对象
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        if (daoSession == null)
            initGreenDao();
        return daoSession;
    }
}
