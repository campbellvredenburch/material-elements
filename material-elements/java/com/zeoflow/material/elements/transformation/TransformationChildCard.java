/*
 * Copyright 2020 ZeoFlow
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zeoflow.material.elements.transformation;

import android.content.Context;
import android.util.AttributeSet;

import com.zeoflow.material.elements.circularreveal.cardview.CircularRevealCardView;
import com.zeoflow.material.elements.expandable.ExpandableWidget;
import com.zeoflow.material.elements.transition.MaterialContainerTransform;

/**
 * CardView layout for views that can react to an {@link ExpandableWidget}'s {@link
 * ExpandableWidget#setExpanded(boolean)} state changes by transforming the ExpandableWidget into
 * itself.
 *
 * <p>This ViewGroup should contain exactly one child.
 *
 * <p>This class should be used if you need to support shadows on pre-L devices.
 *
 * @deprecated Use {@link MaterialContainerTransform}
 * instead.
 */
@Deprecated
public class TransformationChildCard extends CircularRevealCardView
{

  public TransformationChildCard(Context context)
  {
    this(context, null);
  }

  public TransformationChildCard(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }
}