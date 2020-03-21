package com.forever17.project.charityquest.utils;

import com.forever17.project.charityquest.constants.CharityConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * escape util
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 21 Mar 2020
 * @since 1.0
 */
public class EscapeUtils {

    /**
     * escape String for mysql
     *
     * @param search searching String
     * @return escaped String
     */
    public static String escapeMysql(String search) {
        if (StringUtils.isNotBlank(search)) {

            for (String key : CharityConstants.ESCAPE_STRINGS) {
                if (search.contains(key)) {
                    search = search.replace(key, CharityConstants.ESCAPE_REPLACE + key);
                }
            }
        }

        return search;
    }
}
