package com.baslogh.baslogh.dao;


import com.baslogh.baslogh.model.Case;
import org.springframework.stereotype.Repository;

@Repository
public class CaseDao extends AbstractJPADAO<Case>{
    public CaseDao() {
        setClazz(Case.class);
    }
}
