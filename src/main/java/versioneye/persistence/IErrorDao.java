package versioneye.persistence;

import org.bson.types.ObjectId;
import versioneye.domain.Error;

public interface IErrorDao {

    void create(final versioneye.domain.Error error);

    Error getById(ObjectId id);

}
