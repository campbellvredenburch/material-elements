/*
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zeoflow.material.elements.colorwheel.flag;

import android.content.Context;
import android.content.res.ColorStateList;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.ImageViewCompat;

import com.zeoflow.material.elements.R;
import com.zeoflow.material.elements.color.ColorEnvelope;

/**
 * BubbleFlag is a supported {@link FlagView} by the library.
 */
public class BubbleFlag extends FlagView
{

    private final AppCompatImageView bubble;

    public BubbleFlag(Context context)
    {
        super(context, R.layout.flag_bubble);
        this.bubble = findViewById(R.id.bubble);
    }

    /**
     * invoked when selector is moved.
     *
     * @param colorEnvelope provide hsv color, hexCode, argb
     */
    @Override
    public void onRefresh(ColorEnvelope colorEnvelope)
    {
        ImageViewCompat.setImageTintList(bubble, ColorStateList.valueOf(colorEnvelope.getColor()));
    }

}
