/*
 * Copyright 2018 The Apache Software Foundation.
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
package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler;
import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDFreeTextAppearanceHandler;
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText;

/**
 *
 * @author Paul King
 */
public class PDAnnotationFreeText extends PDAnnotationMarkup
{
    /**
     * The type of annotation.
     */
    public static final String SUB_TYPE = "FreeText";

    private PDAppearanceHandler freeTextAppearanceHandler;

    public PDAnnotationFreeText()
    {
        getCOSObject().setName(COSName.SUBTYPE, SUB_TYPE);
    }

    /**
     * Creates a FreeText annotation from a COSDictionary, expected to be a correct object definition.
     *
     * @param field the PDF object to represent as a field.
     */
    public PDAnnotationFreeText(COSDictionary field)
    {
        super(field);
    }

    /**
     * Get the default appearance.
     * 
     * @return a string describing the default appearance.
     */
    public String getDefaultAppearance()
    {
        return getCOSObject().getString(COSName.DA);
    }

    /**
     * Set the default appearance.
     *
     * @param daValue a string describing the default appearance.
     */
    public void setDefaultAppearance(String daValue)
    {
        getCOSObject().setString(COSName.DA, daValue);
    }

    /**
     * Get the default style string.
     *
     * The default style string defines the default style for rich text fields.
     *
     * @return the DS element of the dictionary object
     */
    public String getDefaultStyleString()
    {
        return getCOSObject().getString(COSName.DS);
    }

    /**
     * Set the default style string.
     *
     * Providing null as the value will remove the default style string.
     *
     * @param defaultStyleString a string describing the default style.
     */
    public void setDefaultStyleString(String defaultStyleString)
    {
        getCOSObject().setString(COSName.DS, defaultStyleString);
    }

    /**
     * This will get the 'quadding' or justification of the text to be displayed.
     * 
     * This is an inheritable attribute.
     * <br>
     * 0 - Left(default)<br>
     * 1 - Centered<br>
     * 2 - Right<br>
     * Please see the QUADDING_CONSTANTS in {@link PDVariableText }.
     *
     * @return The justification of the text strings.
     */
    public int getQ()
    {
        return getCOSObject().getInt(COSName.Q, 0);
    }

    /**
     * This will set the quadding/justification of the text. Please see the QUADDING_CONSTANTS
     * in {@link PDVariableText }.
     *
     * @param q The new text justification.
     */
    public void setQ(int q)
    {
        getCOSObject().setInt(COSName.Q, q);
    }

    /**
     * Set a custom appearance handler for generating the annotations appearance streams.
     * 
     * @param freeTextAppearanceHandler
     */
    public void setCustomFreeTextAppearanceHandler(PDAppearanceHandler freeTextAppearanceHandler)
    {
        this.freeTextAppearanceHandler = freeTextAppearanceHandler;
    }

    @Override
    public void constructAppearances()
    {
        if (freeTextAppearanceHandler == null)
        {
            PDFreeTextAppearanceHandler appearanceHandler = new PDFreeTextAppearanceHandler(this);
            appearanceHandler.generateAppearanceStreams();
        }
        else
        {
            freeTextAppearanceHandler.generateAppearanceStreams();
        }
    }
}
