package model;

public class User {
    private int idUser;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String card_nr;
    private boolean loyal;
    private boolean admin;
    private boolean logged;



    public User() {

    }

    public User(String username, String password, String phone, String address,String card_nr) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.card_nr=card_nr;

    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public User(int idUser, String username, String password, String phone, String address, String card_nr,  boolean loyal, boolean admin, boolean logged) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.card_nr = card_nr;

        this.loyal = loyal;
        this.admin = admin;
        this.logged = logged;
    }

    public String getCard_nr() {
        return card_nr;
    }

    public void setCard_nr(String card_nr) {
        this.card_nr = card_nr;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", loyal=" + loyal +
                ", admin=" + admin +
                '}'+"\n";
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public boolean isLoyal() {
        return loyal;
    }

    public void setLoyal(boolean loyal) {
        this.loyal = loyal;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}


