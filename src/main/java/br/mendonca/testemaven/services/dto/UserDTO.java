package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.dao.UserDAO;
import br.mendonca.testemaven.model.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
	
	private String name;
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public List<UserDTO> searchByName(String name) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		List<User> users = dao.searchByName(name);

		List<UserDTO> result = new ArrayList<>();
		for (User user : users) {
			result.add(UserDTO.userMapper(user));
		}
		return result;
	}



	public static UserDTO userMapper(User user) {
		UserDTO dto = new UserDTO();
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		
		return dto;
	}
}
