package updateUser;

public class UpdateToJson {
    private String id;
    private String pharmacyName;
    private String userName;
    private String userPassword;
    private String userStatus;
    private String userLicense;

    public UpdateToJson(String id, String pharmacyName, String userName, String userPassword, String userStatus, String userLicense) {
        this.id = id;
        this.pharmacyName = pharmacyName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.userLicense = userLicense;
    }
}
