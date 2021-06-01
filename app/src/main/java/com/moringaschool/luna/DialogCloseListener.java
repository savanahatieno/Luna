package com.moringaschool.luna;

import android.content.DialogInterface;
import android.view.Menu;

public interface DialogCloseListener {
    public void handleDialogClose(DialogInterface dialog);

    //Search Widget
    boolean onCreateOptionMenu (Menu menu_search);
}
