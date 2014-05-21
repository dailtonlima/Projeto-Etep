/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.studio.dao;

import br.edu.etep.common.DBConnManager;
import br.edu.etep.studio.dto.CompanyDTO;
import br.edu.etep.studio.dto.PersonDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dailtonlima
 */
public class PersonDAO {
    
    
        /**
	 * 
	 * @param id
	 * @return
	 */
	public PersonDTO read(String id){
		PersonDTO person = null;
		try {
			String sql = "SELECT Top 1 code,fullName,birthDate,email,registryDate,companyCode FROM Company Where code = ?";
			//Prepara o Comando de SQL
			PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
			
                        //Seta o parametro
			ps.setString(1, id);
			//Executa o Comando de SQL e converte para Objeto
			List<PersonDTO> list = convertForList(ps.executeQuery());
			if(list.size() > 0){
				person = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}
        /**
         * 
         * @param person
         * @return 
         */
        public PersonDTO get(PersonDTO person){
            PersonDTO personDTO = this.get(person.getEmail());
            return personDTO == null ? person : personDTO;            
        }
        
	/**
	 * 
	 * @param email
	 * @return
	 */
	public PersonDTO get(String email){
		PersonDTO person = null;
		try {
			String sql = "SELECT code,fullName,birthDate,email,registryDate,companyCode FROM person Where email = ?";
			//Prepara o Comando de SQL
			PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
			//Seta o parametro
			ps.setString(1, email);
			//Executa o Comando de SQL e converte para Objeto
			List<PersonDTO> list = convertForList(ps.executeQuery());
			if(list.size() > 0){
				person = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}
	/**
	 * 
	 * @return
	 */
	public List<PersonDTO> listAll(){
		List<PersonDTO> list = new ArrayList<PersonDTO>();
		try {
			String sql = "SELECT code,fullName,birthDate,email,registryDate,companyCode FROM Person";
			PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
			list = convertForList(ps.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 */
	public PersonDTO create(PersonDTO person){
		PersonDTO result = get(person.getEmail());
		
		try {
			if(result == null){
								
				String sql = "Insert Into Person (code,fullName,email,companyCode) Values('',?,?,?)";
				PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
				//Parametros
                                ps.setString(1, person.getFullName());
				ps.setString(2, person.getEmail());
                                ps.setString(3, person.getCompany().getCode());
//				// Se existir endereço
				
				//Execute
				int create =  ps.executeUpdate();
				if(create == 1)
					result = this.get(person.getEmail());
			}
		} catch (Exception e) {
                    e.printStackTrace();
		}
	return result;
	}
	/**
	 * 
	 * @param person
	 * @return
	 */
	public PersonDTO update(PersonDTO person){
		PersonDTO result = null;
		try {
			if(person.getCode() != null){
				
				// SQL de atualização da empresa
				String sql = 	"	Update person set " +
						"	fullName  = ?," +
                                                "	email  = ?," +
                                                "	companyCode  = ?" +
						"	Where code = ?";
				PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
				//Parametros
				ps.setString(1, person.getFullName());
                                ps.setString(2, person.getEmail());
                                ps.setString(3, person.getCompany().getCode());
				ps.setString(4, person.getCode());
				//Execute
				int update =  ps.executeUpdate();
				if(update == 1)
					result = this.read(person.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return result;
	}
	/**
	 * 
	 * @param person
	 * @return
	 */
	public boolean delete(PersonDTO person){
		Boolean result = true;
		try {
			String sql = 	" Delete From person " +
					" Where code = ?";
			PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
			//Parametros
			ps.setString(1, person.getCode());
			//Execute
			int delete =  ps.executeUpdate();
			if(delete != 1)
				result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	return result;
	}
	/**
	 * 
	 * @param rs
	 * @return
	 */
	private List<PersonDTO> convertForList(ResultSet rs){
		List<PersonDTO> list = new ArrayList<PersonDTO>();
		try {
			while(rs.next()){
				list.add(convertObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 */
	private PersonDTO convertObject(ResultSet rs){
		PersonDTO person = new PersonDTO();
		try {
			person.setCode(rs.getString("code"));
			person.setFullName(rs.getString("fullName"));
                        person.setEmail(rs.getString("email"));
                        person.setCompany(new CompanyDAO().get(new CompanyDTO(rs.getString("companyCode"))));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
}
