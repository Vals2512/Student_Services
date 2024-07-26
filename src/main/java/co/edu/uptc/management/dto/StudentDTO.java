package co.edu.uptc.management.dto;

public class StudentDTO {
	
	private Integer id;
    private String name;
    private String lastName;
    private String code;
    private String career;
    private String email;
    

    public StudentDTO() {
    }

    public StudentDTO(Integer id, String name, String lastName, String code, String career, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.code = code;
        this.career = career;
        this.email = email;
        
    }
    
   
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}