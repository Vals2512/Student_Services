package co.edu.uptc.management.dto;
public class SubjectDTO {

	private String codeSubject;
    private String name;
    private Integer numberCredits;
    private String codeStudent;

    public SubjectDTO() {
    }

    public SubjectDTO(String codeSubject, String name, Integer numberCredits, String codeStu) {

        this.codeSubject = codeSubject;
        this.name = name;
        this.numberCredits = numberCredits;
        this.codeStudent= codeStu;
    }
    
   
    
    

    public String getCodeSubject() {
		return codeSubject;
	}

	public String getCodeStudent() {
		return codeStudent;
	}

	public void setCodeSubject(String codeSubject) {
		this.codeSubject = codeSubject;
	}

	public void setCodeStudent(String codeStudent) {
		this.codeStudent = codeStudent;
	}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Integer getNumberCredits() {
		return numberCredits;
	}

	public void setNumberCredits(Integer numberCredits) {
		this.numberCredits = numberCredits;
	}

 

}