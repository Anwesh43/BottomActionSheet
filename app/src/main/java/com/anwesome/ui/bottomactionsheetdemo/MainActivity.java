package com.anwesome.ui.bottomactionsheetdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anwesome.ui.bottomactionsheet.ActionSheet;
import com.anwesome.ui.bottomactionsheet.ActionSheetElement;

public class MainActivity extends AppCompatActivity {
    private String actions[] = {"Delete","Add","Order","Track"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionSheet actionSheet = new ActionSheet(this);
        for(int i=0;i<actions.length;i++) {
            final String action = actions[i];
            actionSheet.addActionSheetElement(new ActionSheetElement(action, new ActionSheetElement.OnElementClickListener() {
                @Override
                public void onElementClick() {
                    Toast.makeText(MainActivity.this, action , Toast.LENGTH_SHORT).show();
                }
            }));
        }
        actionSheet.show();
    }
}
