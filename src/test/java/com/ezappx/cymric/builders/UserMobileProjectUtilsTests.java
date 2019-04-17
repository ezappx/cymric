package com.ezappx.cymric.builders;


import com.ezappx.cymric.builders.utils.UserMobileProjectUtil;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserMobileProjectUtilsTests {

    @Test
    public void checkPackageNameValid() {
        assertTrue((UserMobileProjectUtil.isValidPackageName("com.ezappx")));

        assertFalse((UserMobileProjectUtil.isValidPackageName("1com.ezappx")));
        assertFalse((UserMobileProjectUtil.isValidPackageName("com")));
        assertFalse((UserMobileProjectUtil.isValidPackageName("com.")));
        assertFalse((UserMobileProjectUtil.isValidPackageName("com.test-project")));
    }
}
