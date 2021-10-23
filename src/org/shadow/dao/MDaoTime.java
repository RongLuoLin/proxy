package org.shadow.dao;


/**
 *   1.代理类(ProxyObject)  和目标类  实现同一个接口
 *   2.代理对象当中必须包含目标对象
 */
public class MDaoTime implements MDao{

    // 目标对象 TargetObject
    MDao mdao;

    public MDaoTime(MDao mDao){
        this.mdao = mDao;
    }

    @Override
    public String query(int id, String name) {

        String result = mdao.query(id, name);
        System.out.println("Time,after implements========proxy time");
        return result;
    }

    @Override
    public void del() {

    }
}
