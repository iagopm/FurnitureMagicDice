package com.example.laleydeldadoappoficial.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.laleydeldadoappoficial.pojo.Dice;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class DiceDAO_Impl implements DiceDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDice;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDice;

  public DiceDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDice = new EntityInsertionAdapter<Dice>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `dices`(`id`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Dice value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__deletionAdapterOfDice = new EntityDeletionOrUpdateAdapter<Dice>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `dices` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Dice value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
  }

  @Override
  public void insert(Dice... dices) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDice.insert(dices);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Dice dice) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDice.handle(dice);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Dice> findAll() {
    final String _sql = "select * from dices";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final List<Dice> _result = new ArrayList<Dice>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Dice _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item = new Dice(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Dice findById(Integer id) {
    final String _sql = "select* from dices where id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final Dice _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _result = new Dice(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
