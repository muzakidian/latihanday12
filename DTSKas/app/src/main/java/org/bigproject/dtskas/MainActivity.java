package org.bigproject.dtskas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements TransactionAdapter.OnItemTransactionListener {
    TextView welcomeText;
    TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onTransactionClicked(int index, SurfaceControl.Transaction item) {
        welcomeText.setText(String.format("Welcome %s", account.getName()));
        totalText.setText(String.valueOf(account.getBalance()));

        adapter = new TransactionAdapter(account.getTransactions(), this);
        transactionsView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        transactionsView.setLayoutManager(layoutManager);

        Intent intent = new Intent(this, SaveActivity.class);
        intent.putExtra(TRANSACTION_KEY, item);
        intent.putExtra(INDEX_KEY, 0);
        startActivityForResult(intent, UPDATE_REQUEST);

        if (resultCode == RESULT_OK) {
            Transaction transaction = data.getParcelableExtra(TRANSACTION_KEY);
            if (requestCode == INSERT_REQUEST) {
                account.addTransaction(transaction);
            }
            adapter.notifyDataSetChanged();
            welcomeText.setText(String.valueOf(account.getBalance()));
        } else if (requestCode == UPDATE_REQUEST) {
            int index = data.getIntExtra(INDEX_KEY, 0);
            account.updateTransaction(index, transaction);
        }
    }
}