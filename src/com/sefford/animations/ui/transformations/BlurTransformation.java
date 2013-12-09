package com.sefford.animations.ui.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import com.squareup.picasso.Transformation;

/**
 * Created with IntelliJ IDEA.
 * User: sefford
 * Date: 29/11/13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class BlurTransformation implements Transformation {

    private static final int RADIUS = 25;
    private final Context context;

    public BlurTransformation(Context context) {
        this.context = context;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        // Load a clean bitmap and work from that.
        Bitmap originalBitmap = source;

        // Create another bitmap that will hold the results of the filter.
        Bitmap blurredBitmap;
        blurredBitmap = Bitmap.createBitmap(originalBitmap);

        // Create the Renderscript instance that will do the work.
        RenderScript rs = RenderScript.create(context);

        // Allocate memory for Renderscript to work with
        Allocation input = Allocation.createFromBitmap(rs, originalBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SCRIPT);
        Allocation output = Allocation.createTyped(rs, input.getType());

        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setInput(input);
        // Set the blur radius
        script.setRadius(RADIUS);

        // Start the ScriptIntrinisicBlur
        script.forEach(output);
        // Copy the output to the blurred bitmap
        output.copyTo(blurredBitmap);
        if (originalBitmap != blurredBitmap) {
            originalBitmap.recycle();
        }
        return blurredBitmap;
    }

    @Override
    public String key() {
        return "blurred";
    }
}