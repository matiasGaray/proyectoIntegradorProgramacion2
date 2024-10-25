
package main;


public abstract class Person {
    private String name;
    private char gender;
    private String email;
    private int dni;
    Person(String aName, char aGender, String aEmail, int aDni){
        name = aName;
        gender = aGender;
        email = aEmail;
        dni = aDni;
    }
    
    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setGender(char gender){
        this.gender = gender;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDni(int dni){
        this.dni = dni;
    }
    
    //getters
    public String getName(){
        return name;
    }
    public char getGender(){
        return gender;
    }
    public String getEmail(){
        return email;
    }
    public int getDni(){
        return dni;
    }
    
}
