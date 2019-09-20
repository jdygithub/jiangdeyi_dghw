package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CollectiontypeDao;
import com.zking.zkingedu.common.model.Collectiontype;
import com.zking.zkingedu.common.service.CollectiontypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("collectiontypeService")
public class CollectiontypeServiceImpl implements CollectiontypeService {
    @Resource
    private CollectiontypeDao collectiontypeDao;

    @Override
    public List<Collectiontype> getalltypes() {
        List<Collectiontype> results = new ArrayList<>();
        List<Collectiontype> types = collectiontypeDao.getalltypes();
        if(types!=null&&types.size()!=0){
            for (int i = 0; i < types.size(); ++i) {
                //如果类别父ID==0
                if(((Collectiontype)types.get(i)).getCollectionparenttypeid()==0){
                    Collectiontype type1 = new Collectiontype();
                    type1.setCollectiontypeid(((Collectiontype)types.get(i)).getCollectiontypeid());
                    type1.setCollectiontypename(((Collectiontype)types.get(i)).getCollectiontypename());
                    type1.setCollectionparenttypeid(((Collectiontype)types.get(i)).getCollectionparenttypeid());
                    List<Collectiontype> typeslist = new ArrayList<>();
                    for (int j = 0; j < types.size(); ++j) {
                        //所有类别的父ID等于顶级类别的ID
                        if(((Collectiontype)types.get(i)).getCollectiontypeid()==((Collectiontype)types.get(j)).getCollectionparenttypeid()){
                            Collectiontype type2 = new Collectiontype();
                            type2.setCollectiontypeid(((Collectiontype)types.get(j)).getCollectiontypeid());
                            type2.setCollectiontypename(((Collectiontype)types.get(j)).getCollectiontypename());
                            type2.setCollectionparenttypeid(((Collectiontype)types.get(j)).getCollectionparenttypeid());
                            typeslist.add(type2);
                        }
                    }
                    type1.setTypes(typeslist);
                    results.add(type1);
                }
            }
        }
        return results;
    }

    @Override
    public List<Collectiontype> gettypes() {
        return collectiontypeDao.getalltypes();
    }

    @Override
    public List<Collectiontype> gettypeo() {
        return collectiontypeDao.gettypeo();
    }

    @Override
    public int addtype(Collectiontype collectiontype) {
        return collectiontypeDao.addtype(collectiontype);
    }

    @Override
    public int deltype(Integer collectiontypeid) {
        return collectiontypeDao.deltype(collectiontypeid);
    }

    @Override
    public int updatetype(Collectiontype collectiontype) {
        return collectiontypeDao.updatetype(collectiontype);
    }
    @Override
    public int exitid(Integer collectiontypeid){
        return collectiontypeDao.exitid(collectiontypeid);
    }

}
