package co.edu.uptc.management.rest;


import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import co.edu.uptc.management.dto.UserDTO;
import co.edu.uptc.management.persistence.UserPersistence;
import co.edu.uptc.management.utils.ManagementListUtils;



@Path("/ManagementUser")
public class ManagementUser {

	

	public static UserPersistence userP = new  UserPersistence();
	
	/* Atributo que determina la clase utilitaria para operaciones a listas */
	public static ManagementListUtils<UserDTO> managementListUtils;
	
	static {
		/* Hacemos el cargue de la información */
		userP.loadFilePlain("/data/users.txt");
		
		/* Enviamos la información cargada de los archivos a la clase utilitaria */
		managementListUtils = new ManagementListUtils<UserDTO>(
				userP.getListUserDTO());
		try {
			/* Asignamos el nombre del atributo por los atributos que deseamos ordenar */
			managementListUtils.sortList("nameUser", "password");
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			System.out.println("No se encontró el nombre del atributo en la clase");
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/validateUser")
	@Consumes({MediaType.TEXT_PLAIN})
	public Boolean validateUser(@QueryParam("nameUser") String nameUser,
			@QueryParam("password") String password) {
		UserDTO userDTO = new UserDTO(nameUser, password);
		UserDTO usuarioEncontrado = null;
		try {
			usuarioEncontrado = managementListUtils.findObjectBinary(userDTO, "nameUser", "password");
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return !Objects.isNull(usuarioEncontrado);
	}
	
	@POST
	@Path("/createUser")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public UserDTO createUser(UserDTO userDTO) {
	    
	    List<UserDTO> users= userP.getListUserDTO();
	    
	    for (UserDTO user : users) {
	        if (user.getNameUser().equals(userDTO.getNameUser())) {
	            return null;
	        }
	    }
	    if (users.add(userDTO)) {
	        
	        userP.dumpFilePlain("users.txt");
	        return userDTO;
	    }
	    
	    return null;
	}
	
	@GET
	@Path("/getUserbyUserName")
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDTO getUserbyUserName(@QueryParam("nameUser") String nameUser){
			for(UserDTO userDTO: userP.getListUserDTO()) {
				if(userDTO.getNameUser().equals(nameUser)) {
					return userDTO;
				}
			}
			return null;
		}
	
	@DELETE
	@Path("/deleteUser")
	@Produces ({MediaType.APPLICATION_JSON})
	@Consumes ({MediaType.APPLICATION_JSON})
		public UserDTO deleteUser(@QueryParam("nameUser") String nameUser){
			UserDTO userDTO = this.getUserbyUserName(nameUser);
			if(userDTO != null) {
				userP.getListUserDTO().remove(userDTO);
			}
			userP.dumpFilePlain("users.txt");
			
			return userDTO;
			}
}
