package com.feit.feep.dbms.crud;

import com.feit.feep.dbms.crud.middle.UpdateRepository;

public interface IUpdate {
    UpdateRepository getUpdateDao();
}
