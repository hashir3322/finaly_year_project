package register;

public class RegisterToJson {
    private String pharmacyName;
    private String userName;
    private String userPassword;
    private String userStatus;
    private String userLicense;

    public RegisterToJson(String pharmacyName, String userName, String userPassword, String userStatus, String userLicense) {
        this.pharmacyName = pharmacyName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.userLicense = userLicense;
    }
}
