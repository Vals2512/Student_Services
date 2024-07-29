package co.edu.uptc.management.rest;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.uptc.management.dto.StudentDTO;
import co.edu.uptc.management.dto.SubjectDTO;
import co.edu.uptc.management.persistence.SubjectPersistence;
 


@Path("/ManagementSubject")

public class ManagementSubject {

	public static SubjectPersistence subjectPersistence = new SubjectPersistence();
	
	
	static {
		
		subjectPersistence.loadFilePlain("/data/subjects.txt");
	}
	
	
	@GET
	@Path("/getSubjects")
	@Produces( { MediaType.APPLICATION_JSON } )
	public List<SubjectDTO> getSubjects(){
		return subjectPersistence.getListsubjectDTO();
	}
		
		
		
		
	@GET
	@Path("/getSubjectbyCode")
	@Produces({ MediaType.APPLICATION_JSON })
	public SubjectDTO getSubjectbyCode(@QueryParam("codeSubject") String codeSubject){
			for(SubjectDTO subjectDTO: subjectPersistence.getListsubjectDTO()) {
				if(subjectDTO.getCodeSubject().equals(codeSubject)) {
					return subjectDTO;
				}
			}
			return null;
		}


	   @POST
	    @Path("/createSubject")
	    @Produces({MediaType.APPLICATION_JSON})
	    @Consumes({MediaType.APPLICATION_JSON})
	   public SubjectDTO createSubject(SubjectDTO subjectDTO) {
	        // Buscar estudiante por código
	        StudentDTO student = null;
	        for (StudentDTO s : ManagementStudent.studentPersistence.getListStudentsDTO()) {
	            if (s.getCode().equals(subjectDTO.getCodeStudent())) {
	                student = s;
	                break;
	            }
	        }

	        // Verificar si existe el estudiante
	        if (student != null) {
	            // Agregar la materia al estudiante
	            if (subjectPersistence.getListsubjectDTO().add(subjectDTO)) {
	                // Guardar la lista de materias en un archivo
	                subjectPersistence.dumpFilePlain("subjects.txt");
	                return subjectDTO;
	            }
	        }

	        // Si no existe el estudiante, retornar null
	        return null;
	    }
	

@PUT
@Path("/updateSubject")
@Produces ({MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_JSON})
public SubjectDTO updateSubject(SubjectDTO subjectDTO) {
	for(SubjectDTO subject: subjectPersistence.getListsubjectDTO()) {
		if(subject.getCodeSubject().equals(subjectDTO.getCodeSubject())) {
			subject.setName(subjectDTO.getName());
			subject.setCodeSubject(subjectDTO.getCodeSubject());
			subject.setNumberCredits(subjectDTO.getNumberCredits());
			subjectPersistence.dumpFilePlain("subjects.txt");
			return subjectDTO;
		}
	}
	return null;
}

@PUT
@Path("/updateSubjectAttribute")
@Produces ({MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_JSON})
public SubjectDTO updateSubjectAttribute (SubjectDTO subjectDTO) {
	for(SubjectDTO subject: subjectPersistence.getListsubjectDTO()) {
		if(subject.getCodeSubject().equals(subjectDTO.getCodeSubject())) {
			
			if(!Objects.isNull(subjectDTO.getName())) {
				subject.setName(subjectDTO.getName());
			}
			if(!Objects.isNull(subjectDTO.getNumberCredits())) {
				subject.setNumberCredits(subjectDTO.getNumberCredits());
			}
			subjectPersistence.dumpFilePlain("subjects.txt");
			return subjectDTO;
		}
	}
	return null;
}


@DELETE
@Path("/deleteSubject")
@Produces ({MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_JSON})
	public SubjectDTO deleteSubject(@QueryParam("codeSubject") String codeSubject){
		SubjectDTO subjectDTO = this.getSubjectbyCode(codeSubject);
		if(subjectDTO != null) {
			subjectPersistence.getListsubjectDTO().remove(subjectDTO);
		}
		subjectPersistence.dumpFilePlain("subjects.txt");
		ManagementStudent.studentPersistence.dumpFilePlain("students.txt");
		return subjectDTO;
		}



}