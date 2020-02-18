package com.example.fady.uspets.USPetsMain;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.fady.uspets.R;

import javax.annotation.Nullable;

public abstract class USPetMainAlertDialog extends Dialog {
    Context context;

    public USPetMainAlertDialog(Context context) {
        super(context);
        this.context = context;
    }


    public USPetMainAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;

    }

    protected USPetMainAlertDialog(Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dialog_view);
        if (getDialogContent() != 0) {
            ViewGroup dialogContentView = findViewById(R.id.dialog_content_view);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(getDialogContent(), dialogContentView);
            onCreateDialog();
        }
    }

    protected abstract void onCreateDialog();


    protected abstract int getDialogContent();
}
