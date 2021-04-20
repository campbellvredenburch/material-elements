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

package com.zeoflow.material.elements.color;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import com.zeoflow.material.elements.resources.MaterialAttributes;

import java.util.Locale;

import static android.graphics.Color.TRANSPARENT;

/**
 * ColorUtils a util class for changing the form of colors.
 */
public class ColorUtils
{
  public static final float ALPHA_FULL = 1.00F;
  public static final float ALPHA_MEDIUM = 0.54F;
  public static final float ALPHA_DISABLED = 0.38F;
  public static final float ALPHA_LOW = 0.32F;
  public static final float ALPHA_DISABLED_LOW = 0.12F;

  public ColorUtils()
  {
    // Private constructor to prevent unwanted construction.
  }

  /**
   * changes color to string hex code.
   */
  public static String getHexCode(@ColorInt int color)
  {
    int a = Color.alpha(color);
    int r = Color.red(color);
    int g = Color.green(color);
    int b = Color.blue(color);
    return String.format(Locale.getDefault(), "%02X%02X%02X%02X", a, r, g, b);
  }

  /**
   * changes color to argb integer array.
   */
  public static int[] getColorARGB(@ColorInt int color)
  {
    int[] argb = new int[4];
    argb[0] = Color.alpha(color);
    argb[1] = Color.red(color);
    argb[2] = Color.green(color);
    argb[3] = Color.blue(color);
    return argb;
  }

  /**
   * Returns the color int for the provided theme color attribute, using the {@link Context} of the
   * provided {@code view}.
   *
   * @throws IllegalArgumentException if the attribute is not set in the current theme.
   */
  @ColorInt
  public static int getColor(@NonNull View view, @AttrRes int colorAttributeResId)
  {
    return MaterialAttributes.resolveOrThrow(view, colorAttributeResId);
  }

  /**
   * Returns the color int for the provided theme color attribute.
   *
   * @throws IllegalArgumentException if the attribute is not set in the current theme.
   */
  @ColorInt
  public static int getColor(
          Context context, @AttrRes int colorAttributeResId, String errorMessageComponent)
  {
    return MaterialAttributes.resolveOrThrow(context, colorAttributeResId, errorMessageComponent);
  }

  /**
   * Returns the color int for the provided theme color attribute, or the default value if the
   * attribute is not set in the current theme, using the {@code view}'s {@link Context}.
   */
  @ColorInt
  public static int getColor(
          @NonNull View view, @AttrRes int colorAttributeResId, @ColorInt int defaultValue)
  {
    return getColor(view.getContext(), colorAttributeResId, defaultValue);
  }

  /**
   * Returns the color int for the provided theme color attribute, or the default value if the
   * attribute is not set in the current theme.
   */
  @ColorInt
  public static int getColor(
          @NonNull Context context, @AttrRes int colorAttributeResId, @ColorInt int defaultValue)
  {
    TypedValue typedValue = MaterialAttributes.resolve(context, colorAttributeResId);
    if (typedValue != null)
    {
      return typedValue.data;
    } else
    {
      return defaultValue;
    }
  }

  /**
   * Convenience method that calculates {@link ColorUtils#layer(View, int, int, float)} without
   * an {@code overlayAlpha} value by passing in {@code 1f} for the alpha value.
   */
  @ColorInt
  public static int layer(
          @NonNull View view,
          @AttrRes int backgroundColorAttributeResId,
          @AttrRes int overlayColorAttributeResId)
  {
    return layer(view, backgroundColorAttributeResId, overlayColorAttributeResId, 1f);
  }

  /**
   * Convenience method that wraps {@link ColorUtils#layer(int, int, float)} for layering colors
   * from theme attributes.
   */
  @ColorInt
  public static int layer(
          @NonNull View view,
          @AttrRes int backgroundColorAttributeResId,
          @AttrRes int overlayColorAttributeResId,
          @FloatRange(from = 0.0, to = 1.0) float overlayAlpha)
  {
    int backgroundColor = getColor(view, backgroundColorAttributeResId);
    int overlayColor = getColor(view, overlayColorAttributeResId);
    return layer(backgroundColor, overlayColor, overlayAlpha);
  }

  /**
   * Calculates a color that represents the layering of the {@code overlayColor} (with {@code
   * overlayAlpha} applied) on top of the {@code backgroundColor}.
   */
  @ColorInt
  public static int layer(
          @ColorInt int backgroundColor,
          @ColorInt int overlayColor,
          @FloatRange(from = 0.0, to = 1.0) float overlayAlpha)
  {
    int computedAlpha = Math.round(Color.alpha(overlayColor) * overlayAlpha);
    int computedOverlayColor = androidx.core.graphics.ColorUtils.setAlphaComponent(overlayColor, computedAlpha);
    return layer(backgroundColor, computedOverlayColor);
  }

  /**
   * Calculates a color that represents the layering of the {@code overlayColor} on top of the
   * {@code backgroundColor}.
   */
  @ColorInt
  public static int layer(@ColorInt int backgroundColor, @ColorInt int overlayColor)
  {
    return androidx.core.graphics.ColorUtils.compositeColors(overlayColor, backgroundColor);
  }

  /**
   * Calculates a new color by multiplying an additional alpha int value to the alpha channel of a
   * color in integer type.
   *
   * @param originalARGB The original color.
   * @param alpha        The additional alpha [0-255].
   *
   * @return The blended color.
   */
  @ColorInt
  public static int compositeARGBWithAlpha(
          @ColorInt int originalARGB, @IntRange(from = 0, to = 255) int alpha)
  {
    alpha = Color.alpha(originalARGB) * alpha / 255;
    return androidx.core.graphics.ColorUtils.setAlphaComponent(originalARGB, alpha);
  }

  /**
   * Determines if a color should be considered light or dark.
   */
  public static boolean isColorLight(@ColorInt int color)
  {
    return color != TRANSPARENT && androidx.core.graphics.ColorUtils.calculateLuminance(color) > 0.5;
  }
}
