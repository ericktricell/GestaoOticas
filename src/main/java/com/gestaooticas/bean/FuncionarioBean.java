/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestaooticas.bean;

import com.gestaooticas.dao.FuncionarioDao;
import com.gestaooticas.model.Funcionario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author reeri
 */
@ManagedBean(name = "funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Funcionario funcionario = new Funcionario();

    private List<Funcionario> funList = new ArrayList<>();

    private String busca;

    private FuncionarioDao dao = new FuncionarioDao();

    public void salvar() {
        
            dao.savemerge(funcionario);
    }

    public void novo() {
        funcionario = new Funcionario();
        funcionario.setAtivo(true);
    }

    public void findAll() {
        funList = dao.findAll(Funcionario.class);
    }

    public void find() {
        funList = dao.find(busca);
    }

    public String onRowSelected(Long id) {
        for (int i = 0; i < funList.size(); i++) {
            if (Objects.equals(funList.get(i).getCodigo(), id)) {
                funcionario = funList.get(i);
                break;
            }
        }
        return "";
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFunList() {
        return funList;
    }

    public void setFunList(List<Funcionario> funList) {
        this.funList = funList;
    }

}
