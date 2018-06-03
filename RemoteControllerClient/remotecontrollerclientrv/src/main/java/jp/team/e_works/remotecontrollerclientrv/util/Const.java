package jp.team.e_works.remotecontrollerclientrv.util;

import android.os.Environment;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

public class Const {
    public static final String INI_DIR = Environment.getExternalStorageDirectory().getPath()
            + File.separator + "RemotecontrollerClient";

    public static String CreateIniFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(INI_DIR);
        sb.append(File.separator);
        Calendar calendar = Calendar.getInstance();
        sb.append(calendar.get(Calendar.YEAR));
        sb.append(String.format(Locale.US, "%02d", calendar.get(Calendar.MONTH) + 1));
        sb.append(String.format(Locale.US, "%02d", calendar.get(Calendar.DATE)));
        sb.append("_");
        sb.append(String.format(Locale.US, "%02d", calendar.get(Calendar.HOUR_OF_DAY)));
        sb.append(String.format(Locale.US, "%02d", calendar.get(Calendar.MINUTE)));
        sb.append(String.format(Locale.US, "%02d", calendar.get(Calendar.SECOND)));
        sb.append(String.format(Locale.US, "%03d", calendar.get(Calendar.MILLISECOND)));
        sb.append(".ini");

        return sb.toString();
    }

    public static final String INI_KEY_ITEM_NAME = "ItemName";

    public static final String INI_KEY_BUTTON_X_NAME = "Button%dName";
    public static final String INI_KEY_BUTTON_X_COMMAND = "Button%dCommand";
}
