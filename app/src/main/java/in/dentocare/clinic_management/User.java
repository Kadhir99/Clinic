package in.dentocare.clinic_management;

public class User {
    String userEmail,Username,Mobileno,Gender,DOB;

    public User(){}

    public User(String email, String username, String mobileno, String gender, String DOB) {
        this.userEmail = email;
        this.Username = username;
        this.Mobileno = mobileno;
        this.Gender = gender;
        this.DOB = DOB;
    }

    public String getuserEmail() {
        return userEmail;
    }

    public String getUsername() {
        return Username;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public String getGender() {
        return Gender;
    }

    public String getDOB() {
        return DOB;
    }
}
