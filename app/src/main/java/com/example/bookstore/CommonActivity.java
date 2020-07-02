package com.example.bookstore;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.example.bookstore.MVVM.Navigator;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CommonActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected ProgressDialog progressDialog = null;
    protected Snackbar snackbar;

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(ApplicationEx.getAppTypeface());
            }
        }
    }

    protected void showBack() {
        if (toolbar == null) {
            setupToolbar();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected void showTitle(final String title) {
        getSupportActionBar().setTitle(title);
    }

    protected void showSubtitle(final String subtitle) {
        getSupportActionBar().setSubtitle(subtitle);
    }

    public void hideKeypad() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void hideKeypad(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                onHomeClicked();
                makeFinish();
                break;
            default:
                break;
        }
        return true;
    }

    public void makeFinish() {
        hideKeypad();
        setResult(RESULT_CANCELED);
        finish();
    }

    protected void onHomeClicked() {

    }

    public void allowFinish() {
        hideKeypad();
        setResult(RESULT_OK);
        finish();
    }

    public void allowFinish(Intent intent) {
        setResult(RESULT_OK, intent);
        finish();
    }




    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void infoMsg(final String text, int lines) {
        hideKeypad();
        final Snackbar snackbar = Snackbar.make(
                getWindow().getDecorView().findViewById(android.R.id.content),
                text, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.white));
        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(R.id.snackbar_text);
        textView.setMaxLines(lines);
        snackbar.show();
    }

    public void showMsg(String text) {
        Snackbar.make(
                getWindow().getDecorView().findViewById(android.R.id.content),
                text, Snackbar.LENGTH_LONG).show();
    }

    public void showMsgPrimaryColor(String text) {
        snackbar = Snackbar.make(
                getWindow().getDecorView().findViewById(android.R.id.content),
                text, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.uao_colorPrimary));
        snackbar.show();
    }

    public void shortMsg(String text) {
        Snackbar.make(
                getWindow().getDecorView().findViewById(android.R.id.content),
                text, Snackbar.LENGTH_SHORT).show();
    }

    public void keepMsg(String text) {
        snackbar = Snackbar.make(
                getWindow().getDecorView().findViewById(android.R.id.content),
                text, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    public void hideKeepMsg() {
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public List<Integer> getNumbers(int min, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    public List<Integer> getLastYear(int count) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        List<Integer> list = new ArrayList<>();
        for (int i = count; i >= 0; i--) {
            list.add(currentYear - i);
        }
        return list;
    }

    public List<String> getNumbersStr(int min, int max) {
        List<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.lang_select_string));
        for (int i = min; i <= max; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    public List<String> getLastYearStr(int count) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        List<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.lang_select_string));
        int i = count;
        while(i <= currentYear){
            list.add(String.valueOf(i));
            i++;
        }
        return list;
    }




    protected void startActivity(Navigator navigationUtils) {
        hideKeypad();
        final Intent intent;
        Navigator.NavigationAction navigationAction = navigationUtils.getNavigationAction();
        switch (navigationAction) {
            case startActivity:
                intent = new Intent(this, navigationUtils.getDestination());
                if (navigationUtils.getBundle() != null) {
                    intent.putExtras(navigationUtils.getBundle());
                }
                startActivity(intent);
                break;
            case startActivityFinishCurrent:
                intent = new Intent(this, navigationUtils.getDestination());
                if (navigationUtils.getBundle() != null) {
                    intent.putExtras(navigationUtils.getBundle());
                }
                startActivity(intent);
                allowFinish();
                break;
            case startActivityForResult:
                intent = new Intent(this, navigationUtils.getDestination());
                if (navigationUtils.getBundle() != null) {
                    intent.putExtras(navigationUtils.getBundle());
                }
                startActivityForResult(intent, navigationUtils.getRequestCode());
                break;
            case sendResult:
                intent = new Intent();
                for (String key : navigationUtils.getDataToSend().keySet()) {
                    intent.putExtra(key, navigationUtils.getDataToSend().get(key));
                }
                allowFinish(intent);
                break;
            case finishCurrent:
                allowFinish();
                break;
            case implicitIntent:
                startActivityForResult(navigationUtils.getImplicitIntent(), navigationUtils.getRequestCode());
                break;
            case startActivityWithCloseAll:
                intent = new Intent(this, navigationUtils.getDestination());
                if (navigationUtils.getBundle() != null) {
                    intent.putExtras(navigationUtils.getBundle());
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int dpToPx(int dp) {
        Resources r = Resources.getSystem();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
