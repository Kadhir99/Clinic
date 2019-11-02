package in.dentocare.clinic_management;

public class UserData {
    public String userEmail, userName, mobileNo,gender,dob;

    public UserData(){}

    public UserData(String email, String userName, String mobileNo, String gen, String DOB) {
        this.userEmail = email;
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.gender = gen;
        this.dob = DOB;
    }

    public String getuserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getgender() {
        return gender;
    }

    public String getdob() {
        return dob;
    }
}
