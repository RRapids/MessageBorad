package note.vo;

public class Person {
    private int userId;
    private String userName;
    private String userCount;
    private String userPassword;
    private String flag;
    private String active;
    private String image;

    public String getActive() {
        return active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActive(String string) {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Person() {
        this.flag = flag;
    }

    public Person(int userId, String userName, String userCount, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userCount = userCount;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
