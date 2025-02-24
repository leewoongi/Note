package com.woongi.data.local.room.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//val migration_1_2 = object : Migration(1, 2) {
//    override fun migrate(db: SupportSQLiteDatabase) {
//        // paths 테이블에 새로운 컬럼 추가
//        db.execSQL("ALTER TABLE paths ADD COLUMN new_column INTEGER DEFAULT 0 NOT NULL")
//        // lines 테이블에 새로운 컬럼 추가
//        db.execSQL("ALTER TABLE lines ADD COLUMN new_column INTEGER DEFAULT 0 NOT NULL")
//        // points 테이블에 새로운 컬럼 추가
//        db.execSQL("ALTER TABLE points ADD COLUMN new_column INTEGER DEFAULT 0 NOT NULL")
//    }
//}