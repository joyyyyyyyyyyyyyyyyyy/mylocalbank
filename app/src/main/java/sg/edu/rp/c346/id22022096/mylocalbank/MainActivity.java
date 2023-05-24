package sg.edu.rp.c346.id22022096.mylocalbank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView DBS;
    TextView OCBC;
    TextView UOB;
    TextView banks;
    TextView text;

    boolean DBSfav = false;
    boolean OCBCfav = false;
    boolean UOBfav = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBS = findViewById(R.id.textDBS);
        OCBC = findViewById(R.id.textOCBC);
        UOB = findViewById(R.id.textUOB);
        banks = findViewById(R.id.bank);
        text = findViewById(R.id.bankBelowText);

        registerForContextMenu(DBS);
        registerForContextMenu(OCBC);
        registerForContextMenu(UOB);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    String wordClicked = "";
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == DBS) {
            wordClicked = "DBS";
        } else if (v == OCBC) {
            wordClicked = "OCBC";
        } else if (v == UOB) {
            wordClicked = "UOB";
        }

        menu.add(0, 0, 0, "Visit Website");
        menu.add(0, 1, 1, "Call Hotline");
        menu.add(0, 2, 2, "Toggle Favourite");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.EnglishSelection) {

            DBS.setText("DBS");
            OCBC.setText("OCBC");
            UOB.setText("UOB");
            banks.setText("- BANKS -");
            text.setText("View all bank accounts here");
            Toast.makeText(MainActivity.this, "English is chosen", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.ChineseSelection) {

            DBS.setText("星展銀行");
            OCBC.setText("華僑銀行");
            UOB.setText("大華銀行");
            banks.setText("- 銀行 -");
            text.setText("在此處查看所有銀行帳戶");
            Toast.makeText(MainActivity.this, "Chinese is chosen", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (wordClicked.equalsIgnoreCase("DBS")) {
            if (item.getItemId() == 1) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18001111111"));
                startActivity(intentCall);
            } else if (item.getItemId() == 0) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dbs.com"));
                startActivity(intent);
            } else if (item.getItemId() == 2) { // Toggle Favourite option
                DBSfav = !DBSfav;
                updateFavoriteState(DBS, DBSfav);
            }
        }

        if (wordClicked.equalsIgnoreCase("OCBC")) {
            if (item.getItemId() == 1) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18003633333"));
                startActivity(intentCall);
            } else if (item.getItemId() == 0) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ocbc.com"));
                startActivity(intent);
            } else if (item.getItemId() == 2) { // Toggle Favourite option
                OCBCfav = !OCBCfav;
                updateFavoriteState(OCBC, OCBCfav);
            }
        }

        if (wordClicked.equalsIgnoreCase("UOB")) {
            if (item.getItemId() == 1) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18002222121"));
                startActivity(intentCall);
            } else if (item.getItemId() == 0) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.uob.com.sg"));
                startActivity(intent);
            } else if (item.getItemId() == 2) { // Toggle Favourite option
                UOBfav = !UOBfav;
                updateFavoriteState(UOB, UOBfav);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    //enhancements
    private void updateFavoriteState(TextView textView, boolean isFavourite) {
        if (isFavourite) {
            textView.setTextColor(Color.parseColor("#FFFF0000"));
            Toast.makeText(MainActivity.this, "Added to Favorites", Toast.LENGTH_LONG).show();
        } else {
            textView.setTextColor(Color.parseColor("#FFFFFFFF"));
            Toast.makeText(MainActivity.this, "Removed from Favorites", Toast.LENGTH_LONG).show();
        }
    }

}