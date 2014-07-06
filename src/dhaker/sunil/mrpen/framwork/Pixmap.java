package dhaker.sunil.mrpen.framwork;

import dhaker.sunil.mrpen.framwork.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
