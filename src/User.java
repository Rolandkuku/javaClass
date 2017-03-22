import java.io.Serializable;

/**
 * Created by roland on 21/03/2017.
 */
public class User implements Serializable{
    private String name;
    private String pwd;

    public User() {
        this.name = "";
        this.pwd = "";
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
