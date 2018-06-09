package jp.team.e_works.remotecontrollerclientrv.object;

import jp.team.e_works.remotecontrollerclientrv.util.RedisConst;

public enum SelectButtonSpinnerItem {
    NONE(RedisConst.REDIS_EVENT_NONE),

    A(RedisConst.REDIS_KEYEVENT_A),
    B(RedisConst.REDIS_KEYEVENT_B),
    C(RedisConst.REDIS_KEYEVENT_C),
    D(RedisConst.REDIS_KEYEVENT_D),
    E(RedisConst.REDIS_KEYEVENT_E),
    F(RedisConst.REDIS_KEYEVENT_F),
    G(RedisConst.REDIS_KEYEVENT_G),
    H(RedisConst.REDIS_KEYEVENT_H),
    I(RedisConst.REDIS_KEYEVENT_I),
    J(RedisConst.REDIS_KEYEVENT_J),
    K(RedisConst.REDIS_KEYEVENT_K),
    L(RedisConst.REDIS_KEYEVENT_L),
    M(RedisConst.REDIS_KEYEVENT_M),
    N(RedisConst.REDIS_KEYEVENT_N),
    O(RedisConst.REDIS_KEYEVENT_O),
    P(RedisConst.REDIS_KEYEVENT_P),
    Q(RedisConst.REDIS_KEYEVENT_Q),
    R(RedisConst.REDIS_KEYEVENT_R),
    S(RedisConst.REDIS_KEYEVENT_S),
    T(RedisConst.REDIS_KEYEVENT_T),
    U(RedisConst.REDIS_KEYEVENT_U),
    V(RedisConst.REDIS_KEYEVENT_V),
    W(RedisConst.REDIS_KEYEVENT_W),
    X(RedisConst.REDIS_KEYEVENT_X),
    Y(RedisConst.REDIS_KEYEVENT_Y),
    Z(RedisConst.REDIS_KEYEVENT_Z),

    ZERO(RedisConst.REDIS_KEYEVENT_0),
    ONE(RedisConst.REDIS_KEYEVENT_1),
    TWO(RedisConst.REDIS_KEYEVENT_2),
    THREE(RedisConst.REDIS_KEYEVENT_3),
    FOUR(RedisConst.REDIS_KEYEVENT_4),
    FIVE(RedisConst.REDIS_KEYEVENT_5),
    SIX(RedisConst.REDIS_KEYEVENT_6),
    SEVEN(RedisConst.REDIS_KEYEVENT_7),
    EIGHT(RedisConst.REDIS_KEYEVENT_8),
    NINE(RedisConst.REDIS_KEYEVENT_9),

    F1(RedisConst.REDIS_KEYEVENT_F1),
    F2(RedisConst.REDIS_KEYEVENT_F2),
    F3(RedisConst.REDIS_KEYEVENT_F3),
    F4(RedisConst.REDIS_KEYEVENT_F4),
    F5(RedisConst.REDIS_KEYEVENT_F5),
    F6(RedisConst.REDIS_KEYEVENT_F6),
    F7(RedisConst.REDIS_KEYEVENT_F7),
    F8(RedisConst.REDIS_KEYEVENT_F8),
    F9(RedisConst.REDIS_KEYEVENT_F9),
    F10(RedisConst.REDIS_KEYEVENT_F10),
    F11(RedisConst.REDIS_KEYEVENT_F11),
    F12(RedisConst.REDIS_KEYEVENT_F12),
    F13(RedisConst.REDIS_KEYEVENT_F13),
    F14(RedisConst.REDIS_KEYEVENT_F14),
    F15(RedisConst.REDIS_KEYEVENT_F15),
    F16(RedisConst.REDIS_KEYEVENT_F16),

    BACK_SPACE(RedisConst.REDIS_KEYEVENT_BACK_SPACE),
    TAB(RedisConst.REDIS_KEYEVENT_TAB),
    PAUSE(RedisConst.REDIS_KEYEVENT_PAUSE),
    ESC(RedisConst.REDIS_KEYEVENT_ESC),
    SPACE(RedisConst.REDIS_KEYEVENT_SPACE),
    PAGE_UP(RedisConst.REDIS_KEYEVENT_PAGE_UP),
    PAGE_DOWN(RedisConst.REDIS_KEYEVENT_PAGE_DOWN),
    HOME(RedisConst.REDIS_KEYEVENT_HOME),
    END(RedisConst.REDIS_KEYEVENT_END),
    INSERT(RedisConst.REDIS_KEYEVENT_INSERT),
    DELETE(RedisConst.REDIS_KEYEVENT_DELETE),
    WIN(RedisConst.REDIS_KEYEVENT_WIN),
    NUMLOCK(RedisConst.REDIS_KEYEVENT_NUMLOCK);

    private int mRedisCommand = RedisConst.REDIS_EVENT_NONE;

    SelectButtonSpinnerItem(int redisCommand) {
        mRedisCommand = redisCommand;
    }

    public int getRedisCommand() {
        return mRedisCommand;
    }

    /**
     * Redis のコマンドから選択対象キーを取得する
     *
     * @param redisCommand キーを取得したい Redis コマンド
     * @return 一致するキーがあればキーを、<br>
     * なければ {@link null} を返す
     */
    public static SelectButtonSpinnerItem getItem(int redisCommand) {
        for (SelectButtonSpinnerItem item : SelectButtonSpinnerItem.values()) {
            if (item.mRedisCommand == redisCommand) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        // 一部の定義は表示名を変える
        switch (this) {
            case NONE:
                return "";

            case ZERO:
                return "0";

            case ONE:
                return "1";

            case TWO:
                return "2";

            case THREE:
                return "3";

            case FOUR:
                return "4";

            case FIVE:
                return "5";

            case SIX:
                return "6";

            case SEVEN:
                return "7";

            case EIGHT:
                return "8";

            case NINE:
                return "9";

            case BACK_SPACE:
                return "Back Space";

            case TAB:
                return "Tab";

            case PAUSE:
                return "Pause";

            case ESC:
                return "Esc";

            case SPACE:
                return "Space";

            case PAGE_UP:
                return "Page Up";

            case PAGE_DOWN:
                return "Page Down";

            case HOME:
                return "Home";

            case END:
                return "End";

            case INSERT:
                return "Insert";

            case DELETE:
                return "Delete";

            case WIN:
                return "Win";

            case NUMLOCK:
                return "NumLock";

            default:
                return name();
        }
    }
}