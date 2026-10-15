package com.captainclaw.android;

import org.libsdl.app.SDLActivity;

/**
 * SDL2 host activity for the OpenClaw native game library.
 * This first bootstrap stage does not yet implement the touch overlay or game-file importer.
 */
public class MainActivity extends SDLActivity {
    @Override
    protected String[] getLibraries() {
        return new String[] {
            "SDL2",
            "openclaw"
        };
    }
}
