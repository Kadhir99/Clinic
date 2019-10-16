package in.dentocare.clinic_management;

public class UserData {
    public String userEmail, userName, mobileNo,Gender,DOB;

    public UserData(){}

    public UserData(String email, String userName, String mobileNo, String gender, String DOB) {
        this.userEmail = email;
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.Gender = gender;
        this.DOB = DOB;
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

    public String getGender() {
        return Gender;
    }

    public String getDOB() {
        return DOB;
    }
}
