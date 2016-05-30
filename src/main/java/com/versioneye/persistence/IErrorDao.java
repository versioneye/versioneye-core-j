package com.versioneye.persistence;

import org.bson.types.ObjectId;
import com.versioneye.domain.Error;

public interface IErrorDao {

    void create(final com.versioneye.domain.Error error);

    Error getById(ObjectId id);

}
