package jp.team.e_works.remotecontrollerclientrv.object;

import android.support.annotation.NonNull;

public class ProfileListItem {
    private String mItemTitle;
    private String mFilePath;

    public ProfileListItem(@NonNull String title, @NonNull String filePath) {
        mItemTitle = title;
        mFilePath = filePath;
    }

    public void setItemTitle(@NonNull String title) {
        mItemTitle = title;
    }

    public void setFilePath(@NonNull String filePath) {
        mFilePath = filePath;
    }

    public String getItemTitle() {
        return mItemTitle;
    }

    public String getFilePath() {
        return mFilePath;
    }
}
