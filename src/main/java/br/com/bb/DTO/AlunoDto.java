package br.com.bb.DTO;

public class AlunoDto {
    
    private int id;
    private String name;


    AlunoDto()
    {

    }

    AlunoDto(int id, String name)
    {
        this.setId(id);
        this.setName(name);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

}
