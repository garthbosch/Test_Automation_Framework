package testFramework.utils;

/*
 * @author Garth Bosch
 */
public class MemberDetails {

    private String schemeName;
    private String relationship;
    private String idNumber;
    private String dob;
    private String titleDescription;
    private String firstName;
    private String surname;
    private String gender;
    private String cover;

    public MemberDetails() {
    }

    public MemberDetails(String schemeName, String relationship, String idNumber, String dob, String titleDescription,
            String firstName, String surname, String gender, String cover) {
        this.schemeName = schemeName;
        this.relationship = relationship;
        this.idNumber = idNumber;
        this.dob = dob;
        this.titleDescription = titleDescription;
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
        this.cover = cover;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTitleDescription() {
        return titleDescription;
    }

    public void setTitleDescription(String titleDescription) {
        this.titleDescription = titleDescription;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
