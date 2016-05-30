package com.versioneye.persistence;

import com.versioneye.domain.Versionchange;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/8/12
 * Time: 3:43 PM
 */
public interface IVersionchangeDao {

    Versionchange create(Versionchange versionchange);

}
