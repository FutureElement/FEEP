package com.feit.feep.dbms.crud;

import com.feit.feep.dbms.crud.middle.DeleteRepository;

public interface IDelete {
    DeleteRepository getDeleteDao();
}
