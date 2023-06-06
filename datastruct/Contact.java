package datastruct;

/**
 * Contact
 */
public class Contact {

    private PhoneNumber phone;
    private String name;
    private String surname;
    private DateTime dateOfBirth;
    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Contact(PhoneNumber phone, String name, String surname, DateTime dateOfBirth) {
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }
    public PhoneNumber getPhone() {
        return phone;
    }
    public void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contact other = (Contact) obj;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s %s | %s",surname,name,phone);
    }
    public String detailedToString() {
        String r = "";
        r+="*---------------------*\n";
        r+="|      CONTATTO       |\n";
        r+="*---------------------*\n";
        r+=String.format("| nome: %-14s|\n",name);
        r+=String.format("| cognome: %-11s|\n",surname);
        r+=String.format("| Tel: %-15s|\n",phone);
        r+=String.format("| Date: %-10s|\n",dateOfBirth);
        r+="*---------------------*\n";
        return r;
    }
    
}