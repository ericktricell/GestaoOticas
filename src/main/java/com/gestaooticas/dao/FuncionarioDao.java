package com.gestaooticas.dao;

import com.gestaooticas.model.Funcionario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author reeri
 */
public class FuncionarioDao extends DaoGeneric<Funcionario> implements Serializable{

    private static final long serialVersionUID = 1L;
    
    public List<Funcionario> find(String nome){
        try{
            return getSessao().createQuery("SELECT f FROM Funcionario f WHERE f.nome LIKE '%"+nome+"%' or f.cpf like '%"+nome+"%'").list();
        }catch(Exception e){
            return new ArrayList<>();
        }
    }

    public List<Funcionario> find(Class<Funcionario> entidade) {
        return getSessao().createCriteria(entidade).list();
    }
    
    public void update(Funcionario funcionario){
        getSessao().createQuery("UPDATE Funcionario f SET f.nome= "+funcionario.getNome()+", f.login= "+funcionario.getLogin()+", f.ativo= "+funcionario.isAtivo()+", f.cargo= "+funcionario.getCargo()+", f.cpf= "+funcionario.getCpf()+", f.tipo= "+funcionario.getTipo()+" WHERE f.codigo= "+funcionario.getCodigo());
    }
}
