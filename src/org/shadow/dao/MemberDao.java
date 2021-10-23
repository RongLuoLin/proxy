package org.shadow.dao;

/**
 * 目标类
 */
public class MemberDao implements MDao{
    @Override
    public String query(int id, String name) {
        System.out.println("--------------query-------logic");
        return name + "-return";
    }

    @Override
    public void del() {

    }
}
