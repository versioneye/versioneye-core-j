package com.versioneye.persistence;

import com.versioneye.domain.MavenRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 8/11/13
 * Time: 4:01 PM
 */
public interface IMavenRepostoryDao {

    void create(MavenRepository repository);

    void remove(MavenRepository repository);

    MavenRepository findByName(String name);

    List<MavenRepository> loadAll();

}
