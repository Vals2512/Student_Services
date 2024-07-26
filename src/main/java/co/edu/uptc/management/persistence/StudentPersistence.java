package co.edu.uptc.management.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import co.edu.uptc.management.constants.CommonConstants;
import co.edu.uptc.management.dto.StudentDTO;

public class StudentPersistence extends FilePlain {
private List<StudentDTO> listStudentsDTO = new ArrayList<>();
	
	public void dumpFilePlain(String rutaArchivo) {
		List<String> records = new ArrayList<>();
		 for(StudentDTO studentDTO: listStudentsDTO ){
			 StringBuilder contentBook = new StringBuilder();
			 contentBook.append(studentDTO.getId()).append(CommonConstants.SEMI_COLON);
			 contentBook.append(studentDTO.getName()).append(CommonConstants.SEMI_COLON);
			 contentBook.append(studentDTO.getLastName()).append(CommonConstants.SEMI_COLON);
			 contentBook.append(studentDTO.getCode()).append(CommonConstants.SEMI_COLON);
			 contentBook.append(studentDTO.getCareer()).append(CommonConstants.SEMI_COLON);
			 contentBook.append(studentDTO.getEmail());
			 records.add(contentBook.toString());
		 }
		 this.writer(rutaArchivo, records);
	}
	
	public void loadFilePlain(String rutaNombreArchivo) {
		List<String> contentInLine = this.reader(rutaNombreArchivo);
		for(String row: contentInLine) {
			StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
			while(tokens.hasMoreElements()){
				String id = tokens.nextToken();
				String name = tokens.nextToken();
				String lastName = tokens.nextToken();
				String code = tokens.nextToken();
				String career = tokens.nextToken();
				String email= tokens.nextToken();
				listStudentsDTO.add(new StudentDTO(Integer.parseInt(id), name, lastName, 
						code,career, email));
			}
		}
	}

	public List<StudentDTO> getListStudentsDTO() {
		return listStudentsDTO;
	}

	public void setListStudentsDTO(List<StudentDTO> listStudentsDTO) {
		this.listStudentsDTO = listStudentsDTO;
	}


}