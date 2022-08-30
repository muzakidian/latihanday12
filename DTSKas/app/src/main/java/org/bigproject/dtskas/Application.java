package org.bigproject.dtskas;

import android.accounts.Account;

import androidx.recyclerview.widget.ItemTouchHelper;

public class Application {

    public String account;

    public Application(String account) {
        this.account = account;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        account = new Account("Budi");
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback() {
        };
    }

}
