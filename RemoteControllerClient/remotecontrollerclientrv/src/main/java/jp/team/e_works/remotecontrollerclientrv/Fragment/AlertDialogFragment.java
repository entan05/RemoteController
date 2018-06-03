package jp.team.e_works.remotecontrollerclientrv.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.CheckResult;

public class AlertDialogFragment extends DialogFragment {
    public interface OnAlertDialogButtonClickListener {
        void onPositiveClick(DialogInterface dialogInterface, int i);

        void onNegativeClick(DialogInterface dialogInterface, int i);
    }

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_MESSAGE = "key_message";
    private static final String KEY_POSITIVE_TEXT = "key_positive_text";
    private static final String KEY_NEGATIVE_TEXT = "key_negative_text";

    private OnAlertDialogButtonClickListener mListener;

    @CheckResult
    public static AlertDialogFragment createInstance(String title, String message,
                                                     String positiveButtonText, String negativeButtonText) {
        AlertDialogFragment fragment = new AlertDialogFragment();

        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putString(KEY_MESSAGE, message);
        args.putString(KEY_POSITIVE_TEXT, positiveButtonText);
        args.putString(KEY_NEGATIVE_TEXT, negativeButtonText);
        fragment.setArguments(args);

        return fragment;
    }

    public void setClickListener(OnAlertDialogButtonClickListener listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(KEY_TITLE, "");
        String message = getArguments().getString(KEY_MESSAGE, "");
        String positiveText = getArguments().getString(KEY_POSITIVE_TEXT, "");
        String negativeText = getArguments().getString(KEY_NEGATIVE_TEXT, "");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mListener != null) {
                            mListener.onPositiveClick(dialogInterface, i);
                        }
                    }
                })
                .setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mListener != null) {
                            mListener.onNegativeClick(dialogInterface, i);
                        }
                    }
                })
                .show();
    }

    @Override
    public void onPause() {
        super.onPause();

        dismiss();
    }
}
