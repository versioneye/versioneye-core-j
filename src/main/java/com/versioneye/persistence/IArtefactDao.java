package com.versioneye.persistence;

import com.versioneye.domain.Artefact;


public interface IArtefactDao {

    void create(final Artefact artefact);

    Artefact getBySha(String sha);

}
