package game.model.player;


import game.model.account.Account;
import game.model.impl.IParser;

import java.time.LocalDate;

public class Player implements IParser {
    private long id;
    private long idAccount; //Id tài khoản
    private String name; //Tên người chơi

    public Player() {}
    public Player(long id, long idAccount, String name) {
        this.id = id;
        this.idAccount = idAccount;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s", this.id, this.idAccount, this.name);
    }

    @Override
    public void parse(String line) {
        String[] items = line.split("\\|");

        this.id = Long.parseLong(items[0]);
        this.idAccount = Long.parseLong(items[1]);
        this.name = items[2];
    }
}