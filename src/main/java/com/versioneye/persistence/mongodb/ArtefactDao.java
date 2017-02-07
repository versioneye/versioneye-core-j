package com.versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.versioneye.domain.Artefact;
import com.versioneye.persistence.IArtefactDao;

public class ArtefactDao implements IArtefactDao {

    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(Artefact.ARTEFACTS);
    }

    public void create(Artefact artefact) {
        DBCollection artefacts = getCollection();
        artefacts.insert(artefact.getDBObject());
    }

    public Artefact getBySha(String sha) {
        BasicDBObject match = new BasicDBObject();
        match.put(Artefact.SHA_VALUE, sha);
        DBCursor cursor = getCollection().find(match);
        if (!cursor.hasNext())
            return null;
        DBObject artefactObj = cursor.next();
        Artefact artefact = new Artefact();
        artefact.updateFromDBObject(artefactObj);
        return artefact;
    }

    public MongoDB getMongoDB() {
        return mongoDB;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

}
