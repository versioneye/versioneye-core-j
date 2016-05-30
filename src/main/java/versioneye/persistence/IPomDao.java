package versioneye.persistence;


import versioneye.domain.Pom;

public interface IPomDao {

    void create(String url);

    boolean existsAlready(String url);

}
