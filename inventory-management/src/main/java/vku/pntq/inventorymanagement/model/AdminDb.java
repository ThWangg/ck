package vku.pntq.inventorymanagement.model;


public class AdminDb {
    private String id;
    private String email;
    private String username;
    private String password;

    public AdminDb(String id, String email, String username, String password){
        this.id = id;
        this.email = email;
        this.username = username;
        this.password =password;
    }

    public AdminDb(String password, String email){
        this.password = password;
        this.email = email;
    }

    public String getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
