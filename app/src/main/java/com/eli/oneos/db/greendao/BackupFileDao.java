package com.eli.oneos.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.eli.oneos.db.greendao.BackupFile;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table BACKUP_FILE.
*/
public class BackupFileDao extends AbstractDao<BackupFile, Long> {

    public static final String TABLENAME = "BACKUP_FILE";

    /**
     * Properties of entity BackupFile.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Uid = new Property(1, long.class, "uid", false, "UID");
        public final static Property Path = new Property(2, String.class, "path", false, "PATH");
        public final static Property Auto = new Property(3, Boolean.class, "auto", false, "AUTO");
        public final static Property Type = new Property(4, Integer.class, "type", false, "TYPE");
        public final static Property Priority = new Property(5, Integer.class, "priority", false, "PRIORITY");
        public final static Property Time = new Property(6, Long.class, "time", false, "TIME");
        public final static Property Count = new Property(7, Long.class, "count", false, "COUNT");
    };


    public BackupFileDao(DaoConfig config) {
        super(config);
    }
    
    public BackupFileDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'BACKUP_FILE' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'UID' INTEGER NOT NULL ," + // 1: uid
                "'PATH' TEXT NOT NULL ," + // 2: path
                "'AUTO' INTEGER," + // 3: auto
                "'TYPE' INTEGER," + // 4: type
                "'PRIORITY' INTEGER," + // 5: priority
                "'TIME' INTEGER," + // 6: time
                "'COUNT' INTEGER);"); // 7: count
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'BACKUP_FILE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BackupFile entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getUid());
        stmt.bindString(3, entity.getPath());
 
        Boolean auto = entity.getAuto();
        if (auto != null) {
            stmt.bindLong(4, auto ? 1l: 0l);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(5, type);
        }
 
        Integer priority = entity.getPriority();
        if (priority != null) {
            stmt.bindLong(6, priority);
        }
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(7, time);
        }
 
        Long count = entity.getCount();
        if (count != null) {
            stmt.bindLong(8, count);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public BackupFile readEntity(Cursor cursor, int offset) {
        BackupFile entity = new BackupFile( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // uid
            cursor.getString(offset + 2), // path
            cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0, // auto
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // type
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // priority
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // time
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7) // count
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BackupFile entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUid(cursor.getLong(offset + 1));
        entity.setPath(cursor.getString(offset + 2));
        entity.setAuto(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0);
        entity.setType(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setPriority(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setTime(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setCount(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BackupFile entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(BackupFile entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
