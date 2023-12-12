package game.service;

import game.model.account.Account;
import game.service.impl.IAccountService;
import game.utils.Config;
import game.utils.FileUtils;

import java.util.List;

public class AccountService implements IAccountService {
    @Override
    public List<Account> getAll() {
        return FileUtils.readFile(Config.PATH_ACCOUNT, Account.class);
    }
}
