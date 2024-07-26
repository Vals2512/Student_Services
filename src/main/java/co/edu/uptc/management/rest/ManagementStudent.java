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
import co.edu.uptc.management.persistence.StudentPersistence;




@Path("/ManagementStudent")
public class ManagementStudent {

	
		
		public static StudentPersistence studentPersistence= new StudentPersistence();

		 

		static {
			
			studentPersistence.loadFilePlain("/data/students.txt");
		
		}
		
		@GET
		@Path("/getStudents")
		@Produces( { MediaType.APPLICATION_JSON } )
		public List<StudentDTO> getStudents(){
			return studentPersistence.getListStudentsDTO();
		}
		
		@GET
		@Path("/getStudentbyCode")
		@Produces({ MediaType.APPLICATION_JSON })
		public StudentDTO getStudentbyCode(@QueryParam("code") String codeStudent){
			for(StudentDTO studentDTO: studentPersistence.getListStudentsDTO()) {
				if(studentDTO.getCode().equals(codeStudent)) {
					return studentDTO;
				}
			}
			return null;
		}
		
		@POST
		@Path("/createStudent")
		@Produces ({MediaType.APPLICATION_JSON})
		@Consumes ({MediaType.APPLICATION_JSON})
		public StudentDTO createStudent (StudentDTO studentDTO) {
			if(studentPersistence.getListStudentsDTO().add(studentDTO)) {
				studentPersistence.dumpFilePlain("students.txt");
				return studentDTO;
			}
			return null;
		}
		
		@PUT
		@Path("/updateStudent")
		@Produces ({MediaType.APPLICATION_JSON})
		@Consumes ({MediaType.APPLICATION_JSON})
		public StudentDTO updateStudent (StudentDTO studentDTO) {
			for(StudentDTO student: studentPersistence.getListStudentsDTO()) {
				if(student.getCode().equals(studentDTO.getCode())) {
					student.setId(studentDTO.getId());
					student.setName(studentDTO.getName());
					student.setLastName(studentDTO.getLastName());
					student.setCode(studentDTO.getCode());
					student.setCareer(studentDTO.getCareer());
					student.setEmail(studentDTO.getEmail());
					studentPersistence.dumpFilePlain("students.txt");
					return studentDTO;
				}
			}
			return null;
		}
		
		@PUT
		@Path("/updateStudentAttribute")
		@Produces ({MediaType.APPLICATION_JSON})
		@Consumes ({MediaType.APPLICATION_JSON})
		public StudentDTO updateStudentAttribute(StudentDTO studentDTO) {
			for(StudentDTO student: studentPersistence.getListStudentsDTO()) {
				if(student.getCode().equals(studentDTO.getCode())) {
					
					if(!Objects.isNull(studentDTO.getId())) {
						student.setId(studentDTO.getId());
					}
					if(!Objects.isNull(studentDTO.getName())) {
						student.setName(studentDTO.getName());
					}
					if(!Objects.isNull(studentDTO.getLastName())) {
						student.setLastName(studentDTO.getLastName());
					}
					if(!Objects.isNull(studentDTO.getCareer())) {
						student.setCareer(studentDTO.getCareer());
					}
					if(!Objects.isNull(studentDTO.getEmail())) {
						student.setEmail(studentDTO.getEmail());
					}
					studentPersistence.dumpFilePlain("students.txt");
					return studentDTO;
				}
			}
			return null;
		}
		
		
		@DELETE
		@Path("/deleteStudent")
		@Produces ({MediaType.APPLICATION_JSON})
		@Consumes ({MediaType.APPLICATION_JSON})
			public StudentDTO deleteStudent(@QueryParam("code") String codeStudent){
				StudentDTO studentDTO = this.getStudentbyCode(codeStudent);
				if(studentDTO != null) {
					studentPersistence.getListStudentsDTO().remove(studentDTO);
				}
				studentPersistence.dumpFilePlain("students.txt");
				return studentDTO;
				}
		
		
		
	}