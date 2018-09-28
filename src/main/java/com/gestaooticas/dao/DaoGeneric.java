/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestaooticas.dao;

import com.gestaooticas.jpa.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author reeri
 * @param <E>
 */
public class DaoGeneric<E>{
    
    private static Session sessao = HibernateUtil.getSessionfactory().openSession();;

    public static Session getSessao() {
        return sessao;
    }
    
    public void save(E entidade){
        Transaction t = null;
        try {
            t = sessao.beginTransaction();
            sessao.save(entidade);
            t.commit();
        } catch (Exception e){
            e.printStackTrace();
            t.rollback();
        }
    }
    
    public void savemerge(E entidade){
        Transaction t = null;
        try {
            t = sessao.beginTransaction();
            sessao.saveOrUpdate(entidade);
            t.commit();
        } catch (Exception e){
            e.printStackTrace();
            t.rollback();
            
        }
    }
    
    public List<E> findAll(Class<E> entidade){
        try{
            return sessao.createCriteria(entidade).list();
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
    
    public List<E> find(Class<E> entidade, String prop, String nome){
       List<E> retorno = new ArrayList<>();
        try{
            
            retorno = sessao.createCriteria(entidade).add(Restrictions.ilike(prop, "%" + nome + "%")).list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return retorno;
    }
}
