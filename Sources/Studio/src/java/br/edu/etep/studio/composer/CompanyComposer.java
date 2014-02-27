/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.studio.composer;

import br.edu.etep.studio.dao.CompanyDAO;
import br.edu.etep.studio.dto.CompanyDTO;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Dailton
 */
@ManagedBean(name = "company")
public class CompanyComposer {
    
    private CompanyDTO selectedCompany;
    
    public List list() throws SQLException, UnsupportedEncodingException{
        CompanyDAO companyDao = new CompanyDAO();
        return companyDao.listAll();
    }
}
