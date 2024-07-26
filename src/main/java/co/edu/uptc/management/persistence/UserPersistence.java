package co.edu.uptc.management.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import co.edu.uptc.management.constants.CommonConstants;
import co.edu.uptc.management.dto.UserDTO;

public class UserPersistence extends FilePlain{
		
		private List<UserDTO> listUserDTO = new ArrayList<>();
			
			public void dumpFilePlain(String rutaArchivo) {
				List<String> records = new ArrayList<>();
				 for(UserDTO userDTO : listUserDTO){
					 StringBuilder contentUser = new StringBuilder();
					 contentUser.append(userDTO.getNameUser()).append(CommonConstants.SEMI_COLON);
					 contentUser.append(userDTO.getPassword());
					 records.add(contentUser.toString());
				 }
				 this.writer(rutaArchivo, records);
			}
			
			public void loadFilePlain(String rutaNombreArchivo) {
				List<String> contentInLine = this.reader(rutaNombreArchivo);
				for(String row: contentInLine) {
					StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
					while(tokens.hasMoreElements()){
						String nameUser = tokens.nextToken();
						String password = tokens.nextToken();
						listUserDTO.add(new UserDTO(nameUser, password));
					}
				}
			}

			public List<UserDTO> getListUserDTO() {
				return listUserDTO;
			}

			public void setListUserDTO(List<UserDTO> listUserDTO) {
				this.listUserDTO = listUserDTO;
			}
		}

