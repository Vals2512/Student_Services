package co.edu.uptc.management.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import co.edu.uptc.management.constants.CommonConstants;
import co.edu.uptc.management.dto.SubjectDTO;

public class SubjectPersistence extends FilePlain{

private List<SubjectDTO> listsubjectDTO = new ArrayList<>();
	
	public void dumpFilePlain(String rutaArchivo) {
		List<String> records = new ArrayList<>();
		 for(SubjectDTO subjectDTO : listsubjectDTO){
			 StringBuilder contentBorrow = new StringBuilder();
			 contentBorrow.append(subjectDTO.getCodeSubject()).append(CommonConstants.SEMI_COLON);
			 contentBorrow.append(subjectDTO.getName()).append(CommonConstants.SEMI_COLON);
			 contentBorrow.append(subjectDTO.getNumberCredits()).append(CommonConstants.SEMI_COLON);
			 contentBorrow.append(subjectDTO.getCodeStudent());
			 records.add(contentBorrow .toString());
		 }
		 this.writer(rutaArchivo, records);
	}
	
	public void loadFilePlain(String rutaNombreArchivo) {
		List<String> contentInLine = this.reader(rutaNombreArchivo);
		for(String row: contentInLine) {
			StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
			while(tokens.hasMoreElements()){
				String codeSubject = tokens.nextToken();
				String name = tokens.nextToken();
				String numberCredits = tokens.nextToken();
				String codeStudent = tokens.nextToken();
				listsubjectDTO.add(new SubjectDTO(codeSubject, name, Integer.parseInt(numberCredits), codeStudent));
			}
		}
	}

	public List<SubjectDTO> getListsubjectDTO() {
		return listsubjectDTO;
	}

	public void setListsubjectDTO(List<SubjectDTO> listsubjectDTO) {
		this.listsubjectDTO = listsubjectDTO;
	}
	
}