package game.model.account;

import game.model.impl.IParser;
import game.utils.DateUtils;

import java.time.LocalDate;

public class Account implements IParser {
    private long id;
    private String username; //Tên đăng nhập
    private String password; //Mật khẩu
    private ERole eRole; //Vai trò tài khoản
    private LocalDate creatAt; //Ngày tạo dd-MM-yyyy

    public Account() {}
    public Account(long id, String username, String password, ERole eRole, LocalDate creatAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.eRole = eRole;
        this.creatAt = creatAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public ERole geteRole() {
        return eRole;
    }

    public void seteRole(ERole eRole) {
        this.eRole = eRole;
    }

    public LocalDate getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(LocalDate creatAt) {
        this.creatAt = creatAt;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s", this.id, this.username, this.password, this.eRole.getRole(), this.creatAt);
    }

    @Override
    public void parse(String line) {
        String[] items = line.split("\\|");
        //1|hiepadmin|123|Quản lý|2023-12-11
        this.id = Long.parseLong(items[0]);
        this.username = items[1];
        this.password = items[2];
        this.eRole = ERole.getBy(items[3]);
        this.creatAt = DateUtils.parse(items[4]);

    }
}
