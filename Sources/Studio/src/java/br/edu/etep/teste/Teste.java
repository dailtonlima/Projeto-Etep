/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.teste;

import br.edu.etep.studio.dto.UserDTO;
import br.edu.etep.studio.facade.StudioFacade;

/**
 *
 * @author dailtonlima
 */
public class Teste {
    
    public static void main(String[] args) {
        
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("teste123");
        userDTO.getPerson().setFullName("Dailton Dourado de Lima");
        userDTO.getPerson().setEmail("dailtonlima@gmail.com");
        userDTO.getPerson().getCompany().setCompanyName("Dailton Lima");
        
        StudioFacade facade = new StudioFacade();
        facade.createOWNER(userDTO);
    }    
}
