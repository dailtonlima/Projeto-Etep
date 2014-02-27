/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.studio.composer;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Dailton
 */
@ManagedBean(name = "menuPrincipal")
public class MenuPrincipal {
    
    public String listCompany(){
        return "/faces/cadastro/company/list.xhtml";
    }
}
