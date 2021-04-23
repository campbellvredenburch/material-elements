/*
 * Copyright (C) 2021 ZeoFlow
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zeoflow.material.elements.bottomsheet.utils;

import android.os.Build;
import android.view.View;
import android.view.Window;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class Extensions
{

    public static void changeNavigationIconColor(boolean isLight, DialogFragment fragment)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            Window window = Objects.requireNonNull(fragment.getDialog()).getWindow();
            int flags = window.getDecorView().getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                flags = isLight ?
                        flags ^ View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR :
                        flags ^ View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;

            }
            window.getDecorView().setSystemUiVisibility(flags);
        }
    }
    public static void changeStatusBarIconColor(boolean isLight, DialogFragment fragment)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            Window window = Objects.requireNonNull(fragment.getDialog()).getWindow();
            int flags = window.getDecorView().getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                flags = isLight ?
                        flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR :
                        flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

            }
            window.getDecorView().setSystemUiVisibility(flags);
        }
    }

}
