package com.vkdinventor.app.foodrecipes;

import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by einfochips on 6/7/17.
 */

public abstract class BaseTest {

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);
        if (RuntimeEnvironment.application!= null){
            ShadowApplication shadowApp = Shadows.shadowOf( RuntimeEnvironment.application);
            shadowApp.grantPermissions("android.permission.INTERNET");
        }
    }
}
