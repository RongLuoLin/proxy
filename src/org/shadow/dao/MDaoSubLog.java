package org.shadow.dao;

/**
 * MDaoSubLog 代理类 会继承目标类
 * 代理对象和目标对象父子关系  代理对象是子， 目标对象是父
 *
 */
public class MDaoSubLog extends MemberDao{
    @Override
    public String query(int id, String name) {
        System.out.println("subLog,before extends=============proxy");
        return super.query(id, name);
    }

}
