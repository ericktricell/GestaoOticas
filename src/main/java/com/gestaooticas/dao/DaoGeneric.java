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
public class DaoGeneric<E> {
    
    private static Session sessao = null;

    public DaoGeneric() {
        if(sessao == null){
            HibernateUtil.getSessionfactory().openSession();
        }
    }

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
        }finally{
            sessao.close();
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
            
        }finally{
           sessao.close();
        }
    }
    
    public E getEntidade(Class<E> entidade){
        
        E retorno = null;
        
        try{
            retorno = (E) sessao.createCriteria(entidade).uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sessao.close();
        }
        
        return retorno;
    }
    
    public List<E> getListEntity(Class<E> entidade){
        
        List<E> retorno = new ArrayList<>();
        try{
            
            retorno = sessao.createCriteria(entidade).addOrder(Order.desc("idFechamento")).list();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sessao.close();
        }
        return retorno;
    }
    
    public List<E> getListEntity(Class<E> entidade, String prop, String nome){
        
        List<E> retorno = new ArrayList<>();
        try{
            
            retorno = sessao.createCriteria(entidade).add(Restrictions.ilike(prop, "%" + nome + "%")).list();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sessao.close();
        }
        return retorno;
    }
}
