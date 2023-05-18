package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.Dao.TypeProductDao;

public class TypeProduct {
    private String id;
    private String name;
    private String typeFather;


    public TypeProduct(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeProduct(String id, String name, String typeFather) {
        this.id = id;
        this.name = name;
        this.typeFather = typeFather;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
