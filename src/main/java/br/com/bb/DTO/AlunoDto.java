package br.com.bb.DTO;

import javax.validation.metadata.ReturnValueDescriptor;

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

    @Override
    public String toString() {
        return "AlunoDto(" + 
                "id = " + getId() +
                " name = " + getName() + ")";
    }

    @Override
    public int hashCode() {
        return getId().hashCode() * getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        if (obj.getClass() != obj.getClass())
        {
            return false;
        }
        AlunoDto al = (AlunoDto) obj;
        return (al.getId() == getId() && al.getName() == getName());
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
