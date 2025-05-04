package com.baotien.k22411caproject2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
import android.content.res.Configuration;

public class MainActivity extends AppCompatActivity {
    EditText edtcoefficienta;
    EditText edtcoefficientb;
    TextView txtresult;

    // ← add this field for cycling through languages
    private int langState = 0;   // 0 = English, 1 = Vietnamese, 2 = Latin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        addViews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        edtcoefficienta = findViewById(R.id.edtcoefficienta);
        edtcoefficientb = findViewById(R.id.edtcoefficientb);
        txtresult = findViewById(R.id.txtr);
    }

    public void do_solution(View view) {
        String hsa = edtcoefficienta.getText().toString();
        double a = Double.parseDouble(hsa);
        double b = Double.parseDouble(edtcoefficientb.getText().toString());

        if (a == 0 && b == 0) {
            txtresult.setText(getResources().getText(R.string.Title_infinity));
        } else if (a == 0 && b != 0) {
            txtresult.setText(getResources().getText(R.string.Title_nosolution));
        } else {
            txtresult.setText("X=" + (-b / a));
        }
    }

    public void do_next(View view) {
        edtcoefficienta.setText("");
        edtcoefficientb.setText("");
        txtresult.setText("");
        edtcoefficienta.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }

    public void do_changelang(View view) {
        // cycle through your three language tags
        String[] tags = {"en", "vi", "la"};
        langState = (langState + 1) % tags.length;

        // apply new locale
        LocaleListCompat newLocale = LocaleListCompat.forLanguageTags(tags[langState]);
        AppCompatDelegate.setApplicationLocales(newLocale);
    }
}
