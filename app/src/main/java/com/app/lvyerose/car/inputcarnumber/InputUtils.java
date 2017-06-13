package com.app.lvyerose.car.inputcarnumber;

import android.text.InputFilter;
import android.text.Spanned;

/************************************************************************
 *                    .::::.                                            *
 *                  .::::::::.                                          *
 *                 :::::::::::  FUCK YOU                                *
 *             ..:::::::::::'                                           *
 *           '::::::::::::'                                             *
 *             .::::::::::                                              *
 *        '::::::::::::::..                                             *
 *             ..::::::::::::.                                          *
 *           ``::::::::::::::::                                         *
 *            ::::``:::::::::'        .:::.                             *
 *           ::::'   ':::::'       .::::::::.                           *
 *         .::::'      ::::     .:::::::'::::.                          *
 *        .:::'       :::::  .:::::::::' ':::::.                        *
 *       .::'        :::::.:::::::::'      ':::::.                      *
 *      .::'         ::::::::::::::'         ``::::.                    *
 *  ...:::           ::::::::::::'              ``::.                   *
 * ```` ':.          ':::::::::'                  ::::..                *
 *                    '.:::::'                    ':'````..             *
 *              ━━━━━━━━━━━━━━━━━━━━━                                   *
 ************************************************************************
 *
 * 项目名称：InputCarNumber
 * 类描述： 
 * 创建人：lvyerose
 * 邮箱：lvyerose@163.com
 * 创建时间：17/6/13
 *
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 *  ......
 */
public class InputUtils {

    public static InputFilter getInputChineseFilter() {
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dStart, int dEnd) {
                for (int i = start; i < end; i++) {
                    if (!InputUtils.isChinese(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };

        return filter;
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
//                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
//                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
//                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                ) {
            return true;
        }
        return false;
    }

}
