package com.feit.feep.dbms.crud;

import com.feit.feep.dbms.crud.middle.CreateRepository;

public interface ICreate {
    CreateRepository getCreateDao();
}
