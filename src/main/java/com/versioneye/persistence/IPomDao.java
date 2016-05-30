package com.versioneye.persistence;

import com.versioneye.domain.Pom;

public interface IPomDao {

    void create(String url);

    boolean existsAlready(String url);

}
